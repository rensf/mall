package com.sys.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @author rensf
 * @date 2021/7/20 18:45
 */
public class OperateToken {

    /**
     * token过期时间 30min
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    public static synchronized String generateToken(String userId) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create().withExpiresAt(date).sign(Algorithm.HMAC256(userId));
    }

    public static String refreshToken() {
        return null;
    }

}
