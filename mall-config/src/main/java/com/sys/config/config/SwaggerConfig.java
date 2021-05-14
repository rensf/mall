package com.sys.config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * @author rensf
 * @date 2021/5/12 18:04
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket getDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("配置管理")
                .description("配置管理相关接口描述")
                .version("0.0.1")
                .build();
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sys.config.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public UiConfiguration uiConfiguration() {
        // 隐藏UI上的Models模块
        return UiConfigurationBuilder.builder()
                .defaultModelsExpandDepth(-1)
                .build();
    }
}
