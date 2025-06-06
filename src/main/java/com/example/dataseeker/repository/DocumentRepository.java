package com.example.dataseeker.repository;

import com.example.dataseeker.model.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DocumentRepository {

    private final ConcurrentHashMap<Long, Document> store = new ConcurrentHashMap<>();
    private long idSequence = 1L;

    public List<Document> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Document> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Document save(Document doc) {
        if (doc.getId() == null) {
            doc.setId(idSequence++);
        }
        store.put(doc.getId(), doc);
        return doc;
    }
}
