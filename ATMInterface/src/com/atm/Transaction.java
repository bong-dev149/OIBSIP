package com.atm;

import java.util.Date;

public class Transaction {

    public Transaction(Date d,String mode,double amount,String to,String from) {
        this.d = new Date(String.valueOf(d));
        this.mode = mode;
        this.amount = amount;
        this.to = to;
        this.from = from;
    }
    public Date d;
    public String mode;
    public double amount;
    public String to;
    public String from;
}
