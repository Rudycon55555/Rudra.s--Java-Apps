package com.rudra.converter.ui;

import javax.swing.*;
import java.awt.*;

public class HowToDialog extends JDialog {
    public HowToDialog(JFrame parent) {
        super(parent, "How to Use", true);
        setSize(520, 420);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(12, 12));

        JTextArea instructions = new JTextArea(
            "Welcome to Plain Text Converter!\n\n" +
            "1. Type plain English or simple key/value lines in the top box.\n" +
            "   Examples:\n" +
            "     Name: Alice, Age: 12, City: New York\n" +
            "     or\n" +
            "     name=Bob\n" +
            "     score: 42\n\n" +
            "2. Press Convert to generate JSON, XML, CSV, and JSONL.\n" +
            "3. For each format you can Copy, Download, or Send the data.\n" +
            "   - Copy copies to clipboard.\n" +
            "   - Download saves a file with the appropriate extension.\n" +
            "   - Send posts the data to an API endpoint (optional key).\n\n" +
            "Tips:\n" +
            "- Use commas or new lines to separate fields.\n" +
            "- Use ':' or '=' between key and value.\n" +
            "- The app works offline; Send requires network access.\n\n" +
            "Enjoy converting plain text into structured data!"
        );
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setFont(Theme.uiFont(13));
        instructions.setBackground(new Color(250, 251, 253));
        instructions.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton skipBtn = new JButton("Skip");
        skipBtn.addActionListener(e -> dispose());
        bottom.add(skipBtn);

        add(new JScrollPane(instructions), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
