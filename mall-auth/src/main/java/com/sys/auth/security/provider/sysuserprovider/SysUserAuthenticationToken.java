package com.sys.auth.security.provider.sysuserprovider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author rensf
 * @date 2023/10/24
 */
public class SysUserAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = -2710773294508718554L;

    public SysUserAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public SysUserAuthenticationToken(Object principal, Object credentials,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
