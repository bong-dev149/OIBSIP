package com.atm;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        User u = LogInHandle.login(1234,4321);
        if(u!=null) {
            System.out.println(u.getUserID());
            System.out.println(u.getBalance());
        }
    }
}