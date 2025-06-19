package com.example.dataseeker.agent;

import com.example.dataseeker.tool.PromptOptimizerTool;
import dev.langchain4j.messenger.console.StdioMessenger;
import dev.langchain4j.mcp.McpClient;
import dev.langchain4j.agent.tool.Tools;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class PromptOptimizerAgent {

    private final PromptOptimizerTool tool;
    private McpClient client;

    public PromptOptimizerAgent(PromptOptimizerTool tool) {
        this.tool = tool;
    }

    @PostConstruct
    public void startClient() {
        Tools tools = Tools.builder().add(tool).build();
        client = McpClient.builder()
                .tools(tools)
                .messenger(new StdioMessenger())
                .build();
        client.start();
    }

    public String optimize(String prompt) {
        return tool.optimize(prompt);
    }
}
