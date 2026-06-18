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
 * Driver console entry file managing navigation states and input routing channels.
 */
public class Main {
    public static void main(String[] args) {
        Scanner keyboardInputScanner = new Scanner(System.in);
        Login sessionSecurityCoordinator = new Login();
        MessageManager dataArchiveCoordinator = new MessageManager();
        
        boolean userIsAuthenticated = false;

        System.out.println("=== SECURITY GATEWAY INTERFACE ===");
        System.out.println("Complete registration details to build current profile state data:\n");

        // Loop until profile parameters clear validation constraints correctly
        while (true) {
            System.out.print("Select User Username: ");
            String accountUserField = keyboardInputScanner.nextLine();
            System.out.print("Select Secure Password string: ");
            String accountSecretField = keyboardInputScanner.nextLine();
            System.out.print("Enter Profile First Name: ");
            String profileFirstField = keyboardInputScanner.nextLine();
            System.out.print("Enter Profile Last Name: ");
            String profileLastField = keyboardInputScanner.nextLine();
            System.out.print("Enter Contact Cell Phone reference: ");
            String contactPhoneField = keyboardInputScanner.nextLine();

            String registrationResultMsg = sessionSecurityCoordinator.registerUser(
                accountUserField, accountSecretField, profileFirstField, profileLastField, contactPhoneField
            );
            System.out.println("\n" + registrationResultMsg);

            if (registrationResultMsg.contains("successfully captured")) {
                System.out.println("\nAccount established safely. Proceeding to profile access challenge...");
                break;
            }
            System.out.println("Initialization validation rejected. Let's restart registration steps again.\n");
        }

        // Challenge login loops until credentials match profile criteria safely
        while (!userIsAuthenticated) {
            System.out.println("\n--- PROFILE AUTHENTICATION STAGE ---");
            System.out.print("Provide Username: ");
            String inputUser = keyboardInputScanner.nextLine();
            System.out.print("Provide Password: ");
            String inputPass = keyboardInputScanner.nextLine();

            boolean verificationToken = sessionSecurityCoordinator.loginUser(inputUser, inputPass);
            System.out.println(sessionSecurityCoordinator.returnLoginStatus(verificationToken));

            if (verificationToken) {
                userIsAuthenticated = true;
            }
        }

        System.out.println("\nWelcome to QuickChat.");
        boolean processRunningStateFlag = true;

        // Core workflow console option management loops
        while (processRunningStateFlag) {
            System.out.println("\n=================================");
            System.out.println(" MAIN PLATFORM WORKSPACE ");
            System.out.println("=================================");
            System.out.println("1) Send Batch Message Records Collection");
            System.out.println("2) Show Recently Sent Messages Report Summary");
            System.out.println("3) Query Element Using Unique Hash ID Code");
            System.out.println("4) Isolate Longest Message Frame Log");
            System.out.println("5) Remove Specific Message Row Log");
            System.out.println("6) Print Raw Database File JSON Contents");
            System.out.println("7) Terminate Workspace Operations");
            System.out.print("Choose execution parameter path [1-7]: ");

            int structuralSelectionToken;
            try {
                structuralSelectionToken = Integer.parseInt(keyboardInputScanner.nextLine());
            } catch (Exception parseFail) {
                structuralSelectionToken = -1;
            }

            switch (structuralSelectionToken) {
                case 1:
                    System.out.print("How many message logs do you want to enter? ");
                    int transactionBatchSize;
                    try {
                        transactionBatchSize = Integer.parseInt(keyboardInputScanner.nextLine());
                    } catch (Exception e) {
                        transactionBatchSize = 0;
                    }

                    for (int messageLoopCounter = 0; messageLoopCounter < transactionBatchSize; messageLoopCounter++) {
                        System.out.println("\n--- Entering Message Log " + (messageLoopCounter + 1) + " of " + transactionBatchSize + " ---");
                        System.out.print("Enter Target Recipient/Device ID: ");
                        String systemUnitTag = keyboardInputScanner.nextLine();
                        System.out.print("Enter Core Message text payload: ");
                        String transactionTextSegment = keyboardInputScanner.nextLine();
                        
                        dataArchiveCoordinator.addMessage(systemUnitTag, transactionTextSegment);
                        System.out.println("✔ Log Entry " + (messageLoopCounter + 1) + " saved to JSON!");
                    }
                    break;

                case 2:
                    System.out.println("\n=== SYSTEM BROADCAST ENTRIES REPOSITORY COMPILATION ===");
                    System.out.println(dataArchiveCoordinator.displayFullReport());
                    break;

                case 3:
                    System.out.print("Provide system Target Hash reference code to explore: ");
                    String matchHashKey = keyboardInputScanner.nextLine();
                    System.out.println("\n" + dataArchiveCoordinator.searchByMessageID(matchHashKey));
                    break;

                case 4:
                    System.out.println("\n=== MAXIMUM PAYLOAD SIZE SUMMARY VALUE ===");
                    System.out.println("\"" + dataArchiveCoordinator.getLongestMessageReport() + "\"");
                    break;

                case 5:
                    System.out.print("Provide unique Hash reference index targeting removal row: ");
                    String destructionHashKey = keyboardInputScanner.nextLine();
                    System.out.println("\n" + dataArchiveCoordinator.deleteMessageByID(destructionHashKey));
                    break;

                case 6:
                    System.out.println("\n=== RAW DATABASE JSON FLAT-FILE PRINTOUT ===");
                    System.out.println(dataArchiveCoordinator.importDataFromJSON());
                    break;

                case 7:
                    System.out.println("Closing operations workspace workspace safely. Data persistent streams written.");
                    processRunningStateFlag = false;
                    break;

                default:
                    System.out.println("Invalid selection parameter sequence entry choice made, retry selection.");
            }
        }
        keyboardInputScanner.close();
    }
}


