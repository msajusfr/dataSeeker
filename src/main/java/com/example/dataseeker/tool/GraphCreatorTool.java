package com.example.dataseeker.tool;

import org.springframework.stereotype.Component;

/**
 * Simulates a tool that creates graph data using a LLM.
 */
@Component
public class GraphCreatorTool {
    public String createGraph(String prompt) {
        // in real implementation, connect via MCP and use LLM
        return "Graph for prompt: " + prompt;
    }
}
