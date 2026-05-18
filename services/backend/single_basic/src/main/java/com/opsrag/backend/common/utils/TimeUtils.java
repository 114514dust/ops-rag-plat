package com.opsrag.backend.common.utils;


import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class TimeUtils {
    public static void setUpdateTime(Object obj) {
        LocalDateTime now = LocalDateTime.now();
        try{
            Field f = obj.getClass().getDeclaredField("updateTime");
            if(f != null){
                f.setAccessible(true);
                f.set(obj, now);
            }
        }catch (Exception e) {}
    }
    public static void setCreateTime(Object obj) {
        LocalDateTime now = LocalDateTime.now();
        try{
            Field f = obj.getClass().getDeclaredField("createTime");
            if(f != null){
                f.setAccessible(true);
                f.set(obj, now);
            }
        }catch (Exception e) {}
    }
}
