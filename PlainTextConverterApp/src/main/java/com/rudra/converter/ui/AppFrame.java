package com.rudra.converter.ui;

import com.rudra.converter.logic.Converter;
import com.rudra.converter.logic.Sender;
import com.rudra.converter.util.FileUtils;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private final JTextArea inputArea;
    private final JTabbedPane outputTabs;

    public AppFrame() {
        Theme.apply();
        setTitle("Plain Text Converter");
        setSize(900, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Top input area
        inputArea = new JTextArea();
        inputArea.setFont(Theme.uiFont(14));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setText("Name: Alice, Age: 12, City: New York");

        JButton convertBtn = new JButton("Convert");
        convertBtn.setFont(Theme.uiFont(14));
        convertBtn.addActionListener(e -> convertData());

        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Plain English Input"));
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        JPanel convertRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        convertRow.add(convertBtn);
        inputPanel.add(convertRow, BorderLayout.SOUTH);

        // Output tabs
        outputTabs = new JTabbedPane();
        outputTabs.setFont(Theme.uiFont(13));
        JPanel outputsContainer = new JPanel(new BorderLayout());
        outputsContainer.setBorder(BorderFactory.createTitledBorder("Converted Outputs"));
        outputsContainer.add(outputTabs, BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputPanel, outputsContainer);
        split.setResizeWeight(0.35);
        add(split, BorderLayout.CENTER);

        // Menu bar with Help
        JMenuBar menuBar = new JMenuBar();
        JMenu help = new JMenu("Help");
        JMenuItem howTo = new JMenuItem("How to Use");
        howTo.addActionListener(e -> new HowToDialog(this).setVisible(true));
        help.add(howTo);
        menuBar.add(help);
        setJMenuBar(menuBar);

        // Show How-To dialog on first run
        SwingUtilities.invokeLater(() -> new HowToDialog(this).setVisible(true));
    }

    private void convertData() {
        String input = inputArea.getText();
        outputTabs.removeAll();

        addOutputTab("JSON", Converter.toJson(input));
        addOutputTab("XML", Converter.toXml(input));
        addOutputTab("CSV", Converter.toCsv(input));
        addOutputTab("JSONL", Converter.toJsonl(input));
    }

    private void addOutputTab(String format, String data) {
        JTextArea area = new JTextArea(data);
        area.setEditable(false);
        area.setFont(Theme.uiFont(13));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JButton copyBtn = new JButton("Copy Data");
        copyBtn.addActionListener(e -> {
            Toolkit.getDefaultToolkit().getSystemClipboard()
                   .setContents(new java.awt.datatransfer.StringSelection(data), null);
            JOptionPane.showMessageDialog(this, format + " copied to clipboard.");
        });

        JButton downloadBtn = new JButton("Download Data");
        downloadBtn.addActionListener(e -> {
            boolean ok = FileUtils.saveToFile(format.toLowerCase(), data, this);
            if (ok) JOptionPane.showMessageDialog(this, "Saved " + format + " file.");
        });

        JButton sendBtn = new JButton("Send Data");
        sendBtn.addActionListener(e -> Sender.sendData(format, data, this));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(copyBtn);
        buttons.add(downloadBtn);
        buttons.add(sendBtn);

        JPanel panel = new JPanel(new BorderLayout(8,8));
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        outputTabs.addTab(format, panel);
    }
}

