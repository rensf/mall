package com.sys.gateway.config;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rensf
 * @date 2022/3/23 18:05
 */
@Configuration
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

    private static final String API_URI = "/v3/api-docs";

    @Resource
    private GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<RouteDefinition> routes = gatewayProperties.getRoutes();
        for (RouteDefinition route : routes) {
            resources.add(createSwaggerResource(route.getId()));
        }
        return resources;
    }

    private SwaggerResource createSwaggerResource(String name) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation("/" + name + API_URI);
        return swaggerResource;
    }

    @Bean
    public UiConfiguration uiConfiguration() {
        // 隐藏UI上的Models模块
        return UiConfigurationBuilder.builder()
                .defaultModelsExpandDepth(-1)
                .build();
    }

}
