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

    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 30;

    private int accessTokenValiditySeconds = 60 * 60 * 12;

    private boolean supportRefreshToken = false;

    private TokenStore tokenStore;

    private ClientDetailsService clientDetailsService;

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
        // 创建refreshToken
        String refreshToken = createRefreshToken(authentication);
        // 创建accessToken
        OAuth2AccessToken accessToken = createAccessToken(authentication, refreshToken);
        tokenStore.storeAccessToken(accessToken, authentication);
        return accessToken;
    }

    /**
     * 创建refreshToken
     *
     * @param authentication 身份验证
     * @return refreshToken
     */
    private String createRefreshToken(OAuth2Authentication authentication) {
        // 判断是否支持refreshToken
        if (!isSupportRefreshToken(authentication.getOAuth2Request())) {
            log.info("不支持refreshToken");
            return null;
        }
        String key = SecurityConstants.REFRESH_TOKEN_KEY + ":" + authentication.getName();
        // 判断是否存在refreshToken
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            log.info("存在refreshToken");
            return redisTemplate.opsForValue().get(key);
        }
        // 创建refreshToken
        String value = UUID.randomUUID().toString();
        int validitySeconds = getRefreshTokenValiditySeconds(authentication.getOAuth2Request());
        if (validitySeconds > 0) {
            redisTemplate.opsForValue().set(key, value, validitySeconds, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(key, value, refreshTokenValiditySeconds, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 创建accessToken
     *
     * @param authentication 身份验证
     * @param value jti
     * @return accessToken
     */
    private OAuth2AccessToken createAccessToken(OAuth2Authentication authentication, String value) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(value);
        int validitySeconds = getAccessTokenValiditySeconds(authentication.getOAuth2Request());
        if (validitySeconds > 0) {
            token.setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
        }
        token.setScope(authentication.getOAuth2Request().getScope());
        return accessTokenEnhancer != null ? accessTokenEnhancer.enhance(token, authentication) : token;
    }

    /**
     * 获取accessToken有效期（秒）
     *
     * @param clientAuth 认证请求
     * @return accessToken有效期（秒）
     */
    @Override
    protected int getAccessTokenValiditySeconds(OAuth2Request clientAuth) {
        if (clientDetailsService != null) {
            ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
            Integer validity = client.getAccessTokenValiditySeconds();
            if (validity != null) {
                return validity;
            }
        }
        return accessTokenValiditySeconds;
    }

    /**
     * 获取refreshToken有效期（秒）
     *
     * @param clientAuth 认证请求
     * @return refreshToken有效期（秒）
     */
    @Override
    protected int getRefreshTokenValiditySeconds(OAuth2Request clientAuth) {
        if (clientDetailsService != null) {
            ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
            Integer validity = client.getRefreshTokenValiditySeconds();
            if (validity != null) {
                return validity;
            }
        }
        return refreshTokenValiditySeconds;
    }

    /**
     * Is a refresh token supported for this client (or the global setting if
     * {@link #setClientDetailsService(ClientDetailsService) clientDetailsService} is not set.
     *
     * @param clientAuth the current authorization request
     * @return boolean to indicate if refresh token is supported
     */
    @Override
    protected boolean isSupportRefreshToken(OAuth2Request clientAuth) {
        if (clientDetailsService != null) {
            ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
            return client.getAuthorizedGrantTypes().contains("refresh_token");
        }
        return this.supportRefreshToken;
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
     * The validity (in seconds) of the refresh token. If less than or equal to zero then the tokens will be
     * non-expiring.
     *
     * @param refreshTokenValiditySeconds The validity (in seconds) of the refresh token.
     */
    @Override
    public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    /**
     * The default validity (in seconds) of the access token. Zero or negative for non-expiring tokens. If a client
     * details service is set the validity period will be read from the client, defaulting to this value if not defined
     * by the client.
     *
     * @param accessTokenValiditySeconds The validity (in seconds) of the access token.
     */
    @Override
    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    /**
     * Whether to support the refresh token.
     *
     * @param supportRefreshToken Whether to support the refresh token.
     */
    @Override
    public void setSupportRefreshToken(boolean supportRefreshToken) {
        this.supportRefreshToken = supportRefreshToken;
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

    /**
     * The client details service to use for looking up clients (if necessary). Optional if the access token expiry is
     * set globally via {@link #setAccessTokenValiditySeconds(int)}.
     *
     * @param clientDetailsService the client details service
     */
    @Override
    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

}
