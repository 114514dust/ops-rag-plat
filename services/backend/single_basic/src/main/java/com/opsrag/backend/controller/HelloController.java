package com.opsrag.backend.controller;

import com.opsrag.backend.common.exception.BusinessException;
import com.opsrag.backend.common.response.Result;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "测试接口", description = "测试相关的接口")
@RestController
@RequestMapping("/test")
public class HelloController {
    @Operation(summary = "测试与后端服务器是否连通")
    @GetMapping("/tomcat")
    public Result<String> tomcat() {
        return Result.success("Hello!");
    }
}
