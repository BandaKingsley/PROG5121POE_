/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.programming1a_poe;

/**
 *
 * @author Norothy
 */
import java.util.Scanner;

/**
 * Driver class: Handles console-based user interface and menu navigation.
 * Updated with Field-Level Validation Trapping for robust error handling.
 */
public class Main {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        Login authModule = new Login();
        Message dataArchive = new Message();
        
        System.out.println("--- QUICKCHAT SECURITY ACCESS ---");
        
        // --- 1. REGISTRATION PHASE (STEP-BY-STEP LOCK) ---
        // Loops lock the user at the specific field until input is valid.
        
        String user = "";
        while (true) {
            System.out.print("Enter Username (must have '_', max 5 chars): ");
            user = inputReader.nextLine();
            if (authModule.checkUserName(user)) break;
            System.out.println(">> Error: Invalid Username. Must contain '_' and be <= 5 chars.");
        }

        String pass = "";
        while (true) {
            System.out.print("Enter Password (8+ chars, cap, digit, symbol): ");
            pass = inputReader.nextLine();
            if (authModule.checkPasswordComplexity(pass)) break;
            System.out.println(">> Error: Invalid Password. Check complexity requirements.");
        }

        System.out.print("Enter First Name: ");
        String first = inputReader.nextLine();
        System.out.print("Enter Last Name: ");
        String last = inputReader.nextLine();

        String phone = "";
        while (true) {
            System.out.print("Enter Cell Number (+27...): ");
            phone = inputReader.nextLine();
            if (authModule.checkCellPhoneNumber(phone)) break;
            System.out.println(">> Error: Invalid Cell Number format.");
        }

        // Finalize registration
        authModule.registerUser(user, pass, first, last, phone);
        System.out.println("\nRegistration successful! Redirecting to login...");
        
        // --- 2. AUTHENTICATION PHASE ---
        while (true) {
            System.out.print("\nUsername: ");
            String loginUser = inputReader.nextLine();
            System.out.print("Password: ");
            String loginPass = inputReader.nextLine();
            
            if (authModule.loginUser(loginUser, loginPass)) {
                System.out.println("Access Granted. Welcome to QuickChat.");
                break;
            } else {
                System.out.println("Invalid credentials. Try again.");
            }
        }
        
        // --- 3. PLATFORM WORKSPACE ---
        boolean active = true;
        while (active) {
            System.out.println("\n1: Add Msg | 2: Report | 3: Search ID | 4: Longest Msg | 5: Delete | 6: View JSON | 7: Exit");
            System.out.print("Select an option: ");
            String choice = inputReader.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.print("Enter number of messages: ");
                    try {
                        int count = Integer.parseInt(inputReader.nextLine());
                        for (int i = 0; i < count; i++) {
                            System.out.print("Recipient: ");
                            String rec = inputReader.nextLine();
                            System.out.print("Message: ");
                            String text = inputReader.nextLine();
                            dataArchive.addMessage(rec, text);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a numeric value.");
                    }
                    break;
                case "2": System.out.println(dataArchive.displayFullReport()); break;
                case "3": 
                    System.out.print("Enter ID: ");
                    System.out.println(dataArchive.searchByMessageID(inputReader.nextLine())); 
                    break;
                case "4": System.out.println("Longest: " + dataArchive.getLongestMessageReport()); break;
                case "5": 
                    System.out.print("Delete ID: ");
                    System.out.println(dataArchive.deleteMessageByID(inputReader.nextLine())); 
                    break;
                case "6": System.out.println(dataArchive.importDataFromJSON()); break;
                case "7": active = false; break;
                default: System.out.println("Invalid selection. Try again.");
            }
        }
        inputReader.close();
    }
}