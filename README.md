# dataSeeker

This project now showcases a minimal A2A (agent to agent) architecture built with Spring Boot.
Three agents collaborate:

1. **Master agent** – accepts text from the UI.
2. **Prompt optimizer agent** – connects to `PromptOptimizerTool` via the MCP protocol to improve the prompt.
3. **Graph creator agent** – receives the optimized prompt and generates graph data with `GraphCreatorTool` and a LLM.

The web UI contains a text area for each agent. Typing text in the master agent area and pressing `Enter` sends the text to the server which forwards it through the two slave agents. The response is displayed in the text areas of the slaves.

Run the application with:

```bash
mvn spring-boot:run
```
