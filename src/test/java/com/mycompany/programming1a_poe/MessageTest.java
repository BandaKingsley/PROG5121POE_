/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.programming1a_poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    
    @Test
    public void testGenerateMessageID() {
        Message manager = new Message();
        String generatedHash = manager.generateMessageID("SAMSUNG", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("SA:HIMI", generatedHash);
    }

    @Test
    public void testLongestMessageCalculation() {
        Message manager = new Message();
        String longestPayload = manager.getLongestMessageReport();
        assertNotNull(longestPayload);
    }

    @Test
    public void testSearchRecipientSuccess() {
        Message manager = new Message();
        String reportResult = manager.searchRecipient("+27834557896");
        assertTrue(reportResult.contains("Did you get the cake?"));
    }

    @Test
    public void testSearchRecipientNotFound() {
        Message manager = new Message();
        String reportResult = manager.searchRecipient("9999999999");
        assertEquals("No messages found for this recipient.", reportResult);
    }

    @Test
    public void testSearchByMessageIDCaseInsensitive() {
        Message manager = new Message();
        manager.addMessage("APPLE", "Meeting rescheduled.");
        String generatedID = manager.generateMessageID("APPLE", "Meeting rescheduled.");
        
        String reportResult = manager.searchByMessageID(generatedID.toLowerCase());
        assertTrue(reportResult.contains("AP:MEETI"));
        assertTrue(reportResult.contains("Meeting rescheduled."));
    }

    @Test
    public void testDeleteMessageByIDExecution() {
        Message manager = new Message();
        // Setup: 5 exist (constructor) + 1 added = 6.
        manager.addMessage("+27838884567", "To be deleted");
        String id = manager.generateMessageID("+27838884567", "To be deleted");
        
        // Execution: Delete the 1 we added.
        manager.deleteMessageByID(id);
        
        // Verify: 5 remain.
        assertEquals(5, manager.getRecordCount());
    }
}
