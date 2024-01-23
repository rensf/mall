package com.sys.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author rensf
 * @date 2022/3/30
 */
@Component
public class SwaggerHeaderFilter extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        return null;
    }

}
