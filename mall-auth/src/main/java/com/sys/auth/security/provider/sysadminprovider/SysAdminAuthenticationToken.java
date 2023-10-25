package com.sys.auth.security.provider.sysadminprovider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author rensf
 * @date 2023/10/24
 */
public class SysAdminAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = -6880690049172418503L;

    public SysAdminAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public SysAdminAuthenticationToken(Object principal, Object credentials,
                                       Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
