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

import java.util.ArrayList;
import java.util.List;

/**
 * @author rensf
 * @date 2022/3/23 18:05
 */
@Configuration
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

    private GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
//        List<RouteDefinition> routes = gatewayProperties.getRoutes();
//        for (RouteDefinition route : routes) {
//            resources.add(createSwaggerResource(route.getId(), route.getPredicates().get(1).getName()));
//        }
        resources.add(createSwaggerResource("product-manage", "/product/v3/api-docs"));
        resources.add(createSwaggerResource("system-manage", "/system/v3/api-docs"));
        return resources;
    }

    private SwaggerResource createSwaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
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
