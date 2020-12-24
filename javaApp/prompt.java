package jdbc;
import jdbc.Connector;
import java.util.*;
import java.sql.*;

public class prompt {
     int connectPrompt() {
        Scanner sc = new Scanner(System.in);
        Connector con = new Connector();
        String connect = "Are you supposed to be connected this db? Enter Y (yes) or N (no).";
        System.out.println(connect);
        String strOpt = sc.nextLine();
        if(strOpt.equals("y") || strOpt.equals("Y") || strOpt.equals("yes") || strOpt.equals("Yes"))
            return 0;
        if(strOpt.equals("n") || strOpt.equals("N") || strOpt.equals("no") || strOpt.equals("No"))
            return 1;  
        return 0;
    }
}