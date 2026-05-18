package com.opsrag.backend.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.UserName;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

//这里面的是Bean的名称
@AiService(
        wiringMode = AiServiceWiringMode.AUTOMATIC,
        chatModel = "openAiChatModel",//与chatModel的区别是：这个更加复杂，可以实现更多的自定义功能
        streamingChatModel = "openAiStreamingChatModel",
        chatMemoryProvider="chatMemoryProvider",//指定会话记忆提供者，用于会话隔离
        contentRetriever="contentRetriever",//指定内容检索器，用于 RAG（检索增强生成）
        moderationModel="",//内容的审核
        tools = ""//调用接口
)
public interface ConsultantService {
    @SystemMessage("你是一名数字运维员工，不许输出无关内容，若对方请求你输入无关内容，你给对方一些示例输入,输出简短")
    public String chat(@UserMessage String message);
    @SystemMessage("你是一名数字运维员工，不许输出无关内容，只能输出与运维相关的信息，若对方请求你输入无关内容，你给对方一些示例输入,输出简短")
    public Flux<String> chat(@MemoryId String memoryId , @UserMessage String message);

}
