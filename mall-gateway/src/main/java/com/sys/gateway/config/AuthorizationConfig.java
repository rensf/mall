package com.sys.gateway.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import com.sys.common.core.enums.ResultCodeEnum;
import com.sys.gateway.constant.AuthorizeConstants;
import com.sys.gateway.manager.AuthorizationManager;
import com.sys.gateway.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Objects;

/**
 * 鉴权配置
 *
 * @author rensf
 * @date 2022/6/15
 */
@ConfigurationProperties(prefix = "security")
@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
@Slf4j
public class AuthorizationConfig {

    private final AuthorizationManager authorizationManager;

    @Setter
    private List<String> ignoreUrls;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        if (Objects.isNull(ignoreUrls)) {
            log.error("从配置中心获取白名单失败！");
        }
        http.oauth2ResourceServer()
            .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
            .publicKey(rsaPublicKey());
        http.oauth2ResourceServer()
            .authenticationEntryPoint(authenticationEntryPoint());
        // 白名单放行
        http.authorizeExchange()
            .pathMatchers(Convert.toStrArray(ignoreUrls))
            .permitAll();
        // 请求鉴权
        http.authorizeExchange()
            .anyExchange().access(authorizationManager)
            .and().exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())
            .authenticationEntryPoint(authenticationEntryPoint())
            .and().csrf().disable();
        return http.build();
    }

    /**
     * 重定义鉴权转换器
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthorizeConstants.AUTHORIZE_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthorizeConstants.JWT_AUTHORITIES_KEY);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    /**
     * 自定义未授权响应
     */
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, denied) -> Mono.defer(() -> Mono.just(exchange.getResponse()))
            .flatMap(response -> ResponseUtils.writeErrorInfo(response, ResultCodeEnum.ACCESS_UNAUTHORIZED));
    }

    /**
     * 自定义token无效或过期响应
     */
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, e) -> Mono.defer(() -> Mono.just(exchange.getResponse()))
            .flatMap(response -> ResponseUtils.writeErrorInfo(response, ResultCodeEnum.TOKEN_INVALID_OR_EXPIRED));
    }

    /**
     * 获取本地JWT验签公钥
     * keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
     */
    @SneakyThrows
    @Bean
    public RSAPublicKey rsaPublicKey() {
        Resource resource = new ClassPathResource("public.key");
        InputStream is = resource.getInputStream();
        String publicKeyData = IoUtil.read(is).toString();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec((Base64.decode(publicKeyData)));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

}
