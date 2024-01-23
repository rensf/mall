package com.sys.common.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sys.common.constant.SecurityConstants;
import lombok.SneakyThrows;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author rensf
 * @date 2021/7/20
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

    @SneakyThrows
    public static JSONObject getJwtPayload() {
        JSONObject jsonObject = null;
        String payload = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(SecurityConstants.JWT_PAYLOAD_KEY);
        if (StrUtil.isNotBlank(payload)) {
            jsonObject = JSON.parseObject(URLDecoder.decode(payload, StandardCharsets.UTF_8.name()));
        }
        return jsonObject;
    }

}
