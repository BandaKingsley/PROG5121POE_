/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe;

/**
 *
 * @author Norothy
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

public class MessageManager {
    // Parallel arrays for system architecture tracking
    private String[] platformHardwareIDs = new String[100];
    private String[] communicationPayloads = new String[100];
    private String[] generatedSystemHashes = new String[100];
    private int structuralRecordCounter = 0;
    
    // Hardcoded local JSON flat-file storage destination
    private final String DATABASE_FILE_NAME = "archived_data.json";

    // Dynamic generation algorithm combining hardware details and random integers
    public String generateMessageID(String hardwareUnit, String textPayload) {
        Random trackingGen = new Random();
        int uniqueMarker = trackingGen.nextInt(900000) + 100000;
       
        String hardwareToken = hardwareUnit.length() >= 3 ? hardwareUnit.substring(0, 3).toUpperCase() : hardwareUnit.toUpperCase();
        String payloadToken = textPayload.length() >= 3 ? textPayload.substring(textPayload.length() - 3).toUpperCase() : textPayload.toUpperCase();
       
        return hardwareToken + ":" + uniqueMarker + ":" + payloadToken;
    }

    // Appends new message data, processes attributes, and pushes update to local JSON
    public void addMessage(String hardwareUnit, String textPayload) {
        if (structuralRecordCounter < 100) {
            platformHardwareIDs[structuralRecordCounter] = hardwareUnit;
            communicationPayloads[structuralRecordCounter] = textPayload;
            generatedSystemHashes[structuralRecordCounter] = generateMessageID(hardwareUnit, textPayload);
            structuralRecordCounter++;
            exportDataToJSON(); // Dynamic write routine invocation
        }
    }

    // Manual string building routine writing JSON files without external packages
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

    // Direct text stream reader to output the raw JSON format contents
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

    // Loops linear validation criteria across structure tracking long index records
    public String getLongestMessageReport() {
        if (structuralRecordCounter == 0) return "No messages found.";
        int targetedPeakIndex = 0;
        for (int i = 1; i < structuralRecordCounter; i++) {
            if (communicationPayloads[i].length() > communicationPayloads[targetedPeakIndex].length()) {
                targetedPeakIndex = i;
            }
        }
        return "Device ID: " + platformHardwareIDs[targetedPeakIndex] + 
               "\nMessage ID: " + generatedSystemHashes[targetedPeakIndex] + 
               "\nMessage: " + communicationPayloads[targetedPeakIndex];
    }

    // Evaluates containment elements against sub-string arrays
    public String searchByMessageText(String filteringCriterion) {
        StringBuilder responsePayloadBuilder = new StringBuilder("--- SEARCH RESULTS --- \n");
        boolean structuralMatchesFound = false;
        for (int i = 0; i < structuralRecordCounter; i++) {
            if (communicationPayloads[i].toLowerCase().contains(filteringCriterion.toLowerCase())) {
                responsePayloadBuilder.append("Device: ").append(platformHardwareIDs[i])
                      .append(" | ID: ").append(generatedSystemHashes[i])
                      .append(" | Msg: ").append(communicationPayloads[i]).append("\n");
                structuralMatchesFound = true;
            }
        }
        return structuralMatchesFound ? responsePayloadBuilder.toString() : "No messages found matching: " + filteringCriterion;
    }

    // Matches search parameters, deletes targets and shifts trailing indexes leftward
    public boolean deleteMessageByID(String systemTargetHash) {
        for (int i = 0; i < structuralRecordCounter; i++) {
            if (generatedSystemHashes[i].equalsIgnoreCase(systemTargetHash)) {
                for (int shiftingCursor = i; shiftingCursor < structuralRecordCounter - 1; shiftingCursor++) {
                    platformHardwareIDs[shiftingCursor] = platformHardwareIDs[shiftingCursor + 1];
                    communicationPayloads[shiftingCursor] = communicationPayloads[shiftingCursor + 1];
                    generatedSystemHashes[shiftingCursor] = generatedSystemHashes[shiftingCursor + 1];
                }
                structuralRecordCounter--;
                exportDataToJSON(); // Synchronize file states
                return true;
            }
        }
        return false;
    }

    // Combines data points together to construct standard tabular logs
    public String displayFullReport() {
        if (structuralRecordCounter == 0) return "No messages archived.";
        StringBuilder compositeReportBuilder = new StringBuilder("=== FULL ARCHIVED REPORT ===\n");
        for (int i = 0; i < structuralRecordCounter; i++) {
            compositeReportBuilder.append(String.format("Record [%d]\nDevice ID : %s\nMessage ID: %s\nMessage : %s\n---------------------------\n",
                    (i+1), platformHardwareIDs[i], generatedSystemHashes[i], communicationPayloads[i]));
        }
        return compositeReportBuilder.toString();
    }
}
