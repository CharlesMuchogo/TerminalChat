package com.chat.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class register {
    login log = new login();

    public void registerNewUser() throws ClassNotFoundException, SQLException {
        System.out.println("Enter your email address");
        String email = log.scan.nextLine();

        System.out.println("Enter your username:");
        String newUser = log.scan.nextLine();

        System.out.println("Enter your password: ");
        String pass1 = log.scan.nextLine();

        System.out.println("Confirm your password: ");
        String pass2 = log.scan.nextLine();

        if(pass1.equals(pass2)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chats","root","");

            PreparedStatement stmt=con.prepareStatement("insert into user(username, password, email)values(?,?,?)");
            stmt.setString(1, newUser);
            stmt.setString(2, pass1);
            stmt.setString(3,email);

            int i=stmt.executeUpdate();
            System.out.println(" Registration successful. Press Enter to continue");

            log.getDetails();

        }else{
            System.out.println("Passwords do not match");
        }
    }
}
