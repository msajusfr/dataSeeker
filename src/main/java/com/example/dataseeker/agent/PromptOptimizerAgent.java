package com.example.dataseeker.agent;

import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.time.Duration;
import java.util.List;

public class PromptOptimizerAgent {

    public interface Optimizer {
        String optimize(String prompt);
    }

    private final Optimizer optimizer;
    private final McpClient mcpClient;

    public PromptOptimizerAgent(String mcpCommand, String openAiKey) {
        // 1️⃣ Configuration du client MCP (stdio)
        mcpClient = new DefaultMcpClient.Builder()
                .transport(new StdioMcpTransport.Builder()
                        .command(List.of(mcpCommand))
                        .logEvents(true)
                        .build())
                .protocolVersion("2024-11-05")
                .toolExecutionTimeout(Duration.ofSeconds(30))
                .build();

        // 2️⃣ Création du ToolProvider MCP
        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();

        // 3️⃣ Création du modèle OpenAI
        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(openAiKey)
                .build();

        // 4️⃣ Construction du service AI
        optimizer = AiServices.builder(Optimizer.class)
                .chatModel(chatModel)
                .toolProvider(toolProvider)
                .build();
    }

    public String optimize(String prompt) {
        return optimizer.optimize(prompt);
    }

    public void close() {
        try {
            mcpClient.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        PromptOptimizerAgent agent = new PromptOptimizerAgent("java -jar target/optimize-prompt-1.0-SNAPSHOT.jar", System.getenv("OPENAI_API_KEY"));
        try {
            String result = agent.optimize("Améliore ce prompt : 'Bonjour, aide moi.'");
            System.out.println("Résultat optimisé : " + result);
        } finally {
            agent.close();
        }
    }
}




