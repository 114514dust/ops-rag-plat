package com.opsrag.backend.service.impl;

import com.opsrag.backend.pojo.Entity.KbSyncLog;
import com.opsrag.backend.mapper.KbSyncLogMapper;
import com.opsrag.backend.service.IKbSyncLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 记录 FAQ 数据同步至 AnythingLLM 向量库的记录 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
@Service
public class KbSyncLogServiceImpl extends ServiceImpl<KbSyncLogMapper, KbSyncLog> implements IKbSyncLogService {

}
