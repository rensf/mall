package com.sys.auth.security.config;

import com.alibaba.fastjson.JSONObject;
import com.sys.auth.security.detail.clientdetail.ClientDetailsServiceImpl;
import com.sys.auth.security.detail.sysadmindetail.SysAdminDetails;
import com.sys.auth.security.detail.sysadmindetail.SysAdminDetailsServiceImpl;
import com.sys.auth.security.detail.sysuserdetail.SysUserDetails;
import com.sys.auth.security.detail.sysuserdetail.SysUserDetailsServiceImpl;
import com.sys.auth.security.provider.SysTokenGranter;
import com.sys.auth.security.provider.SysTokenService;
import com.sys.auth.security.refresh.PreAuthenticatedUserDetailsServiceImpl;
import com.sys.common.core.constant.SecurityConstants;
import com.sys.common.core.enums.ResultCodeEnum;
import com.sys.common.core.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
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
    private final SysUserDetailsServiceImpl sysUserDetailsService;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 授权配置（authorization）以及令牌（token）的访问端点和令牌服务（token services）
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 配置token存储方式
        endpoints.tokenStore(jwtTokenStore());
        // 获取授权器列表，添加自定义授权器
        List<TokenGranter> granterList = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        granterList.add(new SysTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(), authenticationManager));
        // 创建混合授权器
        CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(granterList);
        // 配置授权
        endpoints.authenticationManager(authenticationManager)
            .accessTokenConverter(jwtAccessTokenConverter())
            .tokenGranter(compositeTokenGranter)
            .tokenServices(tokenServices(endpoints));
    }

    /**
     * JWT token存储方式
     *
     * @return token存储方式
     */
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * token服务配置
     *
     * @param endpoints 授权服务端点
     * @return token服务
     */
    public SysTokenService tokenServices(AuthorizationServerEndpointsConfigurer endpoints) {
        // 创建token增强链
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        // 创建token服务
        SysTokenService tokenServices = new SysTokenService();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        tokenServices.setRedisTemplate(redisTemplate);
        // 多用户体系下，客户端ID和业务处理器的映射关系
        Map<String, UserDetailsService> clientUserDetailsServiceMap = new HashMap<>();
        clientUserDetailsServiceMap.put(SecurityConstants.ADMIN_CLIENT_ID, sysAdminDetailsService);
        clientUserDetailsServiceMap.put(SecurityConstants.WEB_CLIENT_ID, sysUserDetailsService);
        // 重写预认证身份提供器，可根据客户端ID和认证方式区分用户体系
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
            }
            if (principal instanceof SysUserDetails) {
                SysUserDetails sysUserDetails = (SysUserDetails) principal;
                additionalInfo.put("userId", sysUserDetails.getUserId());
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
