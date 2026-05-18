package com.opsrag.backend.config;

import com.opsrag.backend.common.Interception.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    LoginInterceptor loginInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      /* registry.addInterceptor(loginInterceptor)
               .addPathPatterns("/**")
               .excludePathPatterns("/hello")
               .excludePathPatterns("/user/login");*/
   }
}
