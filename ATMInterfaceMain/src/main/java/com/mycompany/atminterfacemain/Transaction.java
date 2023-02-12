/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atminterfacemain;

/**
 *
 * @author sadit
 */
import java.util.Date;

public class Transaction {

    public Transaction(int trans_id, Date d, String mode, double amount, int to, int from) {
        this.d = (Date) d.clone();
        this.mode = mode;
        this.amount = amount;
        this.to = to;
        this.from = from;
        this.trans_id = trans_id;
    }
    
    public Transaction(Date d, String mode, double amount, int to, int from) {
        this.d = (Date) d.clone();
        this.mode = mode;
        this.amount = amount;
        this.to = to;
        this.from = from;
        trans_id = -1;
    }
    
    public final int trans_id;
    public final java.util.Date d;
    public final String mode;
    public final double amount;
    public final int to;
    public final int from;
    
    public void showTransacrion() {
        System.out.println("Transaction ID : " + trans_id + "\t" + d + "\t" + mode + "\t" + amount + "\tfrom " + from + "\tto " + to );
    }
    
    
}
