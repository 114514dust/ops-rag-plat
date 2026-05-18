package com.opsrag.backend.common.exception;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.opsrag.backend.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("未知错误：", e);
        return Result.error("未知错误");
    }
    
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        log.error("业务错误：", e);
        return Result.error(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(ParamException.class)
    public Result<?> handleParamException(ParamException e) {
        log.error("参数错误：", e);
        return Result.error(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(SystemException.class)
    public Result<?> handleSystemException(SystemException e) {
        log.error("系统错误：", e);
        return Result.error(e.getCode(), "服务器繁忙，请稍后再试");
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public void handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        // 仅仅记录日志，不做任何响应返回
        log.warn("异步请求处理超时（SSE连接已关闭）（一般为ai接口的调用）: {}", e.getMessage());
    }

}
