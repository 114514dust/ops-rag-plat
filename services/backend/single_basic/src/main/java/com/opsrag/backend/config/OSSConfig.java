package com.opsrag.backend.config;

import com.opsrag.backend.common.constent.OSSConstent;
import com.opsrag.backend.common.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OSSConfig {

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(OSSConstent ossConstent){
        log.info("开始创建阿里云文件上传工具类对象：{}",ossConstent);
        return new AliOssUtil(ossConstent.getEndpoint(),
                ossConstent.getAccessKeyId(),
                ossConstent.getAccessKeySecret(),
                ossConstent.getBucketName());
    }
}
