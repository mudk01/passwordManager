package jdbc;
import jdbc.prompt;
import java.sql.*;
import java.util.*;

public class Connector {
    public static void main(String[] args) {
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
                exitPrompt();    
                passPrompt();   
                
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void exitPrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the password manager.\nTo exit, please 'enter' exit or 'q'.");
        String userIn = sc.nextLine();
        if(userIn.equals("q") || userIn.equals("exit"))
            System.exit(0);
        else
            return;
    }

    static String userName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You have selected 'Find by username. Please enter the username you would like to use.");
        String usrNm = sc.nextLine();
        String sqlSelectUsrNm = "select * from accounts where username = '" + usrNm + "'";
        return sqlSelectUsrNm;
    }

    static void passPrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How would you like to find your password?\nYou can view your password for a single site by entering the site name, or enter a username and view all associated accounts.\nPlease enter your choice below.");
        String ppIn = sc.nextLine();
        String strSelectUsrNm = userName();
        if(ppIn.equals("username")){
            try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordManager?allowPublicKeyRetrieval=true&useSSl=falserverTimezone=UTC", "testuser", "securepassword");
                    Statement stmnt = conn.createStatement()) 
                {
                    // String strSelectUsrNm = userName();
                    ResultSet usrSet = stmnt.executeQuery(strSelectUsrNm);
                    while(usrSet.next()) {
                        String usrWebsite = usrSet.getString("website");
                        String usrEmail = usrSet.getString("email");
                        String usrUsername = usrSet.getString("username");
                        String usrPassword = usrSet.getString("password");
                        System.out.println("Website: " + usrWebsite + " " + "Email: " + usrEmail + " " + "Username: " + usrUsername + " " + "Password: " + usrPassword);
                    }  
                    
                }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
