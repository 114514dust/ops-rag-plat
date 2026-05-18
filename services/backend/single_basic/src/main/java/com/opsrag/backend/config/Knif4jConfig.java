package com.opsrag.backend.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knif4jConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OpsRAG 后端 API 接口文档")
                        .version("1.0.0")
                        .description("Spring Boot 3 + Knife4j 接口文档")
                        .contact(new Contact()
                                .name("开发团队")
                                .email("dev@opsrag.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));

    }

    // 可选：分组配置，按路径规则将接口分组显示
    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("全部接口")
                .pathsToMatch("/**")
                .build();
    }

   /* @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("聊天")
                .pathsToMatch("/chat/**")
                .build();
    }*/
}
