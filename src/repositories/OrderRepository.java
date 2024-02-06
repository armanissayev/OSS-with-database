package repositories;
import controllers.ProductController;

import java.sql.*;

public class OrderRepository {
    private Connection connection;
    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    public void addOrder(int userId, int productId, int quantity, double cost) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
        "INSERT INTO orders (UserId, ProductId, Quantity, Cost) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, cost);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Order created successfully!");
            } else {
                System.out.println("Failed to create an order. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllOrders(UserRepository userRepository, ProductRepository productRepository) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");

            while (resultSet.next()) {
                int id =resultSet.getInt("id");
                int userId = resultSet.getInt("UserId");
                int productId = resultSet.getInt("ProductId");
                int quantity = resultSet.getInt("Quantity");
                double cost = resultSet.getDouble("Cost");

                System.out.printf("ID: %d, User: %s, Product: %s, Quantity: %d, Cost: %f\n", id, userRepository.getUsernameById(userId), productRepository.getProductNameById(productId), quantity, cost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
