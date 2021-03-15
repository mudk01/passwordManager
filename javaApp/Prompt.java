import java.util.Scanner;


public class Prompt {
    //    public static void main(String[] args) {
//        connectPrompt();
//    }
    public static int connectPrompt() {
        Scanner sc = new Scanner(System.in);
        String connect = "Are you supposed to be connected this db? Enter Y (yes) or N (no).";
        System.out.println(connect);
        String strOpt = sc.nextLine();
        if(strOpt.equals("y") || strOpt.equals("Y") || strOpt.equals("yes") || strOpt.equals("Yes"))
            return 0;
        if(strOpt.equals("n") || strOpt.equals("N") || strOpt.equals("no") || strOpt.equals("No"))
            return 1;
        return 0;
    }
    public static void exitPrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the password manager.\nTo enter, please press enter. To exit, please enter 'exit' or 'q'.");
        String userIn = sc.nextLine();
        if(userIn.equals("q") || userIn.equals("exit"))
            System.exit(0);
        else
            return;
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
//    public static int SelectionPrompt() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Would you like to find a password and associated info, or add a new password and associated info?\nEnter 1 for the first option and 2 for the latter.");
//        int sel = sc.nextInt();
//        return sel;
//    }
}
