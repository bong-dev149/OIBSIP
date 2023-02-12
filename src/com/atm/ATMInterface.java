package com.atm;

public interface ATMInterface {
    public void transactionHistory(User u);
    public boolean withdraw(User u);
    public boolean deposit(User u);
    public boolean transfer(User u);
    public void quit();
}
