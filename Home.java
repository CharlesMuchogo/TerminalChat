package com.chat.com;
import java.sql.*;

public class Home {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        login log = new login();
        register reg = new register();

        int choice;

        System.out.println("************  WELCOME TO TERMINAL CHAT  ************");
        System.out.println("                    1. Login: ");
        System.out.println("                    2. sign up: ");

        choice = log.scan.nextInt();

        if(choice == 1){
            log.getDetails();
        } else if(choice == 2){
            reg.registerNewUser();
        }


    }
}
