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
        Scanner keyboardInputScanner = new Scanner(System.in);
        Login authenticationProcessor = new Login();
        MessageManager dataArchiveCoordinator = new MessageManager();

        System.out.println("--- SYSTEM INITIALIZATION & REGISTRATION ---");
        System.out.print("First Name: "); String userFirst = keyboardInputScanner.nextLine();
        System.out.print("Last Name: "); String userLast = keyboardInputScanner.nextLine();
        System.out.print("Username: "); String loginUserToken = keyboardInputScanner.nextLine();
        System.out.print("Password: "); String loginSecretKey = keyboardInputScanner.nextLine();
        System.out.print("Cell Number (+27...): "); String telecomLineNumber = keyboardInputScanner.nextLine();

        String registrationProcessFeedback = authenticationProcessor.registerUser(loginUserToken, loginSecretKey, telecomLineNumber, userFirst, userLast);
        System.out.println("\nStatus: " + registrationProcessFeedback);

        if (!registrationProcessFeedback.contains("successfully captured")) return;

        System.out.println("\n--- LOGIN SYSTEM ---");
        System.out.print("Username: "); String loggingCredentialsUser = keyboardInputScanner.nextLine();
        System.out.print("Password: "); String loggingCredentialsPass = keyboardInputScanner.nextLine();

        if (!authenticationProcessor.loginUser(loggingCredentialsUser, loggingCredentialsPass)) {
            System.out.println(authenticationProcessor.returnLoginStatus(false));
            return;
        }
        System.out.println(authenticationProcessor.returnLoginStatus(true));

        // Refactored looping system offering JSON visibility and programmatic batching
        int diagnosticMenuSelection = 0;
        while (diagnosticMenuSelection != 6) {
            System.out.println("\n=== MAIN ARCHIVE MENU ===");
            System.out.println("1. Add Message Logs (Batch Entry)");
            System.out.println("2. Display Longest Message Record");
            System.out.println("3. Search for a Message Phrase");
            System.out.println("4. Delete Message Log via ID");
            System.out.println("5. Read and Display Raw JSON File Data");
            System.out.println("6. Exit & Generate Full Printout Report");
            System.out.print("Select Menu Option: ");
           
            try {
                diagnosticMenuSelection = Integer.parseInt(keyboardInputScanner.nextLine());
            } catch (Exception formatError) {
                diagnosticMenuSelection = 0;
            }

            switch (diagnosticMenuSelection) {
                case 1:
                    System.out.print("How many message logs do you want to enter? ");
                    int transactionBatchSize = 0;
                    try { 
                        transactionBatchSize = Integer.parseInt(keyboardInputScanner.nextLine()); 
                    } catch (Exception e) { 
                        transactionBatchSize = 0; 
                    }
                    
                    // Rubric Specification: Iterative for-loop using index counter tracking
                    for (int messageLoopCounter = 0; messageLoopCounter < transactionBatchSize; messageLoopCounter++) {
                        System.out.println("\n--- Entering Message Log " + (messageLoopCounter + 1) + " of " + transactionBatchSize + " ---");
                        System.out.print("Enter Target Device ID: ");
                        String systemUnitTag = keyboardInputScanner.nextLine();
                        System.out.print("Enter Core Message payload: ");
                        String transactionTextSegment = keyboardInputScanner.nextLine();
                        dataArchiveCoordinator.addMessage(systemUnitTag, transactionTextSegment);
                        System.out.println("✔ Log Entry " + (messageLoopCounter + 1) + " saved to JSON!");
                    }
                    break;
                case 2:
                    System.out.println("\n" + dataArchiveCoordinator.getLongestMessageReport());
                    break;
                case 3:
                    System.out.print("Enter text phrase search query: ");
                    String cryptographicSearchString = keyboardInputScanner.nextLine();
                    System.out.println(dataArchiveCoordinator.searchByMessageText(cryptographicSearchString));
                    break;
                case 4:
                    System.out.print("Enter absolute Message ID to delete: ");
                    String deletionHashSequence = keyboardInputScanner.nextLine();
                    if (dataArchiveCoordinator.deleteMessageByID(deletionHashSequence)) {
                        System.out.println("❌ Entry dropped and JSON storage updated.");
                    } else {
                        System.out.println("⚠️ ID sequence not matched inside arrays.");
                    }
                    break;
                case 5:
                    System.out.println("\n=== RAW DATABASE JSON PRINTOUT ===");
                    System.out.println(dataArchiveCoordinator.importDataFromJSON());
                    break;
                case 6:
                    System.out.println("\n" + dataArchiveCoordinator.displayFullReport());
                    System.out.println("System runtime terminated cleanly.");
                    break;
                default:
                    System.out.println("Invalid selection string parsed.");
            }
        }
    }
}

