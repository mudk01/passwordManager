import java.sql.*;
import java.util.Scanner;

public class Connector {

    public static void main(String[] args) {
        Prompt pr = new Prompt();
        try (
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordManager?allowPublicKeyRetrieval=true&useSSl=false&serverTimezone=UTC", "testuser", "securepassword");
            Statement stmnt = conn.createStatement()) 
            {
//                String strSelect = "select password from accounts where website like '%facebook%'";
//                ResultSet rSet = stmnt.executeQuery(strSelect);
//                while(rSet.next()) {
//                    String password = rSet.getString("password");
//                    System.out.println(password);
//                }
                pr.exitPrompt();
                Execute();
                
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
        String sqlSelectWebSite = "select * from accounts where website like '%" + webSite + "%'";
        return sqlSelectWebSite;
    }

    static int SelectionPrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to find a password and associated info, or add a new password and associated info?\nEnter 1 for the first option and 2 for the latter.");
        int sel = sc.nextInt();
        return sel;
    }

    static String[] addInfo() {
        Scanner sc = new Scanner(System.in);
        String website, username, password, email;
        String[] infoArr;
        System.out.println("Please enter your information in the following format, when instructed:\nFor websites, enter the url: www.example.com\nFor username, password, and email, enter as you normally wound enter them");
        System.out.println("Please enter website url: ");
        website = sc.nextLine();
        System.out.println("Please enter username: ");
        username = sc.nextLine();
        System.out.println("Please enter your password: ");
        password = sc.nextLine();
        System.out.println("Please enter your email address: ");
        email = sc.nextLine();
        infoArr = new String[]{website, username, password, email};
        return infoArr;
    }

    static void Execute() {
        Scanner sc = new Scanner(System.in);
        int sel = SelectionPrompt();
        if(sel == 1) {
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
                        Object[][] table = new String[2][4];
                        table[0] = new String[] { "Website", "Email", "Username", "Password" };
                        table[1] = new String[] { usrWebsite, usrEmail, usrUsername, usrPassword };
                        for (final Object[] row : table) {
                            System.out.format("%-25s%-25s%-25s%-25s%n", row);
                        }
                        //System.out.println("Website: " + usrWebsite + " " + "Email: " + usrEmail + " " + "Username: " + usrUsername + " " + "Password: " + usrPassword);
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
                        Object[][] table = new String[2][4];
                        table[0] = new String[] { "Website", "Email", "Username", "Password" };
                        table[1] = new String[] { usrWebsite, usrEmail, usrUsername, usrPassword };
                        for (final Object[] row : table) {
                            System.out.format("%-25s%-25s%-25s%-25s%n", row);
                        }
                    }

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(sel == 2) {
            System.out.println("To add new info, follow instructions below");
            String[] info = new String[3];
            info = addInfo();
            String website = info[0];
            String username = info[1];
            String password = info[2];
            String email = info[3];
            try (
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordManager?allowPublicKeyRetrieval=true&useSSl=false&serverTimezone=UTC", "testuser", "securepassword");
                    Statement stmnt = conn.createStatement())
            {
                String sqlInsert = "insert into accounts (website, email, username, password) values (" + "'" + website + "', '" + email + "', '" + username + "', '" + password + "')";
                int countInserted = stmnt.executeUpdate(sqlInsert);
                if(countInserted == 1) {
                    System.out.println("Successfully added information.");
                }
                else{
                    System.out.println("Please ty again later");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
