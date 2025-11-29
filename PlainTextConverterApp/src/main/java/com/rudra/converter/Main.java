package com.rudra.converter;

import com.rudra.converter.ui.AppFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            AppFrame frame = new AppFrame();
            frame.setVisible(true);
        });
    }
}
