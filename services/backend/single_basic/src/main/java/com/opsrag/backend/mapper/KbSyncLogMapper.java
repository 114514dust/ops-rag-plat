package com.opsrag.backend.mapper;

import com.opsrag.backend.pojo.Entity.KbSyncLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 记录 FAQ 数据同步至 AnythingLLM 向量库的记录 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
@Mapper
public interface KbSyncLogMapper extends BaseMapper<KbSyncLog> {

}
