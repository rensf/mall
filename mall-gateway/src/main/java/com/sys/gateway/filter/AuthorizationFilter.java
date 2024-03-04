package com.sys.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.nimbusds.jose.JWSObject;
import com.sys.common.core.constant.SecurityConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;

/**
 * @author rensf
 * @date 2023/11/7
 */
@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered {

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        if (StrUtil.isBlank(token) || !StrUtil.startWithIgnoreCase(token, SecurityConstants.JWT_PREFIX)) {
            return chain.filter(exchange);
        }

        token = StrUtil.replaceIgnoreCase(token, SecurityConstants.JWT_PREFIX, Strings.EMPTY);
        String payload = StrUtil.toString(JWSObject.parse(token).getPayload());

        request = exchange.getRequest().mutate()
            .header(SecurityConstants.JWT_PAYLOAD_KEY, URLEncoder.encode(payload, "UTF-8"))
            .build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
