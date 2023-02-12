/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atminterfacemain;

/**
 *
 * @author sadit
 */
import java.sql.*;
import java.util.ArrayList;

public class LogInHandle {



    public static User login(int uId,int pin,Connection con) {
        User user = null;
        if(con!=null) {
//            2. get user details with uid and pin with transaction
            try {
                String query = "select * from user where uId = ? and pin = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1,uId);
                stmt.setInt(2,pin);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    int userId = rs.getInt(1);
                    int pass = rs.getInt(2);
                    double balance = rs.getDouble(3);
                    user = new User(userId,pin,balance);
                    UserHandle.loadTransactions(con,user);
                }
            } catch (SQLException e) {
                user = null;
            }

        }
        return user;
    }
}