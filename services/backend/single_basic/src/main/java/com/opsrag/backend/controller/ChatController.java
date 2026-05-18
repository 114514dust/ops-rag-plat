package com.opsrag.backend.controller;

import com.opsrag.backend.common.context.BaseContext;
import com.opsrag.backend.common.exception.BusinessException;
import com.opsrag.backend.common.response.Result;
import com.opsrag.backend.common.utils.IdGenerate;
import com.opsrag.backend.service.ConsultantService;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ConsultantService consultantService;

    @Operation(summary = "一次性输出所有文本", description = "输入用户信息，输出所有文本（测试用）")
    @GetMapping("/static")
    public Result static_chat(@RequestParam(value = "message", defaultValue = "你是谁") String message
    ){
       return Result.success(consultantService.chat(message));
    }
    @Operation(summary = "流式输出文本", description = "输入用户信息，以及会话id，输出所有文本")
    @CrossOrigin(value = "*")
    @GetMapping("/stream")
    public Flux<String> flux_chat(@RequestParam(value = "memoryId", defaultValue = "1")String memoryId,@RequestParam(value = "message", defaultValue = "你是谁") String message){
        BaseContext.setUserId(1L);
        Flux<String> result = consultantService.chat(memoryId,message);
        BaseContext.removeUserId();
        return result;
    }
    @Operation(summary  = "获取会话id",description = "新建会话的时候，要获取对应的会话id，然后再将会话id和用户信息一起发送")
    @GetMapping("/memoryid")
    public Result getMemoryId(){
        return Result.success(IdGenerate.generateMemoryId());
    }
}
