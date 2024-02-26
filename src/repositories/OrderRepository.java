package repositories;
import controllers.ProductController;

import java.sql.*;
import entities.*;

public class OrderRepository {
    private static Connection connection;
    private static OrderRepository instance;
    private OrderRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "0000");
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized OrderRepository getInstance() {
        if (instance == null){
            instance = new OrderRepository();
        }
        return instance;
    }

    private static void createTableIfNotExists() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS orders ("
                    + "Id SERIAL PRIMARY KEY,"
                    + "UserId INT NOT NULL,"
                    + "ProductId INT NOT NULL,"
                    + "Quantity INT NOT NULL,"
                    + "Cost DOUBLE PRECISION NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
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

    public Order getOrderById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM orders WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Id = resultSet.getInt("Id");
                int userId = resultSet.getInt("UserId");
                int productId = resultSet.getInt("ProductId");
                int quantity = resultSet.getInt("Quantity");
                double cost = resultSet.getDouble("Cost");
                Order order = new Order(Id, userId, productId, quantity, cost);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteOrderById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM orders WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order canceled successfully!");
            } else {
                System.out.println("Failed to cancel an order. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
