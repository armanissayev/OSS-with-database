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
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres/";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0000";

    public static void main(String[] args) {
            // User
            UserController userController = new UserController();
            // Product
            ProductController productController = new ProductController();
            // Order
            OrderController orderController = new OrderController();

            runUserManagementApp(userController, productController, orderController);
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
            System.out.println("11. Cancel an order");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    userController.addUser(scanner);
                    break;
                case 2:
                    userController.getAllUsers();
                    break;
                case 3:
                    userController.updateUser(scanner);
                    break;
                case 4:
                    userController.deleteUser(scanner);
                    break;
                case 5:
                    productController.addProduct(scanner);
                    break;
                case 6:
                    productController.getAllProducts();
                    break;
                case 7:
                    productController.updateProduct(scanner);
                    break;
                case 8:
                    productController.deleteProduct(scanner);
                    break;
                case 9:
                    orderController.createOrder(scanner);
                    break;
                case 10:
                    orderController.getAllOrders();
                    break;
                case 11:
                    orderController.cancelOrder(scanner);
                    break;
                case 12:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}