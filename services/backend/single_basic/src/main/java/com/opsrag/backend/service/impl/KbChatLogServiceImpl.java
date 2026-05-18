package com.opsrag.backend.service.impl;

import com.opsrag.backend.pojo.Entity.KbChatLog;
import com.opsrag.backend.mapper.KbChatLogMapper;
import com.opsrag.backend.service.IKbChatLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户对话日志表 服务实现类
 * </p>
 *
 * @author author
 * @since 2026-04-23
 */
@Service
public class KbChatLogServiceImpl extends ServiceImpl<KbChatLogMapper, KbChatLog> implements IKbChatLogService {

}
