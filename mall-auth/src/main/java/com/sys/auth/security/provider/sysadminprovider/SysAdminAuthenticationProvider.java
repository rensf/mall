package com.sys.auth.security.provider.sysadminprovider;

import com.sys.auth.security.detail.sysadmindetail.SysAdminDetailsServiceImpl;
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
public class SysAdminAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SysAdminAuthenticationToken authenticationToken = (SysAdminAuthenticationToken) authentication;
        String userName = (String) authenticationToken.getPrincipal();
        UserDetails userDetails = ((SysAdminDetailsServiceImpl) userDetailsService).loadUserByUsername(userName);
        SysAdminAuthenticationToken result = new SysAdminAuthenticationToken(userDetails, authentication.getCredentials(), new HashSet<>());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SysAdminAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
