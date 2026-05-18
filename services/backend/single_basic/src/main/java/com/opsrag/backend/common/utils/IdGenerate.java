package com.opsrag.backend.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class IdGenerate{
    private static final Snowflake snowflake = IdUtil.getSnowflake(1, 1); // workerId, dataCenterId

    public static String generateMemoryId() {
        return String.valueOf(snowflake.nextId());
    }
}
