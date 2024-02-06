package repositories;
import java.sql.*;

public class ProductRepository {
    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public String getProductNameById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM products WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public double getPriceById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM products WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double s = resultSet.getDouble("Price");
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getQuantityById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM products WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int s = resultSet.getInt("Quantity");
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void setQuantityById(int id, int quantity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE products SET Quantity = ?, WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, quantity);
            preparedStatement.setInt(2, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
