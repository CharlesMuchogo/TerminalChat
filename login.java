package com.chat.com;
import java.sql.*;
import java.util.Scanner;

public class login {
    Scanner scan = new Scanner(System.in);

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;

    public void getDetails() throws ClassNotFoundException, SQLException {

        String username = scan.nextLine();

        System.out.println("Enter your username:");
        username = scan.nextLine();

        setUser(username);

        System.out.println("Enter your password:");
        String password = scan.nextLine();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/chats","root","");

        PreparedStatement stmt=con.prepareStatement("select username, password from user where username = ?");
        stmt.setString(1, username);

        ResultSet rs=stmt.executeQuery();
        rs.next();

            String nusername = rs.getString(1);
            String npassword = rs.getString(2);

        con.close();

        if (npassword.equals(password)){
            System.out.println("login successful");

            message mes = new message();

            System.out.println("Enter S to send message, R to read received messages and V to view sent messages. Enter Q to log out");
            char choice = scan.nextLine().toUpperCase().charAt(0);

            while (choice != 'Q') {

                if (choice == 'S') {
                    mes.sendMessage(username);
                } else if (choice == 'R') {
                    mes.readreceivedMessages(username);
                } else if (choice == 'V') {
                    mes.readSentMessages(username);
                }

                System.out.println("Enter S to send message, R to read received messages and V to view sent messages. Enter Q to log out");
                choice = scan.nextLine().toUpperCase().charAt(0);
            }
        }else{
            System.out.println("wrong details entered!!");
        }



    }
}
