package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Connector {
    public static void main(String[] args) {
        try (
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordManager?allowPublicKeyRetrieval=true&useSSl=false&serverTimezone=UTC", "testuser", "securepassword");
            Statement stmnt = conn.createStatement()) 
            {
                String strSelect = "select password from accounts where website like '%facebook%'";
                ResultSet rSet = stmnt.executeQuery(strSelect);
                while(rSet.next()) {
                    String password = rSet.getString("password");
                    System.out.println(password);
                }
                prompt.exitPrompt();
                passPrompt();   
                
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static String userNamePrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You have selected 'Find by username. Please enter the username you would like to use.");
        String usrNm = sc.nextLine();
        String sqlSelectUsrNm = "select * from accounts where username = '" + usrNm + "'";
        return sqlSelectUsrNm;
    }
    static String websitePrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the website you wish to find your information for.");
        String webSite = sc.nextLine();
        String sqlSelectWebSite = "select * from accounts where website = '" + webSite + "'";
        return sqlSelectWebSite;
    }
    static void passPrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How would you like to find your password?\nYou can view your password for a single site by entering the site name, or enter a username and view all associated accounts.\nPlease enter your choice below.");
        String ppIn = sc.nextLine();
        if(ppIn.equals("username")){
            try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordManager?allowPublicKeyRetrieval=true&useSSl=false&serverTimezone=UTC", "testuser", "securepassword");
                Statement stmnt = conn.createStatement()) 
                {
                    String strSelectUsrNm = userNamePrompt();
                    ResultSet usrSet = stmnt.executeQuery(strSelectUsrNm);
                    System.out.println();
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
        else if(ppIn.equals("website")) {
            try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordManager?allowPublicKeyRetrieval=true&useSSl=false&serverTimezone=UTC", "testuser", "securepassword");
                Statement stmnt = conn.createStatement()) 
                {
                    String strSelectWbst = websitePrompt();
                    System.out.println(strSelectWbst);
                    ResultSet rSet = stmnt.executeQuery(strSelectWbst);
                    while(rSet.next()) {
                        String usrWebsite = rSet.getString("website");
                        String usrEmail = rSet.getString("email");
                        String usrUsername = rSet.getString("username");
                        String usrPassword = rSet.getString("password");
                        System.out.println("Website: " + usrWebsite + " " + "Email: " + usrEmail + " " + "Username: " + usrUsername + " " + "Password: " + usrPassword);
                    }  
                    
                }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
