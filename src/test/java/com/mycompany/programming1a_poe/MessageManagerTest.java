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
public class MessageManagerTest {

    @Test
    public void testGenerateMessageID() {
        MessageManager manager = new MessageManager();
        String generatedID = manager.generateMessageID("SAMSUNG", "Hello World");
        
        // Verifies the ID starts with the first 3 letters of the device in uppercase
        assertTrue(generatedID.startsWith("SAM:"));
        // Verifies the ID ends with the last 3 letters of the message in uppercase
        assertTrue(generatedID.endsWith(":RLD"));
    }

    @Test
    public void testLongestMessageCalculation() {
        MessageManager manager = new MessageManager();
        manager.addMessage("DEV01", "Short msg");
        manager.addMessage("DEV02", "This is significantly longer payload text");
        
        String report = manager.getLongestMessageReport();
        assertTrue(report.contains("DEV02"));
        assertTrue(report.contains("significantly longer"));
    }
}

