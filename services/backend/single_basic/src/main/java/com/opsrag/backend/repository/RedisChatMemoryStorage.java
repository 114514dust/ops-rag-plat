package com.opsrag.backend.repository;

import com.opsrag.backend.common.constent.AiConstent;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RedisChatMemoryStorage implements ChatMemoryStore {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String key = AiConstent.KEY_REDIS_SESSION_MEMORY_ID + memoryId.toString();
        String json = stringRedisTemplate.opsForValue().get(key);
        List<ChatMessage> messages = ChatMessageDeserializer.messagesFromJson(json);
        return messages;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> list) {
        String key = AiConstent.KEY_REDIS_SESSION_MEMORY_ID + memoryId.toString();
        if(stringRedisTemplate.hasKey(key)) {
            stringRedisTemplate.delete(key);
        }
        String json = ChatMessageSerializer.messagesToJson(list);
        stringRedisTemplate.opsForValue().set(key, json);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        String key = AiConstent.KEY_REDIS_SESSION_MEMORY_ID + memoryId.toString();
        if(stringRedisTemplate.hasKey(key)) {
            stringRedisTemplate.delete(key);
        }
    }
}
