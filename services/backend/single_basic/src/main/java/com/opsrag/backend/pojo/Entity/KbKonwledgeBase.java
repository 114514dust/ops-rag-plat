package com.opsrag.backend.pojo.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 知识库配置表
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kb_konwledge_base")
public class KbKonwledgeBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "kb_id", type = IdType.AUTO)
    private Long kbId;

    @TableField("kb_name")
    private String kbName;

    /**
     * 知识库描述
     */
    @TableField("kb_desc")
    private String kbDesc;

    @TableField("rag_tool")
    private String ragTool;

    /**
     * 同步状态：0 = 未同步 1 = 已同步至向量库
     */
    @TableField("sync_status")
    private Integer syncStatus;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("updatetime")
    private LocalDateTime updatetime;

    @TableField("is_deleted")
    private Boolean isDeleted;


}
