package jdbc;
import jdbc.prompt;
import java.sql.*;

public class Connector {
    public static void main(String[] args) {
        prompt pr = new prompt();
        //pr.connectPrompt();
        // if(pr.connectPrompt() == 0)
        //     return;

        try (
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordManager?allowPublicKeyRetrieval=true&useSSl=falserverTimezone=UTC", "testuser", "securepassword");
                Statement stmnt = conn.createStatement()) 
            {
                String strSelect = "select password from accounts where website like '%facebook%'";
                ResultSet rSet = stmnt.executeQuery(strSelect);
                while(rSet.next()) {
                    String password = rSet.getString("password");
                    System.out.println(password);
                }       
                
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
