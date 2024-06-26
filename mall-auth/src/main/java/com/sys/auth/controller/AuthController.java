package com.sys.auth.controller;

import com.sys.auth.util.RequestUtils;
import com.sys.common.core.result.Result;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * 鉴权认证 控制层
 *
 * @author rensf
 * @date 2022/12/26
 */
@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
@Slf4j
@Api(tags = "鉴权认证")
public class AuthController {

    private TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public Object postAccessToken(Principal principal, @RequestBody Map<String, String> param) throws HttpRequestMethodNotSupportedException {
        String clientId = RequestUtils.getOAuth2ClientId();
        log.info("OAuth认证授权 客户端ID：{}，请求参数：{}", clientId, param);
        OAuth2AccessToken token = tokenEndpoint.postAccessToken(principal, param).getBody();
        return Result.success(token);
    }

}
