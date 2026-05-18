package com.opsrag.backend.config;

import com.opsrag.backend.common.Interception.LoginInterceptor;
import jakarta.annotation.Resource;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    LoginInterceptor loginInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
               .addPathPatterns("/user/**")
               .excludePathPatterns("/user/login");
   }


}
