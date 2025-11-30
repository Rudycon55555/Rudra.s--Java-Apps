// src/main/java/com/flowbots/app/AppFrame.java
package com.flowbots.app;

import com.flowbots.core.FlowProject;
import com.flowbots.ui.WorkspacePanel;
import com.flowbots.ui.ToolbarPanel;
import com.flowbots.ui.InspectorPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AppFrame extends JFrame {
    private final WorkspacePanel workspace;
    private final InspectorPanel inspector;
    private final FlowProject project;

    public AppFrame() {
        super("Flow Bots");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        this.project = new FlowProject("Untitled");
        this.workspace = new WorkspacePanel(project);
        this.inspector = new InspectorPanel(project, workspace);

        setLayout(new BorderLayout());
        add(new ToolbarPanel(project, workspace, inspector), BorderLayout.NORTH);
        add(workspace, BorderLayout.CENTER);
        add(inspector, BorderLayout.EAST);

        setIconImage(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/app-icon.png")));
    }
}
