package com.opsrag.backend.common.Interception;

import com.opsrag.backend.common.constent.JwtConstant;
import com.opsrag.backend.common.context.BaseContext;
import com.opsrag.backend.common.exception.BusinessException;
import com.opsrag.backend.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.ClientResourcesBuilderCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtils jwtUtils;
    @Autowired
    private ClientResourcesBuilderCustomizer clientResourcesBuilderCustomizer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterception preHandle");
        //获取request的header中携带的token
        String token = request.getHeader("token");
        log.info("请求路径：{}，token:{}", request.getRequestURI(), token);
        if (token == null) {
            log.info("token为空");
            throw new BusinessException("请登录账号");
        }
        if(!jwtUtils.validateToken(token)){
            throw new BusinessException("请重新登录");
        }

        //TODO
        Claims claims = jwtUtils.parseToken(token);
        BaseContext.setUserId(claims.get(JwtConstant.USER,Long.class));
        log.info("设置用户id为：{}",claims.get(JwtConstant.USER));
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        BaseContext.removeUserId();
    }
}
