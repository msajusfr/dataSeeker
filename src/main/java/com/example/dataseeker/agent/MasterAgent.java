package com.example.dataseeker.agent;

import org.springframework.stereotype.Component;

@Component
public class MasterAgent {
    private final PromptOptimizerAgent optimizerAgent;
    private final GraphCreatorAgent graphAgent;

    public MasterAgent(PromptOptimizerAgent optimizerAgent, GraphCreatorAgent graphAgent) {
        this.optimizerAgent = optimizerAgent;
        this.graphAgent = graphAgent;
    }

    public MasterResponse handleText(String text) {
        String optimized = optimizerAgent.optimize(text);
        String graph = graphAgent.createGraph(optimized);
        return new MasterResponse(optimized, graph);
    }
}
