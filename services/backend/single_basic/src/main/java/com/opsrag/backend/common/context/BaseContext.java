package com.opsrag.backend.common.context;

import java.math.BigInteger;

public class BaseContext {
    private static final ThreadLocal<Long> context = new ThreadLocal<Long>();

    public static void setUserId(Long userId) {
        context.set(userId);
    }
    public static void removeUserId() {
        context.remove();
    }
    public static Long getUserId() {
        return context.get();
    }
}
