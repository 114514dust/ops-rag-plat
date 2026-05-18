package com.opsrag.backend.service.impl;

import com.opsrag.backend.mapper.OpsLogMapper;
import com.opsrag.backend.pojo.Entity.OpsLog;
import com.opsrag.backend.service.IOpsLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
@Service
public class OpsLogServiceImpl extends ServiceImpl<OpsLogMapper, OpsLog> implements IOpsLogService {

}
