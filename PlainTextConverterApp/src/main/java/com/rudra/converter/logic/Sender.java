package com.rudra.converter.logic;

import javax.swing.*;
import java.awt.Component;   // FIX: added import
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sender {

    /**
     * Sends data to an endpoint. Uses a simple POST with optional Bearer key.
     * Shows dialogs for endpoint and key. Uses the provided parent for dialogs.
     */
    public static void sendData(String format, String data, Component parent) {
        String endpoint = JOptionPane.showInputDialog(parent, "Enter API Endpoint (https://...):");
        if (endpoint == null || endpoint.isBlank()) return;

        String key = JOptionPane.showInputDialog(parent, "Enter API Key (optional):");

        HttpURLConnection conn = null;
        try {
            URL url = new URL(endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String contentType = switch (format.toLowerCase()) {
                case "json", "jsonl" -> "application/json; charset=UTF-8";
                case "xml" -> "application/xml; charset=UTF-8";
                case "csv" -> "text/csv; charset=UTF-8";
                default -> "text/plain; charset=UTF-8";
            };
            conn.setRequestProperty("Content-Type", contentType);
            if (key != null && !key.isBlank()) {
                conn.setRequestProperty("Authorization", "Bearer " + key.trim());
            }

            byte[] payload = data.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            conn.setFixedLengthStreamingMode(payload.length);
            conn.connect();

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload);
                os.flush();
            }

            int code = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();
            JOptionPane.showMessageDialog(parent,
                    "Sent! Response: " + code + " " + responseMessage);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent,
                    "Error sending data:\n" + e.getMessage());
        } finally {
            if (conn != null) conn.disconnect();
        }
    }
}

