package com.atm;

import java.sql.*;

public class ConnectToDB {
    private static Connection makeConnection(String userName,String pass,String url) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,userName,pass);
            if(con.isClosed()) {
                System.out.println("Database connection is closed");
                return null;
            } else {
                System.out.println("Database connection created");
                return con;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    default connection for my local machine
    public static Connection connect() {
        String userName = "aditya";
        String pass = "14920251";
        String url = "jdbc:mysql://localhost:3306/atm";
        return makeConnection(userName,pass,url);
    }
    public static Connection connect(String userName,String pass,String url) {
        return makeConnection(userName,pass,url);
    }
}
