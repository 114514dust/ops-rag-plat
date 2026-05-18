package com.opsrag.backend.common.aop;

import com.opsrag.backend.common.context.BaseContext;
import com.opsrag.backend.common.utils.TimeUtils;

import com.opsrag.backend.pojo.Entity.OpsLog;
import com.opsrag.backend.service.impl.OpsLogServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class GeneralAspect {
    @Pointcut("@annotation(com.opsrag.backend.common.aop.CreateAop)")
    public void createAop() {
    }
    @Before("createAop()")
    public void beforeCreate(final JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                TimeUtils.setCreateTime(arg);  // 设置创建时间
                TimeUtils.setUpdateTime(arg);  // 设置更新时间
            }
        }
    }
    @Pointcut("@annotation(com.opsrag.backend.common.aop.UpdateAop)")
    public void updateAop() {
    }
    @Before("updateAop()")
    public void beforeUpdate(final JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                TimeUtils.setUpdateTime(arg);  // 设置更新时间
            }
        }
    }
    @Pointcut("@annotation(com.opsrag.backend.common.aop.LogAop)")
    public void logAop() {}
    @Resource
    private OpsLogServiceImpl opsLogService;
    @AfterReturning("logAop()")
    public void beforeLog(final JoinPoint joinPoint) {
        try {
            OpsLog opsLog = new OpsLog();
            Long userId = BaseContext.getUserId();
            //设置id
            opsLog.setOperatorId(userId);
            //获取请求
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = null;
            String requestUrl="";
            String requestMethod="";
            if(attributes!=null){//设置url和method
                request = attributes.getRequest();
                requestUrl = request.getRequestURI();
                requestMethod = request.getMethod();

                opsLog.setOperationUrl(requestUrl);
                opsLog.setOperationMethod(requestMethod);
            }
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            LogAop logAop = method.getAnnotation(LogAop.class);
            String operationContent = logAop.content();
            String operationModule=logAop.module();
            //设置content，module，time
            opsLog.setOperationContent(operationContent==null?"":operationContent);
            opsLog.setOperationModule(operationModule==null?"":operationModule);
            opsLog.setOperationTime(LocalDateTime.now());

            //方法参数
            Object[] args = joinPoint.getArgs();
            String params = handleParams(args);
            opsLog.setOperationMethod(params==null?"":params);
            log.info("日志信息：{}",opsLog.toString());
            opsLogService.save(opsLog);
        }
        catch (Exception e) {
            log.info("收集日志失败：{}",e.getMessage());
        }
    }
    private String handleParams(Object[]args){
        if (args == null || args.length == 0) {
            return "[]";
        }
        Map<String,Object> mp =new HashMap<>();
        int i=1;
        for(Object arg:args){
            if(arg!=null){
                String param = arg.toString();
                mp.put("param "+(i++),param);
            }
        }
        return mp.toString();
    }

}
