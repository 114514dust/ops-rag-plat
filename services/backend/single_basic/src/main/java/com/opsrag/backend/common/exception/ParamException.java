package com.opsrag.backend.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ParamException extends RuntimeException {
    private int code;
    public ParamException(String message) {
        super(message);
        this.code = 400;
    }
}
