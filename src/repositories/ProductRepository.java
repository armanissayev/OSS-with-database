package repositories;
import java.sql.*;

public class ProductRepository {
    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }
}
