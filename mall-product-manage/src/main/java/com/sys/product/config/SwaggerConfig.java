package com.sys.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author rensf
 * @date 2021/4/30 11:20
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket getDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("产品管理")
                .description("产品管理相关接口描述")
                .version("0.0.1")
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sys.product.controller"))
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
