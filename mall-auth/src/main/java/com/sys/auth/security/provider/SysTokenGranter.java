package com.sys.auth.security.provider;

import com.sys.auth.security.provider.sysadminprovider.SysAdminAuthenticationToken;
import com.sys.auth.security.provider.sysuserprovider.SysUserAuthenticationToken;
import com.sys.common.core.constant.SecurityConstants;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义token授权器
 *
 * @author rensf
 * @date 2023/10/25
 */
public class SysTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "sys_password";
    private final AuthenticationManager authenticationManager;

    public SysTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, AuthenticationManager authenticationManager) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String username = parameters.get("username");
        String password = parameters.get("password");

        // 防止密码泄露
        parameters.remove("password");

        // 和密码模式一样的逻辑
        Authentication userAuth;
        switch (client.getClientId()) {
            case SecurityConstants.ADMIN_CLIENT_ID:
                userAuth = new SysAdminAuthenticationToken(username, password);
                break;
            case SecurityConstants.WEB_CLIENT_ID:
                userAuth = new SysUserAuthenticationToken(username, password);
                break;
            default:
                userAuth = new UsernamePasswordAuthenticationToken(username, password);
        }
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        try {
            userAuth = this.authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException e) {
            throw new InvalidGrantException(e.getMessage());
        }

        if (userAuth != null && userAuth.isAuthenticated()) {
            OAuth2Request storedOAuth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);
            return new OAuth2Authentication(storedOAuth2Request, userAuth);
        } else {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }
    }

}
