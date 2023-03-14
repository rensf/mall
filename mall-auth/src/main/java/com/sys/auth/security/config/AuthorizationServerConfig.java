package com.sys.auth.security.config;

import com.alibaba.fastjson.JSONObject;
import com.sys.auth.security.detail.clientdetail.ClientDetailsServiceImpl;
import com.sys.auth.security.detail.userdetail.SysAdminDetails;
import com.sys.auth.security.detail.userdetail.SysAdminDetailsServiceImpl;
import com.sys.auth.security.refresh.PreAuthenticatedUserDetailsServiceImpl;
import com.sys.common.constant.SecurityConstants;
import com.sys.common.enums.ResultCodeEnum;
import com.sys.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import java.security.KeyPair;
import java.util.*;

/**
 * 认证配置
 *
 * @author rensf
 * @date 2022/12/29
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final ClientDetailsServiceImpl clientDetailsService;
    private final SysAdminDetailsServiceImpl sysAdminDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 授权配置（authorization）以及令牌（token）的访问端点和令牌服务（token services）
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        endpoints.tokenStore(jwtTokenStore());
        List<TokenGranter> granterList = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(granterList);
        endpoints.authenticationManager(authenticationManager)
            .accessTokenConverter(jwtAccessTokenConverter())
            .tokenEnhancer(tokenEnhancerChain)
            .tokenGranter(compositeTokenGranter)
            .tokenServices(tokenServices(endpoints));
    }

    /**
     * JWT token
     * @return token存储方式
     */
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * token服务配置
     * @param configurer 授权服务端点配置
     * @return token服务
     */
    public DefaultTokenServices tokenServices(AuthorizationServerEndpointsConfigurer configurer) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(configurer.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        Map<String, UserDetailsService> clientUserDetailsServiceMap = new HashMap<>();
        clientUserDetailsServiceMap.put(SecurityConstants.ADMIN_CLIENT_ID, sysAdminDetailsService);
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(new PreAuthenticatedUserDetailsServiceImpl<>(clientUserDetailsServiceMap));
        tokenServices.setAuthenticationManager(new ProviderManager(provider));
        return tokenServices;
    }

    /**
     * 使用非对称加密算法对 token 签名
     */
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    /**
     * 密钥库中获取密钥对（公钥加私钥）
     * keytool -genkeypair -alias 别名 -keyalg RSA -keypass 密码 -keystore jwt.jks -storepass 存储密码
     */
    public KeyPair keyPair() {
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "263661".toCharArray());
        return factory.getKeyPair("mall", "263661".toCharArray());
    }

    /**
     * JWT 内容增强
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (assessToken, authentication) -> {
            Map<String, Object> additionalInfo = new HashMap<>();
            Object principal = authentication.getUserAuthentication().getPrincipal();
            if (principal instanceof SysAdminDetails) {
                SysAdminDetails sysUserDetails = (SysAdminDetails) principal;
                additionalInfo.put("userId", sysUserDetails.getUserId());
                additionalInfo.put("username", sysUserDetails.getUsername());
            }
            ((DefaultOAuth2AccessToken) assessToken).setAdditionalInformation(additionalInfo);
            return assessToken;
        };
    }

    /**
     * 自定义认证异常响应
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            response.setStatus(HttpStatus.OK.value());
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            Result<String> result = Result.error(ResultCodeEnum.CLIENT_AUTHENTICATION_FAILED);
            response.getWriter().print(JSONObject.toJSONString(result));
            response.getWriter().flush();
        };
    }

}
