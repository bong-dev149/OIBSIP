package com.atm;

import java.sql.*;

public class LogInHandle {

//    method to load transactions
//    private void loadTransactions() {
//
//    }

    public static User login(int uId,int pin) {
//        1. connect to database
        Connection con = ConnectToDB.connect();
        User user = null;
        if(con!=null) {
//            2. get user details with uid and pin with transaction
            try {
                String query = "select * from user where uId = ? and pin = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1,uId);
                stmt.setInt(2,pin);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    int userId = rs.getInt(1);
                    int pass = rs.getInt(2);
                    double balance = rs.getDouble(3);
                    user = new User(userId,pin,balance);
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return user;
    }
}
