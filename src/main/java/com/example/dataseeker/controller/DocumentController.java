package com.example.dataseeker.controller;

import com.example.dataseeker.model.Document;
import com.example.dataseeker.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/documents/{id}")
    @ResponseBody
    public Document getDocument(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }

    @PostMapping("/documents")
    @ResponseBody
    public Document addDocument(@RequestBody Document doc) {
        return service.save(doc);
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        String answer = service.search(query);
        model.addAttribute("answer", answer);
        return "index";
    }
}
