package com.sys.auth.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.sys.common.constant.SecurityConstants;
import com.sys.common.enums.AuthenticationIdentityEnum;
import com.sys.common.util.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

/**
 * @author rensf
 * @date 2022/12/27
 */
@Slf4j
public class RequestUtils {

    /**
     * 获取登录认证的客户端ID
     *
     * @return 客户端ID
     */
    public static String getOAuth2ClientId() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 从请求路径中获取clientId
        String clientId = request.getParameter(SecurityConstants.CLIENT_ID_KEY);
        if (StringUtils.isNotBlank(clientId)) {
            return clientId;
        }
        // 从请求头获取
        String basic = request.getHeader(SecurityConstants.AUTHORIZATION_KEY);
        if (StringUtils.isNotBlank(basic)) {
            basic = basic.replace(SecurityConstants.BASIC_PREFIX, Strings.EMPTY);
            String basicPlainText = new String(Base64.getDecoder().decode(basic.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            // client:secret
            clientId = basicPlainText.split(":")[0];
        }
        return clientId;
    }

    /**
     * 解析JWT获取获取身份认证标识
     *
     * @return 身份认证标识
     */
    @SneakyThrows
    public static String getAuthenticationIdentity() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String refreshToken = request.getParameter(SecurityConstants.REFRESH_TOKEN_KEY);

        String payload = String.valueOf(JWSObject.parse(refreshToken).getPayload());
        JSONObject jsonObject = new JSONObject().getJSONObject(payload);

        String authenticationIdentity = jsonObject.getString(SecurityConstants.AUTHENTICATION_IDENTITY_KEY);
        if (StrUtil.isBlank(authenticationIdentity)) {
            authenticationIdentity = AuthenticationIdentityEnum.USERNAME.getValue();
        }
        return authenticationIdentity;
    }

}
