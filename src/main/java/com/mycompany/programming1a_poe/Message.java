/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe;

/**
 *
 * @author Norothy
 */
import java.io.*;

/**
 * Manages parallel array stores, search operations, and flat-file JSON serialization.
 */
public class Message {
    
    // Arrays to store message data (Parallel arrays)
    private final String[] platformHardwareIDs = new String[100];
    private final String[] communicationPayloads = new String[100];
    private final String[] generatedSystemHashes = new String[100];
    private int structuralRecordCounter = 0; // Tracks how many messages are saved
    
    private final String DATABASE_FILE_NAME = "archived_data.json";

    /**
     * Constructor: Automatically runs when 'new Message()' is called.
     * Pre-loads the test data required to match the lecturer's demo.
     */
    public Message() {
        addMessage("+27834557896", "Did you get the cake?");
        addMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        addMessage("+27834484567", "Yohoooo, I am at your gate.");
        addMessage("0838884567", "It is dinner time !");
        addMessage("+27834557896", "Test message to reach initial count.");
    }

    // Creates a unique ID: Takes first 2 chars of ID and first 5 chars of message
    public String generateMessageID(String hardwareUnit, String textPayload) {
        String deviceTag = (hardwareUnit != null && hardwareUnit.length() >= 2) ?
                hardwareUnit.substring(0, 2) : "00";
        String msgFragment = (textPayload != null && textPayload.length() >= 5) ?
                textPayload.substring(0, 5).replaceAll(" ", "") : "LOG";
        return (deviceTag + ":" + msgFragment).toUpperCase();
    }

    // Adds a new message and automatically saves to the JSON file
    public void addMessage(String hardwareUnit, String textPayload) {
        if (structuralRecordCounter < 100) {
            platformHardwareIDs[structuralRecordCounter] = hardwareUnit;
            communicationPayloads[structuralRecordCounter] = textPayload;
            generatedSystemHashes[structuralRecordCounter] = generateMessageID(hardwareUnit, textPayload);
            structuralRecordCounter++;
            exportDataToJSON(); // Save to file immediately
        }
    }

    // Returns a formatted string of all messages to display in a dialog box
    public String displayFullReport() {
        if (structuralRecordCounter == 0) return "No messages found.";
        
        StringBuilder compilationBuffer = new StringBuilder();
        for (int i = 0; i < structuralRecordCounter; i++) {
            compilationBuffer.append("MESSAGE STATUS: Sent\n")
                             .append("MESSAGE ID: ").append(generatedSystemHashes[i]).append("\n")
                             .append("RECIPIENT: ").append(platformHardwareIDs[i]).append("\n")
                             .append("MESSAGE: ").append(communicationPayloads[i]).append("\n")
                             .append("------------------------------------\n");
        }
        return compilationBuffer.toString();
    }

    // Searches for the longest message string in the array
    public String getLongestMessageReport() {
        if (structuralRecordCounter == 0) return "No logs available.";
        
        int longestIndex = 0;
        for (int i = 1; i < structuralRecordCounter; i++) {
            if (communicationPayloads[i].length() > communicationPayloads[longestIndex].length()) {
                longestIndex = i;
            }
        }
        return communicationPayloads[longestIndex];
    }

    // Finds a specific message by its unique ID
    public String searchByMessageID(String targetHashID) {
        for (int i = 0; i < structuralRecordCounter; i++) {
            if (generatedSystemHashes[i].equalsIgnoreCase(targetHashID)) {
                return "MESSAGE ID: " + generatedSystemHashes[i] + "\nRECIPIENT: " + platformHardwareIDs[i] + "\nMESSAGE: " + communicationPayloads[i];
            }
        }
        return "Message ID not found.";
    }

    // Finds all messages sent to a specific recipient
    public String searchRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (int i = 0; i < structuralRecordCounter; i++) {
            if (platformHardwareIDs[i].equalsIgnoreCase(recipient)) {
                sb.append("Message: \"").append(communicationPayloads[i]).append("\"\n");
                found = true;
            }
        }
        return found ? sb.toString().trim() : "No messages found for this recipient.";
    }

    // Removes a message and shifts the remaining records to fill the gap
    public String deleteMessageByID(String targetHashID) {
        for (int i = 0; i < structuralRecordCounter; i++) {
            if (generatedSystemHashes[i].equalsIgnoreCase(targetHashID)) {
                String deletedText = communicationPayloads[i];
                // Shift elements left
                for (int j = i; j < structuralRecordCounter - 1; j++) {
                    platformHardwareIDs[j] = platformHardwareIDs[j + 1];
                    communicationPayloads[j] = communicationPayloads[j + 1];
                    generatedSystemHashes[j] = generatedSystemHashes[j + 1];
                }
                structuralRecordCounter--;
                exportDataToJSON(); // Save changes to file
                return "Message: \"" + deletedText + "\" successfully deleted.";
            }
        }
        return "ID not found.";
    }

    // Saves the current array data into a JSON formatted file
    public void exportDataToJSON() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATABASE_FILE_NAME))) {
            writer.println("[");
            for (int i = 0; i < structuralRecordCounter; i++) {
                writer.println(" { \"deviceID\": \"" + platformHardwareIDs[i] + "\", \"messageID\": \"" + generatedSystemHashes[i] + "\", \"message\": \"" + communicationPayloads[i] + "\" }" + (i == structuralRecordCounter - 1 ? "" : ","));
            }
            writer.println("]");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Reads the JSON file and returns it as a string
    public String importDataFromJSON() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) sb.append(line).append("\n");
        } catch (IOException e) { return "No file found."; }
        return sb.toString();
    }
    
    public int getRecordCount() { return structuralRecordCounter; }
}

