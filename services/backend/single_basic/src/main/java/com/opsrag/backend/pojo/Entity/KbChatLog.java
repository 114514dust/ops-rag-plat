package com.opsrag.backend.pojo.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户对话日志表
 * </p>
 *
 * @author author
 * @since 2026-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kb_chat_log")
public class KbChatLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，消息id
     */
    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;

    /**
     * 会话id，同一组会话共用一个id
     */
    private Long memoryId;

    /**
     * 对话用户的id
     */
    private Long userId;

    /**
     * 角色标识(user/assistant)
     */
    private String role;

    /**
     * 对话内容
     */
    private String content;

    /**
     * 是否命中知识库（true:命中）
     */
    private Boolean isHit;

    /**
     * 用户反馈（true：点赞，false：点踩）(默认为点赞)
     */
    private Boolean feedback;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否被删除(true:被删除)
     */
    private Boolean isDeleted;


}
