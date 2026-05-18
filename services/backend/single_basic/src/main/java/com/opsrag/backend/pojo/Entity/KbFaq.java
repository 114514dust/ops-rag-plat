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
 * 
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kb_faq")
public class KbFaq implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "faq_id", type = IdType.AUTO)
    private Long faqId;

    /**
     * 运维问题
     */
    @TableField("question")
    private String question;
    /**
    * 相似提问
    * */
    @TableField("similar_questions")
    private String similarQuestions;
    /**
     * 解决方案
     */
    @TableField("solution")
    private String solution;

    /**
     * 检索关键词
     */
    @TableField("keywords")
    private String keywords;

    /**
     * 问题分类：账号类 / 系统类 / 硬件类
     */
    @TableField("faq_type")
    private String faqType;

    /**
     * 来源：初始录入 / 人工工单补充 / 其他
     */
    @TableField("source")
    private String source;
    /**
     *向量库id
     * */
    @TableField("vector_id")
    private String vectorId;
    /**
     * 状态：0 = 禁用 1 = 启用（生效于 RAG）
     */
    @TableField("status")
    private Boolean status;

    /**
     * 关联工单 ID（人工完善的工单）
     */
    @TableField("relate_work_order_id")
    private Long relateWorkOrderId;
    /**
     * 访问量
     * */
    @TableField("view_count")
    private Long viewCount;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("updatetime")
    private LocalDateTime updatetime;

    @TableField("is_deleted")
    private Boolean isDeleted;


}
