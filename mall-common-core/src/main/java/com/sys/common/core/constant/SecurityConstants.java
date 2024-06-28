package com.sys.common.core.constant;

/**
 * 安全认证常量
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

    /**
     * redis token key
     */
    String TOKEN_KEY = "token";

    /**
     * redis token expire
     */
    Integer TOKEN_EXPIRE = 1800;

    String JWT_PAYLOAD_KEY = "payload";

    /**
     * MD5加盐值
     */
    String SECRET_KEY = "{mall}";

}
