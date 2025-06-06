package com.example.dataseeker.service;

import com.example.dataseeker.model.Document;
import com.example.dataseeker.model.DocumentType;
import com.example.dataseeker.repository.DocumentRepository;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Locale;

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
        // Add a couple of sample documents for demonstration
        repository.save(new Document(null, "Sample PDF", DocumentType.PDF, "/sample.pdf", "Sample pdf content"));
        repository.save(new Document(null, "Sample HTML", DocumentType.HTML, "/sample.html", "Sample html content"));

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

    public List<Document> search(String query, List<DocumentType> types) {
        return repository.findAll().stream()
                .filter(d -> query == null || query.isBlank() ||
                        (d.getName() != null && d.getName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) ||
                        (d.getContent() != null && d.getContent().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))))
                .filter(d -> types == null || types.isEmpty() || types.contains(d.getType()))
                .toList();
    }
}
