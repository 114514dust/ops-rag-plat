package com.opsrag.backend.pojo.vo;

import lombok.Data;

@Data
public class LoginVO {
    private Long userId;
    private String token;
    private String role;
}
