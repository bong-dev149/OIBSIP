/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atminterfacemain;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sadit
 */
public class UserHandle implements ATMInterface {
    public final User u;
    private final Connection con;
    public UserHandle(User u,Connection con) {
        this.u = u;
        this.con = con;
    }
    
    
    //    method to load transactions
   public static void loadTransactions(Connection con,User u) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        if(con!=null && u!=null) {
            try {
                String query = "select * from transactions where trans_from = ? or trans_to = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1,u.getUserID());
                stmt.setInt(2, u.getUserID());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    int trans_id = rs.getInt(1);
                    java.util.Date d =  new java.util.Date(rs.getDate(2).getTime());
                    String mode = rs.getString(3);
                    double amount = rs.getDouble(4);
                    int to = rs.getInt(5);
                    int from = rs.getInt(6);
                    transactions.add(new Transaction(trans_id,d,mode,amount,to,from));
                }
                u.setTransactions(transactions);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
   }
    
    
    
//    method to update an user detail to database
    private Status updateUserToDB(User u) {
        try {
            String q = "update user set balance = ? where uId = ?";
            PreparedStatement stmt = con.prepareStatement(q);
            stmt.setDouble(1, u.getBalance());
            stmt.setInt(2,u.getUserID());
            stmt.executeUpdate();
            return Status.SUCCESS;
        } catch(SQLException e) {
            return Status.SERVER_FAILURE;
        }
        
    }
    
//    method to update an transaction to database
    
    private Status updateTransactionToDB(Transaction t) {
        try {
            String q = "insert into transactions(trans_date,transe_mode,amount,trans_to,trans_from) values (?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(q);
            stmt.setDate(1, new java.sql.Date(t.d.getTime()));
            stmt.setString(2, t.mode);
            stmt.setDouble(3,t.amount);
            stmt.setInt(4,t.to);
            stmt.setInt(5,t.from);
            stmt.executeUpdate();
            return Status.SUCCESS;
        } catch(SQLException e) {
            e.printStackTrace();
            return Status.SERVER_FAILURE;
        }
    }
    
//    
    
    @Override
    public void quit() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandle.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    @Override
    public void transactionHistory() {
       u.showTransactions();
    }
    
    @Override
    public Status withdraw(double amount,int dest) {
//        1. withdraw amount from user object
        double finBal = u.getBalance()-amount;
        if(finBal >= 0) {
            u.setBalance(finBal);
//        2.  create transaction
            Transaction t = new Transaction(new java.util.Date(),"DEBIT",amount,dest,u.getUserID());
//        3.   update to database
          Status st;
          st = updateUserToDB(u);
//          System.out.println(st);
          st = updateTransactionToDB(t);
//          System.out.println(st);
            loadTransactions(con,u);
          return st;
        } else {
            return Status.INSUFF_BAL;
        }
    }

    @Override
    public Status deposit(double amount) {
        //        1. deposit amount from user object
        double finBal = u.getBalance()+amount;
            u.setBalance(finBal);
//        2.  create transaction
            Transaction t = new Transaction(new java.util.Date(),"CREDIT",amount,u.getUserID(),u.getUserID());
//        3.   update to database
          Status st;
          st = updateUserToDB(u);  
//          System.out.println(st);
          st = updateTransactionToDB(t);
//          System.out.println(st);
            loadTransactions(con,u);
          return st;
    }
    
        private Status deposit(double amount,User u) {
        //        1. deposit amount from user object
        double finBal = u.getBalance()+amount;
            u.setBalance(finBal);
//        2.  create transaction
            Transaction t = new Transaction(new java.util.Date(),"CREDIT",amount,u.getUserID(),this.u.getUserID());
//        3.   update to database
          Status st;
          st = updateUserToDB(u);  
//          System.out.println(st);
          st = updateTransactionToDB(t);
//          System.out.println(st);
            loadTransactions(con,u);
          return st;
    }
    
    
//    method to get a specific user by user id from the database
    private User getUser(int uId) {
        String q = "select * from user where uId = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(q);
            stmt.setInt(1, uId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int userId = rs.getInt(1);
                double balance = rs.getDouble(3);
                return new User(userId,balance);
            } else {
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserHandle.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    

    @Override
    public Status transfer(double amount,int destId) {
        Status st = this.withdraw(amount,destId);
        if(st == Status.SUCCESS) {
            User dest = getUser(destId);
            if(dest!=null) {
                st  = deposit(amount, dest);
            }
        }
        return st;
    }

    @Override
    public void showBalance() {
        System.out.println("Balance : " + u.getBalance());
    }
}