package com.example.dataseeker.tool;

import org.springframework.stereotype.Component;

/**
 * Simulates a tool that optimizes prompts via MCP protocol.
 */
@Component
public class PromptOptimizerTool {
    public String optimize(String prompt) {
        // in real implementation, connect via MCP
        return "[Optimized] " + prompt;
    }
}
