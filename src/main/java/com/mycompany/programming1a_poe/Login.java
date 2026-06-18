/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe;

/**
 *
 * @author Norothy
 */
import java.util.regex.Pattern;

/**
 * Handles security, complexity rules, and user validation constraints.
 * Implements the validation routines required by Part 1 of the PoE rubric.
 */
public class Login {
    
    // In-memory credential tracking states
    private String registeredUsername;
    private String registeredPassword;
    private String registeredFirstName;
    private String registeredLastName;
    private String registeredCellNumber;

    /**
     * Checks if username contains an underscore and is no more than 5 characters long.
     */
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    /**
     * Checks if password meets all length, case, number, and special character requirements.
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        
        boolean hasLength = password.length() >= 8;
        boolean hasCapital = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        
        return hasLength && hasCapital && hasDigit && hasSpecial;
    }

    /**
     * Validates South African cell phone numbers checking country codes and character lengths.
     */
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) return false;
        // Must start with international format '+' or '00' followed by country tracking codes
        return (cellNumber.startsWith("+27") || cellNumber.startsWith("0027") || cellNumber.startsWith("0")) 
                && cellNumber.replaceAll("[^0-9]", "").length() >= 9;
    }

    /**
     * Handles registration message logic and captures credentials upon successful validation matching.
     */
    public String registerUser(String username, String password, String firstName, String lastName, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        
        // Populate records when conditions match perfectly
        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredFirstName = firstName;
        this.registeredLastName = lastName;
        this.registeredCellNumber = cellNumber;
        
        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
    }

    /**
     * Authenticates values against stored state values.
     */
    public boolean loginUser(String username, String password) {
        if (this.registeredUsername == null || this.registeredPassword == null) return false;
        return this.registeredUsername.equals(username) && this.registeredPassword.equals(password);
    }

    /**
     * Compiles status messaging text layouts depending on boolean authentication outcomes.
     */
    public String returnLoginStatus(boolean logInSuccess) {
        if (logInSuccess) {
            return "Welcome " + registeredFirstName + " " + registeredLastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
