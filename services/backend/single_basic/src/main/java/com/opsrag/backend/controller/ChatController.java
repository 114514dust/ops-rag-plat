package com.opsrag.backend.controller;

import com.opsrag.backend.common.context.BaseContext;
import com.opsrag.backend.common.exception.BusinessException;
import com.opsrag.backend.common.response.Result;
import com.opsrag.backend.service.ConsultantService;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
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
    @GetMapping("/hello2")
    public Result stream2(@RequestParam(value = "message", defaultValue = "你是谁") String message
    ){
       return Result.success(consultantService.chat(message));
    }
    @CrossOrigin(value = "*")
    @GetMapping("/stream2")
    public Flux<String> chat(@RequestParam(value = "memoryId", defaultValue = "1")String memoryId,@RequestParam(value = "message", defaultValue = "你是谁") String message){
        BaseContext.setUserId(1L);
        Flux<String> result = consultantService.chat(memoryId,message);
        BaseContext.removeUserId();
        return result;
    }
}
