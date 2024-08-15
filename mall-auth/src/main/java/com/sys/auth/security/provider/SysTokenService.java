package com.sys.auth.security.provider;

import com.sys.common.core.constant.SecurityConstants;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 自定义token服务
 *
 * @author rensf
 * @date 2024/6/3
 */
@Slf4j
public class SysTokenService extends DefaultTokenServices {

    private TokenStore tokenStore;

    private TokenEnhancer accessTokenEnhancer;

    @Setter
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) {
        log.info("自定义createAccessToken");
        OAuth2AccessToken existingAccessToken = tokenStore.getAccessToken(authentication);
        // 判断是否存在accessToken
        if (existingAccessToken != null) {
            // 如果存在，判断accessToken是否过期
            if (existingAccessToken.isExpired()) {
                // 如果过期，删除accessToken
                tokenStore.removeAccessToken(existingAccessToken);
            } else {
                // 如果未过期，重新存储accessToken
                tokenStore.storeAccessToken(existingAccessToken, authentication);
                return existingAccessToken;
            }
        }
        // 创建accessToken
        String value = UUID.randomUUID().toString();
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(value);
        token.setScope(authentication.getOAuth2Request().getScope());
        OAuth2AccessToken accessToken = accessTokenEnhancer != null ? accessTokenEnhancer.enhance(token, authentication) : token;
        tokenStore.storeAccessToken(accessToken, authentication);
        // 将accessToken的jti存入redis，30分钟过期
        String key = SecurityConstants.TOKEN_KEY + ":" + authentication.getName();
        redisTemplate.opsForValue().set(key, value, SecurityConstants.TOKEN_EXPIRE, TimeUnit.SECONDS);
        return accessToken;
    }

    /**
     * An access token enhancer that will be applied to a new token before it is saved in the token store.
     *
     * @param accessTokenEnhancer the access token enhancer to set
     */
    @Override
    public void setTokenEnhancer(TokenEnhancer accessTokenEnhancer) {
        this.accessTokenEnhancer = accessTokenEnhancer;
    }

    /**
     * The persistence strategy for token storage.
     *
     * @param tokenStore the store for access and refresh tokens.
     */
    @Override
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

}
