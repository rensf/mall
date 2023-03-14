package com.sys.auth.security.refresh;

import com.sys.auth.util.RequestUtils;
import com.sys.common.constant.SecurityConstants;
import com.sys.common.enums.ResultCodeEnum;
import com.sys.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;
import java.util.Objects;

/**
 * 刷新token后再次认证UserDetailsService
 *
 * @author rensf
 * @date 2023/2/15
 */
@Slf4j
public class PreAuthenticatedUserDetailsServiceImpl<T extends Authentication> implements AuthenticationUserDetailsService<T>, InitializingBean {

    /**
     * 客户端ID和用户服务UserDetailsService的映射
     */
    private Map<String, UserDetailsService> userDetailsServiceMap;

    public PreAuthenticatedUserDetailsServiceImpl(Map<String, UserDetailsService> userDetailsServiceMap) {
        this.userDetailsServiceMap = userDetailsServiceMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Objects.isNull(userDetailsServiceMap)) {
            throw new Exception("用户服务未定义！");
        }
        log.debug("用户服务已定义完成！");
    }

    /**
     * 重写PreAuthenticatedAuthenticationProvider的preAuthenticatedUserDetailsService属性，
     * 可根据客户端和认证方式选择用户服务UserDetailService获取用户信息UserDetail
     * @param authentication 身份认证信息
     * @return 用户信息
     * @throws UsernameNotFoundException 用户名不存在异常
     */
    @Override
    public UserDetails loadUserDetails(T authentication) throws UsernameNotFoundException {
        String clientId = RequestUtils.getOAuth2ClientId();
        UserDetailsService userDetailsService = userDetailsServiceMap.get(clientId);
        return userDetailsService.loadUserByUsername(authentication.getName());
    }

}
