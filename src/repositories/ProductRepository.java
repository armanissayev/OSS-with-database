package repositories;
import entities.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static Connection connection;
    private static ProductRepository instance;

    private ProductRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "0000");
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    private static void createTableIfNotExists() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS products ("
                    + "Id SERIAL PRIMARY KEY,"
                    + "Name VARCHAR(50) NOT NULL,"
                    + "Price DOUBLE PRECISION NOT NULL,"
                    + "Quantity INT NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
    }

    public void addProduct(String name, double price, int quantity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO products (Name, Price, Quantity) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Products> getAllProducts() {
        List<Products> productsList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                Products product = new Products();
                product.setId(resultSet.getInt("Id"));
                product.setName(resultSet.getString("Name"));
                product.setPrice(resultSet.getDouble("Price"));
                product.setQuantity(resultSet.getInt("Quantity"));
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsList;
    }

    public void updateProduct(int id, String name, double price, int quantity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE products SET Name = ?, Price = ?, Quantity = ? WHERE Id = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM products WHERE Id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                "UPDATE products SET Quantity = ? WHERE Id = ?",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, quantity);
            preparedStatement.setInt(2, id);
            int update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
