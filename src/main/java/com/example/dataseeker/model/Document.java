package com.example.dataseeker.model;

public class Document {
    private Long id;
    private String name;
    private DocumentType type;
    private String url;
    private String content;

    public Document() {
    }

    public Document(Long id, String name, DocumentType type, String url, String content) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
