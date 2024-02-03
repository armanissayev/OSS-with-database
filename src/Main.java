import controllers.UserController;

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

            UserRepository userRepository = new UserRepository(connection);
            UserController userController = new UserController(userRepository);

            runUserManagementApp(userController);

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
    private static void runUserManagementApp(UserController userController) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

        }
    }
}