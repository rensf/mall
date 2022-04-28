package com.sys.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author rensf
 * @date 2021/5/8 16:48
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket getDocket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("订单管理")
                .description("订单管理相关接口描述")
                .version("0.0.1")
                .build();
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sys.order.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/product");
    }

}
