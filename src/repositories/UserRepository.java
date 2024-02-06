package repositories;
import java.sql.*;
import entities.*;

public class UserRepository {
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public String getUsernameById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users WHERE Id = ?",
            Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("Username");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public double getBalanceById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("Balance");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setBalanceById(int id, double balance) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users SET Balance = ?, WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
