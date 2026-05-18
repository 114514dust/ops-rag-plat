package com.opsrag.backend.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SystemException extends RuntimeException {
    private int code;
    public SystemException(String message) {
        super(message);
        this.code = 500;
    }

}
