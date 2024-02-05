package repositories;
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
}
