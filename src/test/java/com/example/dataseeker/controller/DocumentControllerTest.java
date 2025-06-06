package com.example.dataseeker.controller;

import com.example.dataseeker.model.Document;
import com.example.dataseeker.model.DocumentType;
import com.example.dataseeker.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DocumentController.class)
class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService service;

    @Test
    void getDocumentReturnsDocument() throws Exception {
        Document doc = new Document(1L, "test", DocumentType.PDF, "/d", "c");
        when(service.findById(1L)).thenReturn(Optional.of(doc));

        mockMvc.perform(get("/documents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    void addDocumentReturnsSavedDocument() throws Exception {
        Document doc = new Document(1L, "test", DocumentType.HTML, "/d", "c");
        when(service.save(any(Document.class))).thenReturn(doc);

        String json = "{\"name\":\"test\",\"type\":\"HTML\",\"url\":\"/d\",\"content\":\"c\"}";
        mockMvc.perform(post("/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
