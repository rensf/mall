package com.sys.auth.security.detail.sysuserdetail;

import com.sys.common.constant.GlobalConstants;
import com.sys.common.constant.SecurityConstants;
import com.sys.common.dto.UserAuthDTO;
import com.sys.common.enums.PasswordEncodeEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 用户信息
 *
 * @author rensf
 * @date 2023/10/23
 */
@Data
public class SysUserDetails implements UserDetails {

    private static final long serialVersionUID = -3903342724591402537L;

    private String userId;
    private String username;
    private String password;
    private Boolean enabled;
    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    public SysUserDetails(UserAuthDTO user) {
        this.setUserId(user.getUserId());
        this.setUsername(user.getUserName());
        this.setPassword(PasswordEncodeEnum.MD5.getPrefix() + SecurityConstants.SECRET_KEY + user.getPassword());
        this.setEnabled(GlobalConstants.STATUS_TRUE.equals(user.getFlag()));
        this.authorities.add(new SimpleGrantedAuthority(GlobalConstants.USER_ROLE));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
