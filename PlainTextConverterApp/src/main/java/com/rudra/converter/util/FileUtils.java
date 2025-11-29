package com.rudra.converter.util;

import javax.swing.*;
import java.awt.Component;   // FIX: added import
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    /**
     * Save data to a file using a JFileChooser. Returns true if saved.
     */
    public static boolean saveToFile(String extension, String data, Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("output." + extension));
        int res = chooser.showSaveDialog(parent);
        if (res != JFileChooser.APPROVE_OPTION) return false;
        File file = chooser.getSelectedFile();
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(data);
            writer.flush();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent,
                    "Error saving file:\n" + e.getMessage());
            return false;
        }
    }
}

