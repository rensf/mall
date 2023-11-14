package com.sys.auth.security.provider.sysuserprovider;

import com.sys.auth.security.detail.sysuserdetail.SysUserDetailsServiceImpl;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;

/**
 * @author rensf
 * @date 2023/10/24
 */
@Data
public class SysUserAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SysUserAuthenticationToken authenticationToken = (SysUserAuthenticationToken) authentication;
        String userName = (String) authenticationToken.getPrincipal();
        UserDetails userDetails = ((SysUserDetailsServiceImpl) userDetailsService).loadUserByUsername(userName);
        SysUserAuthenticationToken result = new SysUserAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SysUserAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
