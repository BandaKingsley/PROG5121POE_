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

public class Login {
    // Basic fields a student would use
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String cellNumber;

    // 1. Check Username: must contain '_' and be <= 5 characters
    public boolean checkUsername(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        }
        return false;
    }

    // 2. Check Password Complexity: standard student conditional checks
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        // Loop through string characters manually
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    // 3. Check Cell Phone Number using basic South African international format regex (+27)
    public boolean checkCellPhoneNumber(String cellNumber) {
        // Matches +27 followed by exactly 9 digits (total 11 chars excluding +)
        String regex = "^\\+27[0-9]{9}$";
        return Pattern.matches(regex, cellNumber);
    }

    // 4. Register User Method matching exact rubric strings
    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        if (!checkUsername(username)) {
            return "Username is not correctly formatted; please ensure that the username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // If all pass, save data
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        return "Username, password and cell phone number successfully captured.";
    }

    // 5. Login verification
    public boolean loginUser(String username, String password) {
        if (username.equals(this.username) && password.equals(this.password)) {
            return true;
        }
        return false;
    }

    // 6. Return login status message matching exact rubric strings
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
