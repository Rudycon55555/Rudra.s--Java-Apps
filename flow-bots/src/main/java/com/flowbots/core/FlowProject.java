// src/main/java/com/flowbots/core/FlowProject.java
package com.flowbots.core;

import com.flowbots.components.Builtins;
import com.flowbots.components.FlowComponent;

import java.util.ArrayList;
import java.util.List;

public class FlowProject {
    private final String name;
    private final FlowGraph graph;

    public FlowProject(String name) {
        this.name = name;
        this.graph = new FlowGraph();
        // preload builtin types for palette
        Builtins.init();
    }

    public String getName() { return name; }
    public FlowGraph getGraph() { return graph; }

    public List<String> getPalette() {
        return Builtins.getTypes();
    }

    public FlowComponent createComponent(String type, int x, int y) {
        FlowComponent c = Builtins.instantiate(type);
        c.setPosition(x, y);
        graph.addComponent(c);
        return c;
    }
}
