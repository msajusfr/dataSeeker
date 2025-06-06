package com.example.dataseeker.repository;

import com.example.dataseeker.model.Document;
import com.example.dataseeker.model.DocumentType;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DocumentRepositoryTest {

    @Test
    void saveAndFindById() {
        DocumentRepository repository = new DocumentRepository();
        Document doc = new Document(null, "test", DocumentType.PDF, "/test.pdf", "content");
        repository.save(doc);
        assertNotNull(doc.getId());

        Optional<Document> found = repository.findById(doc.getId());
        assertTrue(found.isPresent());
        assertEquals("test", found.get().getName());
    }

    @Test
    void findAllReturnsSavedDocuments() {
        DocumentRepository repository = new DocumentRepository();
        repository.save(new Document(null, "d1", DocumentType.HTML, "/d1", "c1"));
        repository.save(new Document(null, "d2", DocumentType.PDF, "/d2", "c2"));

        assertEquals(2, repository.findAll().size());
    }
}
