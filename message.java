package com.chat.com;

import java.sql.*;

public class message {

    public void sendMessage(String user) throws ClassNotFoundException, SQLException {
        login log = new login();

        System.out.println("Enter the username of the recipient: ");
        String recipient = log.scan.nextLine();

        System.out.println("Enter the message you want to send: ");
        String message = log.scan.nextLine();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/chats","root","");

        PreparedStatement stmt=con.prepareStatement("select username from user where username = ?");
        stmt.setString(1, recipient);

        ResultSet rs=stmt.executeQuery();
        rs.next();

        String id1 = rs.getString("username");

        stmt=con.prepareStatement("select username from user where username = ?");
        stmt.setString(1, user);
        rs = stmt.executeQuery();

        rs.next();

        String id2 = rs.getString("username");


        stmt=con.prepareStatement("insert into chats(sender, receiver, message)values(?,?,?)");

        stmt.setString(1, id2);
        stmt.setString(2, id1);
        stmt.setString(3, message);

        stmt.executeUpdate();

        System.out.println("Message sent!");
    }

    public void readSentMessages(String user) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/chats","root","");

        System.out.println("Here are your sent messages");
        PreparedStatement stmt=con.prepareStatement("select receiver, message, time from chats where sender= ?");
        stmt.setString(1, user);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println( rs.getString("receiver") + " " + rs.getString("time") +":  "+ rs.getString("message"));

        }


    }

    public void readreceivedMessages(String user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/chats","root","");

        System.out.println("Here are your received messages ");

        PreparedStatement stmt=con.prepareStatement("select sender, message, time from chats where receiver = ?");
        stmt.setString(1, user);

        ResultSet rs = stmt.executeQuery();


        while (rs.next()) {
            System.out.println(rs.getString("sender") + " " + rs.getString("time") +":  " + rs.getString("message"));
        }
        con.close();
    }
}
