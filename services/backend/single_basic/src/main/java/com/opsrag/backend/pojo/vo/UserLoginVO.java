package com.opsrag.backend.pojo.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private Long userId;
    private String token;
    private String role;
}
