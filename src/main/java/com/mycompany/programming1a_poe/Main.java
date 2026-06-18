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

        // Part 3 Full Control Interface Loop
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n=== MAIN ARCHIVE MENU ===");
            System.out.println("1. Add Message Log");
            System.out.println("2. Display Longest Message Record");
            System.out.println("3. Search for a Message Phrase");
            System.out.println("4. Delete Message Log via ID");
            System.out.println("5. Exit & Generate Full Printout Report");
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
                    System.out.print("Enter Core Message payload (Max 250 characters): ");
                    String msg = input.nextLine();
                    manager.addMessage(devID, msg);
                    System.out.println("✔ Log Added Successfully!");
                    break;
                case 2:
                    System.out.println("\n" + manager.getLongestMessageReport());
                    break;
                case 3:
                    System.out.print("Enter text phrase search query: ");
                    String query = input.nextLine();
                    System.out.println(manager.searchByMessageText(query));
                    break;
                case 4:
                    System.out.print("Enter absolute Message ID to delete: ");
                    String deleteID = input.nextLine();
                    if (manager.deleteMessageByID(deleteID)) {
                        System.out.println("❌ Entry dropped successfully from architecture.");
                    } else {
                        System.out.println("⚠️ ID sequence not matched inside arrays.");
                    }
                    break;
                case 5:
                    System.out.println("\n" + manager.displayFullReport());
                    System.out.println("System runtime terminated cleanly.");
                    break;
                default:
                    System.out.println("Invalid selection string parsed.");
            }
        }
    }
}

