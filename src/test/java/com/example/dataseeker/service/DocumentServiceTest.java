package com.example.dataseeker.service;

import com.example.dataseeker.model.Document;
import com.example.dataseeker.model.DocumentType;
import com.example.dataseeker.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentServiceTest {

    private DocumentService service;

    @BeforeEach
    void setup() {
        DocumentRepository repository = new DocumentRepository();
        service = new DocumentService(repository);
        // don't call init to avoid OpenAI init
        service.save(new Document(null, "Java", DocumentType.PDF, "/java", "Java content"));
        service.save(new Document(null, "Python", DocumentType.HTML, "/py", "Python content"));
    }

    @Test
    void searchByQueryAndType() {
        List<Document> result = service.search("Java", List.of(DocumentType.PDF));
        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getName());
    }

    @Test
    void searchWithoutFiltersReturnsAll() {
        List<Document> result = service.search("", null);
        assertEquals(2, result.size());
    }

    @Test
    void askForLinksReturnsNullWhenNoOpenAiService() {
        assertNull(service.askForLinks("java", List.of(DocumentType.PDF)));
    }
}
