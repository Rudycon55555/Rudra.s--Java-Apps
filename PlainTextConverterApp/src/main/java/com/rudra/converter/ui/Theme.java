package com.rudra.converter.ui;

import javax.swing.*;
import java.awt.*;

public class Theme {
    public static void apply() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        // Subtle modern palette
        UIManager.put("control", new Color(250, 251, 253));
        UIManager.put("info", new Color(250, 251, 253));
        UIManager.put("nimbusBase", new Color(45, 98, 160));
        UIManager.put("nimbusBlueGrey", new Color(200, 205, 210));
        UIManager.put("text", new Color(20, 20, 20));
        UIManager.put("nimbusLightBackground", new Color(245, 247, 250));
        UIManager.put("Table.gridColor", new Color(230, 230, 230));
        UIManager.put("Button.background", new Color(60, 120, 200));
        UIManager.put("Button.foreground", Color.WHITE);
    }

    public static Font uiFont(int size) {
        return new Font("Segoe UI", Font.PLAIN, size);
    }
}
