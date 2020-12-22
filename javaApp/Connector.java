import java.sql.*;

public class Connector {
    public static void main(String[] args) {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordDb", "testuser", "securepassword");
        Statement stmnt = conn.createStatement();
        try () {

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
