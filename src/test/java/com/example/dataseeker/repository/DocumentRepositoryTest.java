import com.example.dataseeker.model.Document;
import com.example.dataseeker.repository.DocumentRepository;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class DocumentRepositoryTest {

    @Test
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

