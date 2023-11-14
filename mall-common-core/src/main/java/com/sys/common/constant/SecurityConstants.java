package com.sys.common.constant;

/**
 * 安全认证变量
 *
 * @author rensf
 * @date 2022/12/27
 */
public interface SecurityConstants {

    String CLIENT_ID_KEY = "clientId";

    String AUTHORIZATION_KEY = "authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_PREFIX = "bearer ";

    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    String BASIC_PREFIX = "Basic ";

    String ADMIN_CLIENT_ID = "mall-admin";

    String WEB_CLIENT_ID = "mall-web";

    String REFRESH_TOKEN_KEY = "refresh-token";

    String AUTHENTICATION_IDENTITY_KEY = "authenticationIdentity";

    String JWT_PAYLOAD_KEY = "payload";

    /**
     * MD5加盐值
     */
    String SECRET_KEY = "{mall}";

}
