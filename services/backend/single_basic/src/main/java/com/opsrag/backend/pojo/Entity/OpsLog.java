package com.opsrag.backend.pojo.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2026-04-20
 */
@Data
@TableName("ops_log")
public class OpsLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /**
     * 关联userId
     */
    private Long operatorId;

    /**
     * 请求路径
     */
    private String operationUrl;

    /**
     * 方法名字
     */
    private String operationMethod;

    /**
     * 方法参数
     */
    private String operationParams;

    /**
     * 操作描述
     */
    private String operationContent;

    /**
     * 操作模块
     */
    private String operationModule;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;
}
