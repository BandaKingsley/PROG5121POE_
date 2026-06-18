/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.programming1a_poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Norothy
 */
public class LoginTest {

    @Test
    public void testUsernameSuccess() {
        Login login = new Login();
        // Correct format: contains underscore and <= 5 chars
        assertTrue(login.checkUsername("kyl_1"));
    }

    @Test
    public void testUsernameFailure() {
        Login login = new Login();
        // Incorrect format: more than 5 characters
        assertFalse(login.checkUsername("kyle_lucas"));
    }

    @Test
    public void testPasswordComplexitySuccess() {
        Login login = new Login();
        // Correct format: meets length, upper, digit, and special char rules
        assertTrue(login.checkPasswordComplexity("Ch0c0l@te"));
    }

    @Test
    public void testPasswordComplexityFailure() {
        Login login = new Login();
        // Incorrect format: no special character or number
        assertFalse(login.checkPasswordComplexity("password"));
    }
}

