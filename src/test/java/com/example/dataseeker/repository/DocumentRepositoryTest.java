package com.example.dataseeker.repository;

import com.example.dataseeker.model.Document;
import com.example.dataseeker.model.DocumentType;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import com.example.dataseeker.repository.DocumentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

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
  
    void concurrentSavesGenerateSequentialIds() throws Exception {
        DocumentRepository repo = new DocumentRepository();
        int tasks = 50;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Document>> futures = new CopyOnWriteArrayList<>();
        for (int i = 0; i < tasks; i++) {
            futures.add(executor.submit(() -> repo.save(new Document())));
        }
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));

        Set<Long> ids = new HashSet<>();
        for (Future<Document> f : futures) {
            ids.add(f.get().getId());
        }
        assertEquals(tasks, ids.size());
        for (long i = 1; i <= tasks; i++) {
            assertTrue(ids.contains(i));
        }
    }
}
