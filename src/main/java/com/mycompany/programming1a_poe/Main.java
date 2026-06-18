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

        System.out.println("--- WELCOME TO REGISTRATION ---");
        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Create Username: ");
        String username = input.nextLine();

        System.out.print("Create Password: ");
        String password = input.nextLine();

        System.out.print("Enter Cell Phone Number (e.g. +27123456789): ");
        String cellNumber = input.nextLine();

        // Run registration validation
        System.out.println("\n--- REGISTRATION STATUS ---");
        String regMessage = loginSystem.registerUser(username, password, cellNumber, firstName, lastName);
        System.out.println(regMessage);

        // Break early if registration breaks formatting constraints
        if (!regMessage.contains("successfully captured")) {
            System.out.println("\nRegistration failed. Please restart and use correct formats.");
            return;
        }

        // Prompt for login verification
        System.out.println("\n--- LOGIN TO YOUR ACCOUNT ---");
        System.out.print("Enter Username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter Password: ");
        String loginPass = input.nextLine();

        boolean loginSuccess = loginSystem.loginUser(loginUser, loginPass);
        System.out.println("\n--- LOGIN STATUS ---");
        System.out.println(loginSystem.returnLoginStatus(loginSuccess));
    }
}
