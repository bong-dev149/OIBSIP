/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atminterfacemain;
import java.util.Scanner;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sadit
 */
public class ATMInterfaceMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = ConnectToDB.connect();
        Status st;
        if(con!=null) {
            int uID,pin,choice;
            double amount;
            System.out.print("Enter User Id : ");
            uID = sc.nextInt();
            System.out.print("Enter pin : ");
            pin = sc.nextInt();
            User u = LogInHandle.login(uID, pin,con);
            if(u!=null) {
                UserHandle uhnd = new UserHandle(u,con);
//              Show Options
                System.out.println("""
                               1. Transactions History
                               
                               2. Withdraw
                               
                               3. Deposit
                               
                               4. Transfer
                                   
                               5. Show Balance
                               
                               6. Quit""");
            
                while(true) {
                    System.out.print("Enter your choice : ");
                    choice = sc.nextInt();
                    switch(choice) {
                        case 1:
//                        Transaction history
                            uhnd.transactionHistory();
                            break;
                        case 2:
//                           withdraw
                            System.out.print("Enter the amount to be withdrawn : ");
                            amount = sc.nextDouble();
                            st = uhnd.withdraw(amount,uhnd.u.getUserID());
                            if(st==Status.SUCCESS) {
                                System.out.println(amount + " withdrawl successful!!");
                            } else {
                                System.out.println(amount + " withdrawl unsuccessful!!!");
                            }
                            break;
                        case 3:
                          System.out.print("Enter the amount to deposit : ");
                            amount = sc.nextDouble();
                            st = uhnd.deposit(amount);
                            if(st==Status.SUCCESS) {
                                System.out.println(amount + " deposit successful!!");
                            } else {
                                System.out.println(amount + " deposit unsuccessful!!!");
                            }
                            break;
                        case 4:
                            System.out.print("Enter amount to transfer : ");
                            amount = sc.nextDouble();
                            System.out.print("Enter destination user ID : ");
                            int destId = sc.nextInt();
                            st = uhnd.transfer(amount, destId);
                            if(st==Status.SUCCESS) {
                                System.out.println(amount + " transferred to "+ destId);
                            } else {
                                System.out.println(st);
                            }
                            break;
                        case 5:
                            uhnd.showBalance();
                            break;
                        case 6:
                            uhnd.quit();
                        default:
                            System.out.println("Invalid Choice!");
                    }
                
                }
            
            } else {
                System.out.println("Log In failed!!");
            }
        }
    }
}
