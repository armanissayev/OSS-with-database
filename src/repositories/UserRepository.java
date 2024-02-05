package repositories;
import java.sql.*;
import entities.*;

public class UserRepository {
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }
}
