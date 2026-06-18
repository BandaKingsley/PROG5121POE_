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
    // Encapsulated data layer parameters
    private String savedUserCredential;
    private String savedSecretKey;
    private String accountHolderFirst;
    private String accountHolderLast;
    private String registeredContactNo;

    // Evaluates length threshold and specific character inclusion
    public boolean checkUsername(String clientUserString) {
        return clientUserString.contains("_") && clientUserString.length() <= 5;
    }

    // Comprehensive parsing execution scanning code conditions manually
    public boolean checkPasswordComplexity(String clientSecretKey) {
        if (clientSecretKey.length() < 8) return false;

        boolean upperCaseFlag = false;
        boolean lowerCaseFlag = false;
        boolean numericalFlag = false;
        boolean glyphFlag = false;

        for (int positionIdx = 0; positionIdx < clientSecretKey.length(); positionIdx++) {
            char targetedCharacter = clientSecretKey.charAt(positionIdx);
            if (Character.isUpperCase(targetedCharacter)) upperCaseFlag = true;
            else if (Character.isLowerCase(targetedCharacter)) lowerCaseFlag = true;
            else if (Character.isDigit(targetedCharacter)) numericalFlag = true;
            else if (!Character.isLetterOrDigit(targetedCharacter)) glyphFlag = true;
        }

        return upperCaseFlag && lowerCaseFlag && numericalFlag && glyphFlag;
    }

    // RegEx checker asserting standard international prefixes (+27)
    public boolean checkCellPhoneNumber(String mobileDialInCode) {
        String localizedRegexMask = "^\\+27[0-9]{9}$";
        return Pattern.matches(localizedRegexMask, mobileDialInCode);
    }

    // Validates inbound data states using core rules before assigning instances
    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName) {
        this.accountHolderFirst = firstName;
        this.accountHolderLast = lastName;

        if (!checkUsername(username)) {
            return "Username is not correctly formatted; please ensure that the username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        this.savedUserCredential = username;
        this.savedSecretKey = password;
        this.registeredContactNo = cellNumber;
        return "Username, password and cell phone number successfully captured.";
    }

    // Direct equality assertion for credential mapping logic
    public boolean loginUser(String verificationUser, String verificationPass) {
        return verificationUser.equals(this.savedUserCredential) && verificationPass.equals(this.savedSecretKey);
    }

    // Formats system output responses based on boolean inputs
    public String returnLoginStatus(boolean runtimeExecutionFlag) {
        if (runtimeExecutionFlag) {
            return "Welcome " + accountHolderFirst + ", " + accountHolderLast + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
