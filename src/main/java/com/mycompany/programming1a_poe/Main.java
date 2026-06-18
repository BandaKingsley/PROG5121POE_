/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.programming1a_poe;

/**
 *
 * @author Norothy
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login loginSystem = new Login();
        MessageManager manager = new MessageManager();

        System.out.println("--- SYSTEM INITIALIZATION & REGISTRATION ---");
        System.out.print("First Name: "); String first = input.nextLine();
        System.out.print("Last Name: "); String last = input.nextLine();
        System.out.print("Username: "); String user = input.nextLine();
        System.out.print("Password: "); String pass = input.nextLine();
        System.out.print("Cell Number (+27...): "); String cell = input.nextLine();

        String regMessage = loginSystem.registerUser(user, pass, cell, first, last);
        System.out.println("\nStatus: " + regMessage);

        if (!regMessage.contains("successfully captured")) return;

        System.out.println("\n--- LOGIN SYSTEM ---");
        System.out.print("Username: "); String logUser = input.nextLine();
        System.out.print("Password: "); String logPass = input.nextLine();

        if (!loginSystem.loginUser(logUser, logPass)) {
            System.out.println(loginSystem.returnLoginStatus(false));
            return;
        }
        System.out.println(loginSystem.returnLoginStatus(true));

        // Part 2 Main Menu System Loop
        int choice = 0;
        while (choice != 3) {
            System.out.println("\n=== MESSAGE LOGGING SYSTEM ===");
            System.out.println("1. Add Message Entry");
            System.out.println("2. Display Features (Coming Soon)");
            System.out.println("3. Exit Application");
            System.out.print("Select Menu Option: ");
            
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                choice = 0;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Target Device ID: ");
                    String devID = input.nextLine();
                    System.out.print("Enter Core Message payload: ");
                    String msg = input.nextLine();
                    manager.addMessage(devID, msg);
                    System.out.println("✔ Log Entry Saved and Processed Successfully!");
                    break;
                case 2:
                    System.out.println("Reporting module pending Part 3 architecture arrays.");
                    break;
                case 3:
                    System.out.println("System runtime terminated cleanly.");
                    break;
                default:
                    System.out.println("Invalid selection string parsed.");
            }
        }
    }
}
