package repositories;
import java.sql.*;
import entities.*;

public class UserRepository {
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }


    public void addUser(String username, int age, double balance) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users (Username, Age, Balance) VALUES(?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, age);
            preparedStatement.setDouble(3, balance);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User created successfully!");
            } else {
                System.out.println("Failed to create user! Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("Username");
                int age = resultSet.getInt("Age");
                double balance = resultSet.getDouble("Balance");

                System.out.println("ID: " + id + ", Username: " + username + ", Age: " + age + ", Balance: " + balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserById(int id, String username, int age, double balance) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users SET Username = ?, Age = ?, Balance = ? WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, age);
            preparedStatement.setDouble(3, balance);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("Failed to update user! Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM users WHERE ID = ?",
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("Failed to delete user! Please try again.");
            }
        } catch (SQLException e) {

        }
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
                "UPDATE users SET Balance = ? WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, id);

            int update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
