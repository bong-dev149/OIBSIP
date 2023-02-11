package com.atm;

public class UserHandle implements ATMInterface {
    private User u;
    public UserHandle(User u) {
        this.u = u;
    }

    @Override
    public void transactionHistory(User u) {

    }

    @Override
    public boolean withdraw(User u) {
        return false;
    }

    @Override
    public boolean deposit(User u) {
        return false;
    }

    @Override
    public boolean transfer(User u) {
        return false;
    }

    @Override
    public void quit() {
        System.exit(1);
    }
}
