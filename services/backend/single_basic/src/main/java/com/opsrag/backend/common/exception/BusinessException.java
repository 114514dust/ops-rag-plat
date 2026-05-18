package com.opsrag.backend.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 业务异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private int code;
    
    public BusinessException(String message) {
        super(message);
        this.code = 1000;
    }
    
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}
