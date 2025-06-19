package com.example.dataseeker.controller;

import com.example.dataseeker.agent.MasterAgent;
import com.example.dataseeker.agent.MasterResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgentController {
    private final MasterAgent masterAgent;

    public AgentController(MasterAgent masterAgent) {
        this.masterAgent = masterAgent;
    }

    @PostMapping("/message")
    public MasterResponse handle(@RequestBody String message) {
        return masterAgent.handleText(message);
    }
}
