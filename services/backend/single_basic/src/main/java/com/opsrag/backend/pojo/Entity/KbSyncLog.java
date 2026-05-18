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
 * 记录 FAQ 数据同步至 AnythingLLM 向量库的记录
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kb_sync_log")
public class KbSyncLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sync_id", type = IdType.AUTO)
    private Long syncId;

    @TableField("kb_id")
    private Long kbId;

    /**
     * 同步 FAQ 数量
     */
    @TableField("sync_count")
    private Integer syncCount;

    /**
     * 同步结果：1成功 / 0失败
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
