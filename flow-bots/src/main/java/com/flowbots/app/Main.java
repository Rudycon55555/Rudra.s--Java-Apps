// src/main/java/com/flowbots/app/Main.java
package com.flowbots.app;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatLightLaf.setup();
            new AppFrame().setVisible(true);
        });
    }
}
