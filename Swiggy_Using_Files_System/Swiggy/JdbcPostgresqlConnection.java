import java.sql.*;

public class JdbcPostgresqlConnection {
 
   public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } 
	catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
        // auto close connection
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/school", "postgres", "password")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}