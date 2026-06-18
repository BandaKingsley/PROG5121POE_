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
    // Array storage for Part 2 tracking
    private String[] deviceIDs = new String[100];
    private String[] messages = new String[100];
    private String[] messageIDs = new String[100];
    private int messageCount = 0;

    // Part 2: Core ID Generation Rule
    public String generateMessageID(String deviceID, String message) {
        Random rand = new Random();
        int runningNum = rand.nextInt(900000) + 100000; // 6 digit sequence
        
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
}
