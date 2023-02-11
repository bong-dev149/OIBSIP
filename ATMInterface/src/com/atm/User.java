package com.atm;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private int userID;
    private int pin;

    private double balance;

//    private ArrayList<Transaction> transactions;

    public User(int userId,int pin,double balance) {
        this.balance = balance;
        this.userID = userId;
        this.pin = pin;
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


}
