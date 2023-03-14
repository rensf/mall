package com.sys.gateway.constant;

/**
 * @author rensf
 * @date 2022/10/21
 */
public interface AuthorizeConstants {

    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";

    /**
     * JWT存储权限前缀
     */
    String AUTHORIZE_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String JWT_AUTHORITIES_KEY = "authorities";

}
