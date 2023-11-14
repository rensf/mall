package com.sys.gateway.manager;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.sys.common.constant.GlobalConstants;
import com.sys.common.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 鉴权（自定义）管理
 *
 * @author rensf
 * @date 2022/12/7
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final RedisTemplate redisTemplate;

    /**
     * 鉴权检查
     *
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

        String token = request.getHeaders().getFirst(SecurityConstants.AUTHORIZATION_KEY);
        if (StrUtil.isNotBlank(token) && StrUtil.startWithIgnoreCase(token, SecurityConstants.JWT_PREFIX)) {
            Map<String, Object> urlPermRolesMap = redisTemplate.opsForHash().entries(GlobalConstants.URL_PERM_ROLES_KEY);

            List<String> authorizedRoles = new ArrayList<>();
            for (Map.Entry<String, Object> permRoles : urlPermRolesMap.entrySet()) {
                String perm = permRoles.getKey();
                if (pathMatcher.match(path, perm)) {
                    authorizedRoles.addAll(Convert.toList(String.class, permRoles.getValue()));
                }
            }

            return mono.filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authority -> {
                    String roleCode = StrUtil.removePrefix(authority, SecurityConstants.AUTHORITY_PREFIX);
                    if (GlobalConstants.USER_ROLE.equals(roleCode)) {
                        return true;
                    }
                    return authorizedRoles.contains(roleCode);
                }).map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        } else {
            return Mono.just(new AuthorizationDecision(false));
        }
    }

}
