package com.sys.gateway.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

/**
 * 鉴权（自定义）管理
 * @author rensf
 * @date 2022/12/7
 */
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    /**
     * 鉴权检查
     * @param mono
     * @param context
     * @return
     */
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext context) {
        ServerHttpRequest request = context.getExchange().getRequest();
        // 预检请求放行
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            return Mono.just(new AuthorizationDecision(true));
        }
        PathMatcher pathMatcher = new AntPathMatcher();
        String path = request.getMethodValue() + ":" + request.getURI();

        mono.filter(Authentication::isAuthenticated)
            .flatMapIterable(Authentication::getAuthorities)
            .map(GrantedAuthority::getAuthority)
            .any(authority -> {
                String roleCode = authority;
                return true;
            }).map(AuthorizationDecision::new)
            .defaultIfEmpty(new AuthorizationDecision(false));

        return Mono.just(new AuthorizationDecision(false));
    }

}
