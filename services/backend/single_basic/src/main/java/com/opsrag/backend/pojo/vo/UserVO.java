package com.opsrag.backend.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @TableField("account")
    private String account;

    @TableField("real_name")
    private String realName;

    @TableField("phone")
    private String phone;

    /**
     * 0：冻结，1正常，2离职
     */
    @TableField("status")
    private Integer status;

    /**
     * 所属部门
     */
    @TableField("department")
    private String department;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
    /**
     * 0：未删除，1：已经删除
     */
    @TableField("is_deleted")
    private Boolean isDeleted;

}
