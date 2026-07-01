/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.programming1a_poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Validates Part 1 security routines against formatting constraints.
 */
public class LoginTest {
    
    @Test
    public void testUsernameSuccess() {
        Login login = new Login();
        // Valid length constraints containing an underscore character flag match
        assertTrue(login.checkUserName("kyl_1")); //
    }

    @Test
    public void testUsernameFailure() {
        Login login = new Login();
        // Violates maximum length bounds constraints
        assertFalse(login.checkUserName("kyle!!!!!!!")); //
    }

    @Test
    public void testUsernameMissingUnderscore() {
        Login login = new Login();
        // Right length but missing the required underscore character
        assertFalse(login.checkUserName("kyle1"));
    }

    @Test
    public void testPasswordComplexitySuccess() {
        Login login = new Login();
        // Passes uppercase, numerical, length, and symbol requirements cleanly
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!")); //
    }

    @Test
    public void testPasswordComplexityFailure() {
        Login login = new Login();
        // Lacks numeric validation metrics or symbol constraints
        assertFalse(login.checkPasswordComplexity("password")); //
    }

    @Test
    public void testPasswordTooShort() {
        Login login = new Login();
        // Has special char, capital, and number, but is less than 8 characters long
        assertFalse(login.checkPasswordComplexity("A1!b2"));
    }

    @Test
    public void testCellPhoneNumberSuccess() {
        Login login = new Login();
        // Aligned properly with South African cell identifier layouts
        assertTrue(login.checkCellPhoneNumber("+27838968976")); //
    }

    @Test
    public void testCellPhoneNumberFailure() {
        Login login = new Login();
        // Fails due to missing proper lengths or country prefix layout formats
        assertFalse(login.checkCellPhoneNumber("08966553")); //
    }

    @Test
    public void testCellPhoneNumberNullAndEmpty() {
        Login login = new Login();
        // Confirms the function safely rejects null or empty phone values
        assertFalse(login.checkCellPhoneNumber(null));
        assertFalse(login.checkCellPhoneNumber(""));
    }
}
