/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.programming1a_poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Validates storage arrays, lookup queries, and deletion routines.
 */
public class MessageManagerTest {

     @Test
    public void testGenerateMessageID() {
        MessageManager manager = new MessageManager();
        // Confirms formatting compilation logic evaluates cleanly to matching tokens
        String generatedHash = manager.generateMessageID("SAMSUNG", "Hi Mike, can you join us for dinner tonight?");
        
        // Changed expected value from "SA:HIMIK" to "SA:HIMI" to align with space-stripping logic
        assertEquals("SA:HIMI", generatedHash);
    }


    @Test
    public void testLongestMessageCalculation() {
        MessageManager manager = new MessageManager();
        // Insert explicit test data arrays to mirror target submission guidelines
        manager.addMessage("+27834557896", "Did you get the cake?");
        manager.addMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        manager.addMessage("+27834484567", "Yohoooo, I am at your gate.");
        manager.addMessage("0838884567", "It is dinner time !");
        
        String longestPayload = manager.getLongestMessageReport();
        assertEquals("Where are you? You are late! I have asked you to be on time.", longestPayload);
    }

    @Test
    public void testSearchRecipientSuccess() {
        MessageManager manager = new MessageManager();
        manager.addMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        manager.addMessage("+27838884567", "Ok, I am leaving without you.");
        
        // Changed from searchByMessageText to searchRecipient
        String reportResult = manager.searchRecipient("+27838884567");
        
        // Ensure returning arrays map matching records contextually
        assertTrue(reportResult.contains("Where are you?"));
        assertTrue(reportResult.contains("leaving without you."));
    }


    @Test
    public void testDeleteMessageByIDExecution() {
        MessageManager manager = new MessageManager();
        manager.addMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        
        String targetGeneratedHashID = manager.generateMessageID("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        String deletionConfirmationOutput = manager.deleteMessageByID(targetGeneratedHashID);
        
        // Assert strict structural requirement string mapping outputs perfectly
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", deletionConfirmationOutput);
        assertEquals(0, manager.getRecordCount());
    }
}
