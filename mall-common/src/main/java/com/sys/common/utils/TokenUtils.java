package com.sys.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @author rensf
 * @date 2021/7/20 18:45
 */
public class TokenUtils {

    /**
     * token过期时间 30min
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 生成Token
     * @param id
     * @return
     */
    public static synchronized String generateToken(String id) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create().withExpiresAt(date).sign(Algorithm.HMAC256(id));
    }

    public static String refreshToken() {
        return null;
    }

}
