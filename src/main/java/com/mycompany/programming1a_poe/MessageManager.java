/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe;

/**
 *
 * @author Norothy
 */
import java.util.Random;

public class MessageManager {
    private String[] deviceIDs = new String[100];
    private String[] messages = new String[100];
    private String[] messageIDs = new String[100];
    private int messageCount = 0;

    public String generateMessageID(String deviceID, String message) {
        Random rand = new Random();
        int runningNum = rand.nextInt(900000) + 100000; 
        
        String cleanDevice = deviceID.length() >= 3 ? deviceID.substring(0, 3).toUpperCase() : deviceID.toUpperCase();
        String cleanMessage = message.length() >= 3 ? message.substring(message.length() - 3).toUpperCase() : message.toUpperCase();
        
        return cleanDevice + ":" + runningNum + ":" + cleanMessage;
    }

    public void addMessage(String deviceID, String message) {
        if (messageCount < 100) {
            deviceIDs[messageCount] = deviceID;
            messages[messageCount] = message;
            messageIDs[messageCount] = generateMessageID(deviceID, message);
            messageCount++;
        }
    }

    // === PART 3 FEATURES START HERE ===

    // 1. Find the longest message string stored in the arrays
    public String getLongestMessageReport() {
        if (messageCount == 0) return "No messages found.";
        int longestIndex = 0;
        for (int i = 1; i < messageCount; i++) {
            if (messages[i].length() > messages[longestIndex].length()) {
                longestIndex = i;
            }
        }
        return "Device ID: " + deviceIDs[longestIndex] + "\nMessage ID: " + messageIDs[longestIndex] + "\nMessage: " + messages[longestIndex];
    }

    // 2. Search arrays for matching text substrings
    public String searchByMessageText(String targetText) {
        StringBuilder output = new StringBuilder("--- SEARCH RESULTS --- \n");
        boolean found = false;
        for (int i = 0; i < messageCount; i++) {
            if (messages[i].toLowerCase().contains(targetText.toLowerCase())) {
                output.append("Device: ").append(deviceIDs[i])
                      .append(" | ID: ").append(messageIDs[i])
                      .append(" | Msg: ").append(messages[i]).append("\n");
                found = true;
            }
        }
        return found ? output.toString() : "No messages found matching: " + targetText;
    }

    // 3. Delete an entry by ID and shift arrays to close the gap
    public boolean deleteMessageByID(String targetID) {
        for (int i = 0; i < messageCount; i++) {
            if (messageIDs[i].equalsIgnoreCase(targetID)) {
                for (int j = i; j < messageCount - 1; j++) {
                    deviceIDs[j] = deviceIDs[j + 1];
                    messages[j] = messages[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                }
                messageCount--;
                return true;
            }
        }
        return false;
    }

    // 4. Print out every entry currently stored parallel in arrays
    public String displayFullReport() {
        if (messageCount == 0) return "No messages archived.";
        StringBuilder sb = new StringBuilder("=== FULL ARCHIVED REPORT ===\n");
        for (int i = 0; i < messageCount; i++) {
            sb.append(String.format("Record [%d]\nDevice ID : %s\nMessage ID: %s\nMessage : %s\n---------------------------\n", 
                    (i+1), deviceIDs[i], messageIDs[i], messages[i]));
        }
        return sb.toString();
    }
}
