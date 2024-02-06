import controllers.*;
import entities.*;
import repositories.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0000";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            createUsersTable(connection);
            createProductsTable(connection);
            createOrdersTable(connection);

            // User
            UserRepository userRepository = new UserRepository(connection);
            UserController userController = new UserController(userRepository);

            // Product
            ProductRepository productRepository = new ProductRepository(connection);
            ProductController productController = new ProductController(productRepository);


            // Order
            OrderRepository orderRepository = new OrderRepository(connection);
            OrderController orderController = new OrderController(orderRepository, userRepository, productRepository);

            runUserManagementApp(userController, productController, orderController);

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createUsersTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                    + "Id SERIAL PRIMARY KEY,"
                    + "Username VARCHAR(50) NOT NULL,"
                    + "Age INT NOT NULL,"
                    + "Balance DOUBLE PRECISION NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
    }
    private static void createProductsTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS products ("
                    + "Id SERIAL PRIMARY KEY,"
                    + "Name VARCHAR(50) NOT NULL,"
                    + "Price DOUBLE PRECISION NOT NULL,"
                    + "Quantity INT NOT NULL)";

            statement.executeUpdate(createTableQuery);
        }
    }
    private static void createOrdersTable(Connection connection) throws SQLException {
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
    private static void runUserManagementApp(UserController userController, ProductController productController, OrderController orderController) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add user");
            System.out.println("2. Get all users");
            System.out.println("3. Update user");
            System.out.println("4. Delete user");
            System.out.println("5. Add product");
            System.out.println("6. Get all products");
            System.out.println("7. Update product");
            System.out.println("8. Delete product");
            System.out.println("9. Make an order");
            System.out.println("10. Get all orders");
            System.out.println("11. Update an order");
            System.out.println("12. Cancel an order");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    orderController.createOrder(scanner);
                    break;
                case 10:
                    orderController.getAllOrders();
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}