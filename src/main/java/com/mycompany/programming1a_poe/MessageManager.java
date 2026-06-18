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
 * Manages parallel array stores, search operations, and flat-file JSON serialization routines.
 * Aligned precisely with Part 2 and Part 3 task specification tables.
 */
public class MessageManager {
    
    // Parallel primitive array configuration structures to handle records sequentially
    private final String[] platformHardwareIDs = new String[100];
    private final String[] communicationPayloads = new String[100];
    private final String[] generatedSystemHashes = new String[100];
    private int structuralRecordCounter = 0;
    
    // Direct destination targeting string identifying the local storage flat-file
    private final String DATABASE_FILE_NAME = "archived_data.json";

    /**
     * Helper routine mocking algorithmic tracking criteria to compile a standard uppercase ID tracking flag.
     */
    public String generateMessageID(String hardwareUnit, String textPayload) {
        String deviceTag = (hardwareUnit != null && hardwareUnit.length() >= 2) ? hardwareUnit.substring(0, 2) : "00";
        String msgFragment = (textPayload != null && textPayload.length() >= 5) ? textPayload.substring(0, 5).replaceAll(" ", "") : "LOG";
        return (deviceTag + ":" + msgFragment).toUpperCase();
    }

    /**
     * Evaluates constraints and pushes variables to tracking vectors before updating the JSON state.
     */
    public void addMessage(String hardwareUnit, String textPayload) {
        if (structuralRecordCounter < 100) {
            platformHardwareIDs[structuralRecordCounter] = hardwareUnit;
            communicationPayloads[structuralRecordCounter] = textPayload;
            generatedSystemHashes[structuralRecordCounter] = generateMessageID(hardwareUnit, textPayload);
            structuralRecordCounter++;
            
            // Auto-serialize tracking metrics whenever elements insert successfully
            exportDataToJSON();
        }
    }

    /**
     * Compiles full structural summaries standardizing fields matching the exact rubric display format guidelines.
     */
    public String displayFullReport() {
        if (structuralRecordCounter == 0) return "No data elements captured within storage array records yet.";
        
        StringBuilder compilationBuffer = new StringBuilder();
        for (int index = 0; index < structuralRecordCounter; index++) {
            compilationBuffer.append("MESSAGE STATUS: Sent\n");
            compilationBuffer.append("MESSAGE ID: ").append(generatedSystemHashes[index]).append("\n");
            compilationBuffer.append("RECIPIENT: ").append(platformHardwareIDs[index]).append("\n");
            compilationBuffer.append("MESSAGE: ").append(communicationPayloads[index]).append("\n");
            compilationBuffer.append("------------------------------------\n");
        }
        return compilationBuffer.toString();
    }

    /**
     * Scans through payloads to capture the item holding the maximum string length count.
     */
    public String getLongestMessageReport() {
        if (structuralRecordCounter == 0) return "No logs available to process.";
        
        int trackingIndex = 0;
        int maxLengthValue = -1;
        
        for (int index = 0; index < structuralRecordCounter; index++) {
            if (communicationPayloads[index] != null && communicationPayloads[index].length() > maxLengthValue) {
                maxLengthValue = communicationPayloads[index].length();
                trackingIndex = index;
            }
        }
        return communicationPayloads[trackingIndex];
    }

    /**
     * Filters items based on direct textual equality queries targeting specific hash indices.
     */
    public String searchByMessageID(String targetHashID) {
        for (int index = 0; index < structuralRecordCounter; index++) {
            if (generatedSystemHashes[index] != null && generatedSystemHashes[index].equalsIgnoreCase(targetHashID)) {
                return "MESSAGE ID: " + generatedSystemHashes[index] + "\nRECIPIENT: " + platformHardwareIDs[index] + "\nMESSAGE: " + communicationPayloads[index];
            }
        }
        return "Message reference query code not matched within current archives.";
    }

    /**
     * Collects all logs matching a target device/recipient identifier string.
     * Realigned to match the exact Part 3 rubric method name.
     */
    public String searchRecipient(String recipient) {
        StringBuilder responseAccumulator = new StringBuilder();
        boolean itemLocated = false;
        
        for (int index = 0; index < structuralRecordCounter; index++) {
            if (platformHardwareIDs[index] != null && platformHardwareIDs[index].equalsIgnoreCase(recipient)) {
                responseAccumulator.append("Message: \"").append(communicationPayloads[index]).append("\"\n");
                itemLocated = true;
            }
        }
        return itemLocated ? responseAccumulator.toString().trim() : "No message entries discovered matching query constraints.";
    }

    /**
     * Shifts index positions backward to cleanly drop record items and returns the exact rubric-required phrase.
     */
    public String deleteMessageByID(String targetHashID) {
        for (int i = 0; i < structuralRecordCounter; i++) {
            if (generatedSystemHashes[i] != null && generatedSystemHashes[i].equalsIgnoreCase(targetHashID)) {
                String trackingDeletedText = communicationPayloads[i];
                
                // Perform backward register shifting to erase target row tracking safely
                for (int shiftingCursor = i; shiftingCursor < structuralRecordCounter - 1; shiftingCursor++) {
                    platformHardwareIDs[shiftingCursor] = platformHardwareIDs[shiftingCursor + 1];
                    communicationPayloads[shiftingCursor] = communicationPayloads[shiftingCursor + 1];
                    generatedSystemHashes[shiftingCursor] = generatedSystemHashes[shiftingCursor + 1];
                }
                
                structuralRecordCounter--;
                
                // Refresh flat-file JSON storage state to ensure synchronization matches accurately
                exportDataToJSON();
                
                // STRICT RUBRIC STRING REQUIREMENT ALIGNMENT MATCH
                return "Message: \"" + trackingDeletedText + "\" successfully deleted.";
            }
        }
        return "Target tracking record element not identified.";
    }

    /**
     * Standard manual string building routine parsing records directly into structured text loops.
     */
    public void exportDataToJSON() {
        try (PrintWriter diskWriter = new PrintWriter(new FileWriter(DATABASE_FILE_NAME))) {
            diskWriter.println("[");
            for (int index = 0; index < structuralRecordCounter; index++) {
                diskWriter.println(" {");
                diskWriter.println(" \"deviceID\": \"" + platformHardwareIDs[index] + "\",");
                diskWriter.println(" \"messageID\": \"" + generatedSystemHashes[index] + "\",");
                diskWriter.println(" \"message\": \"" + communicationPayloads[index] + "\"");
                diskWriter.print(" }" + (index == structuralRecordCounter - 1 ? "" : ","));
                diskWriter.println();
            }
            diskWriter.println("]");
        } catch (IOException errorEvent) {
            System.out.println("Persistent state serialization warning: " + errorEvent.getMessage());
        }
    }

    /**
     * Custom file retrieval stream processing routine allowing simple reading validation output.
     */
    public String importDataFromJSON() {
        StringBuilder fileStreamBuffer = new StringBuilder();
        try (BufferedReader diskReader = new BufferedReader(new FileReader(DATABASE_FILE_NAME))) {
            String logicalDataLine;
            while ((logicalDataLine = diskReader.readLine()) != null) {
                fileStreamBuffer.append(logicalDataLine).append("\n");
            }
        } catch (IOException missingFileEvent) {
            return "Flat file database sequence not established yet.";
        }
        return fileStreamBuffer.toString();
    }
    
    // Inspector access method to pull structural iteration values inside testing runs safely
    public int getRecordCount() {
        return this.structuralRecordCounter;
    }
}

