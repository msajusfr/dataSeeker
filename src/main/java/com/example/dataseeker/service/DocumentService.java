package com.example.dataseeker.service;

import com.example.dataseeker.model.Document;
import com.example.dataseeker.repository.DocumentRepository;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository repository;
    private OpenAiChatModel openAiService;

    @Value("${OPENAI_API_KEY:}")
    private String apiKey;

    @Value("${OPENAI_MODEL:gpt-3.5-turbo}")
    private String model;

    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if (apiKey != null && !apiKey.isEmpty()) {
            openAiService = OpenAiChatModel.builder()
                    .apiKey(apiKey)
                    .modelName(model)
                    .build();
        }
    }

    public Document save(Document doc) {
        return repository.save(doc);
    }

    public Optional<Document> findById(Long id) {
        return repository.findById(id);
    }

    public List<Document> findAll() {
        return repository.findAll();
    }

    public String search(String query) {
        if (openAiService == null) {
            return "OpenAI not configured";
        }
        // simple prompt for now
        String prompt = "Find relevant information about: " + query;
        return openAiService.generate(prompt).content();
    }
}
