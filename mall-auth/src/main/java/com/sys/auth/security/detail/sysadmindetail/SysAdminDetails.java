package com.sys.auth.security.detail.sysadmindetail;

import com.sys.common.constant.GlobalConstants;
import com.sys.common.dto.AdminAuthDTO;
import com.sys.common.enums.PasswordEncodeEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 管理员信息
 *
 * @author rensf
 * @date 2023/1/13
 */
@Data
public class SysAdminDetails implements UserDetails {

    private static final long serialVersionUID = -6972734182693823036L;

    private String userId;
    private String username;
    private String password;
    private Boolean enabled;
    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    public SysAdminDetails(AdminAuthDTO admin) {
        this.setUserId(admin.getAdminId());
        this.setUsername(admin.getAdminName());
        this.setPassword(PasswordEncodeEnum.MD5.getPrefix() + "{mall}" + admin.getPassword());
        this.setEnabled(GlobalConstants.STATUS_TRUE.equals(admin.getFlag()));
        if (!CollectionUtils.isEmpty(admin.getRoles())) {
            admin.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }
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
