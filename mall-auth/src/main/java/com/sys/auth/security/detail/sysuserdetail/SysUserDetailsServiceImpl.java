package com.sys.auth.security.detail.sysuserdetail;

import com.sys.auth.api.AuthUserFeign;
import com.sys.common.dto.UserAuthDTO;
import com.sys.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 获取用户信息服务
 *
 * @author rensf
 * @date 2023/10/23
 */
@Service("sysUserDetailsService")
@Slf4j
public class SysUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AuthUserFeign authUserFeign;

    @Override
    public SysUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUserDetails userDetails = null;
        Result<UserAuthDTO> result = authUserFeign.loginByNormal(userName);
        if (Result.isSuccess(result)) {
            UserAuthDTO user = result.getResult();
            if (Objects.nonNull(user)) {
                userDetails = new SysUserDetails(user);
            }
        }
        return userDetails;
    }

}
