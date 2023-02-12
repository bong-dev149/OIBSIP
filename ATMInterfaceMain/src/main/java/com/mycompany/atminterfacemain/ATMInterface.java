/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.atminterfacemain;

/**
 *
 * @author sadit
 */
public interface ATMInterface {
    public void transactionHistory();
    public Status withdraw(double amount,int dest);
    public Status deposit(double amount);
    public Status transfer(double amount,int destId);
    public void showBalance();
    public void quit();
}
