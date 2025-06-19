package com.example.dataseeker.tool;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.stereotype.Component;

/**
 * Tool that optimizes a prompt written in Markdown using an LLM.
 */
@Component
public class PromptOptimizerTool {

    private final ChatLanguageModel model;

    public PromptOptimizerTool() {
        this(OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .build());
    }

    public PromptOptimizerTool(ChatLanguageModel model) {
        this.model = model;
    }

    @Tool("Optimize a Markdown prompt")
    public String optimize(String prompt) {
        String instruction = "Improve the following prompt and answer only with the optimized Markdown";
        return model.generate(instruction + "\n\n" + prompt);
    }
}
