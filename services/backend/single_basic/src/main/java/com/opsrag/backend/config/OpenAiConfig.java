package com.opsrag.backend.config;

import com.opsrag.backend.repository.MysqlChatMemoryStorage;
import com.opsrag.backend.repository.RedisChatMemoryStorage;
import com.opsrag.backend.service.ConsultantService;
import com.opsrag.backend.service.RagSyncService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenAiConfig {

    @Autowired
    private RagSyncService ragSyncService;
    @Autowired
    private RedisChatMemoryStorage redisChatMemoryStorage;
    @Value("${ai.message.size}")
    private String messageSize;


    @Bean
    ChatMemoryProvider ChatMemoryProvider() {
        ChatMemoryProvider chatMemoryProvider=new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .id(memoryId)//id值
                        .chatMemoryStore(redisChatMemoryStorage)
                        .maxMessages(Integer.valueOf(messageSize))//最大会话记录数
                        .build();
            }
        };
        return chatMemoryProvider;
    }
    @Bean
    ContentRetriever contentRetriever(EmbeddingStore embeddingStore) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .build();
    }
    @Bean
    EmbeddingStore embeddingStore() {
        List<Document> documents = ragSyncService.syncDbToVector();
        //2.构建向量数据库操作对象  操作的是内存版本的向量数据库
        InMemoryEmbeddingStore embeddingStore=new InMemoryEmbeddingStore();
        EmbeddingStoreIngestor embeddingStoreIngestor=EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .build();
        embeddingStoreIngestor.ingest(documents);
        return embeddingStore;
    }

}
