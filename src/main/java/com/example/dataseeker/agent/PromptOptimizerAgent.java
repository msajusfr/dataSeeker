package com.example.dataseeker.agent;

import com.example.dataseeker.tool.PromptOptimizerTool;
import org.springframework.stereotype.Component;

@Component
public class PromptOptimizerAgent {
    private final PromptOptimizerTool tool;

    public PromptOptimizerAgent(PromptOptimizerTool tool) {
        this.tool = tool;
    }

    public String optimize(String prompt) {
        return tool.optimize(prompt);
    }
}
