package com.example.dataseeker.agent;

import com.example.dataseeker.tool.GraphCreatorTool;
import org.springframework.stereotype.Component;

@Component
public class GraphCreatorAgent {
    private final GraphCreatorTool tool;

    public GraphCreatorAgent(GraphCreatorTool tool) {
        this.tool = tool;
    }

    public String createGraph(String prompt) {
        return tool.createGraph(prompt);
    }
}
