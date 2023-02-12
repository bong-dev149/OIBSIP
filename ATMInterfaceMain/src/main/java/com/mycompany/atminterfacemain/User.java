/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atminterfacemain;

/**
 *
 * @author sadit
 */
import java.util.ArrayList;

public class User {
    private int userID;
    private int pin;

    private double balance;

   public ArrayList<Transaction> transactions;

    public User(int userId,int pin,double balance) {
        this.balance = balance;
        this.userID = userId;
        this.pin = pin;
    }
    
    public User(int userId,double balance) {
        this.balance = balance;
        this.userID = userId;
        this.pin = -1;
    }


//    public ArrayList<Transaction> getTransactions() {
//        return transactions;
//    }

    public double getBalance() {
        return balance;
    }

    public int getUserID() { return this.userID;}

    public void setBalance(double balance) {
        if(balance >= 0)
            this.balance = balance;
    }
    
    public void setTransactions(ArrayList<Transaction> tr) {
        this.transactions = new ArrayList<>(tr);
    }
    
    public void showTransactions(int n) {
        for(int i=0;i<n;i++) {
            this.transactions.get(i).showTransacrion();
        }
    }
    
     public void showTransactions() {
        for(int i=0;i<transactions.size();i++) {
            this.transactions.get(i).showTransacrion();
        }
    }


}