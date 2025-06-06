package com.example.dataseeker.controller;

import com.example.dataseeker.model.Document;
import com.example.dataseeker.model.DocumentType;
import com.example.dataseeker.service.DocumentService;
import java.util.List;
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
    public String index(Model model) {
        model.addAttribute("documents", service.findAll());
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
    public String search(@RequestParam String query,
                         @RequestParam(value = "type", required = false) List<DocumentType> type,
                         Model model) {
        model.addAttribute("documents", service.search(query, type));
        return "index";
    }
}
