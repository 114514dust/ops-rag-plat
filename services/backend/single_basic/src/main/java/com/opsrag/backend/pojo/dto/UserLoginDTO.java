package com.opsrag.backend.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserLoginDTO {
    @TableField("account")
    private String account;

    @TableField("password")
    private String password;
}
