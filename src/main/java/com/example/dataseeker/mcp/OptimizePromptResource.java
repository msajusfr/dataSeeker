package com.example.dataseeker.mcp;

import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OptimizePromptResource {

    @Tool(description = "Optimize a prompt for clarity and performance")
    public String optimizePrompt(@ToolArg(description = "Prompt to optimize") String prompt) {
        return prompt.trim().replaceAll("\\s+", " ");
    }
}
