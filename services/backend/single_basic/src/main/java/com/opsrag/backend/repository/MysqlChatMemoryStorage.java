package com.opsrag.backend.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opsrag.backend.common.Interception.LoginInterceptor;
import com.opsrag.backend.common.context.BaseContext;
import com.opsrag.backend.common.exception.BusinessException;
import com.opsrag.backend.pojo.KbChatLog;
import com.opsrag.backend.service.IKbChatLogService;
import dev.langchain4j.data.message.*;
import dev.langchain4j.model.openai.internal.chat.AssistantMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class MysqlChatMemoryStorage implements ChatMemoryStore {
    @Autowired
    IKbChatLogService chatLogService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        //在数据库中找到所有memory_id等于memoryId的对象
        List<KbChatLog>chatLogs = chatLogService.lambdaQuery()
                .eq(KbChatLog::getMemoryId,Long.parseLong(String.valueOf(memoryId)))
                .eq(KbChatLog::getIsDeleted,false)
                .orderByAsc(KbChatLog::getCreateTime)
                .list();
        log.info("get:{}",chatLogs.toString());
        if(chatLogs==null||chatLogs.isEmpty()){
            return Collections.emptyList();
        }
        List<ChatMessage> chatMessages = chatLogs.stream()
                        .map(log->{
                            String role = log.getRole().toLowerCase();
                            String content = log.getContent();
                            switch (role) {
                                case "user":
                                    return UserMessage.from(content);
                                case "ai":
                                    return AiMessage.from(content);
                                case "system":
                                    return SystemMessage.from(content);
                                default:
                                    return UserMessage.from(content);
                            }
                        }).collect(Collectors.toList());
        log.info("get:{}",chatMessages.toString());
        return chatMessages;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> list) {
        log.info("update:{}",list.toString());
        chatLogService.lambdaUpdate()
                .eq(KbChatLog::getMemoryId,Long.parseLong(String.valueOf(memoryId)))
                .set(KbChatLog::getIsDeleted,true)
                .update();
        List<KbChatLog>newLogs =list.stream().map(m->{
            KbChatLog chatLog = new KbChatLog();
            chatLog.setMemoryId(Long.parseLong(String.valueOf(memoryId)));
            chatLog.setUserId(BaseContext.getUserId());
            chatLog.setCreateTime(LocalDateTime.now());
            String name = m.type().name().toLowerCase();
            log.info(name);
            if(name.equals("system")){
                chatLog.setContent(((SystemMessage) m).text());
            }
            else if(name.equals("user")){//todo这里存储的内容要优化
                chatLog.setContent(((UserMessage) m).contents().toString());
            }
            else{
                chatLog.setContent(((AiMessage) m).text());
            }
            chatLog.setRole(name);
            chatLog.setIsDeleted(false);
            return chatLog;
        }).toList();
        log.info("update:{}",newLogs.toString());
        chatLogService.saveBatch(newLogs);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        /*chatLogService.lambdaUpdate()
                .eq(KbChatLog::getMemoryId,Long.parseLong(String.valueOf(memoryId)))
                .set(KbChatLog::getIsDeleted,true)
                .update();*/
        log.info("{}:会话删除成功",memoryId);
    }

}
