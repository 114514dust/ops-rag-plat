package com.opsrag.backend.service;

import dev.langchain4j.data.document.Document;

import java.util.List;

public interface RagSyncService {
    public List<Document> syncDbToVector();
}
