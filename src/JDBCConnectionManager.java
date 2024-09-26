import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionManager {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // for MySQL 8.x.x versions

            // Establish the connection
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_db?user=root&password=password");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to make a connection.");
        }
        return connection;
    }
}

