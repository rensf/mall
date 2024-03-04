package com.sys.auth.security.detail.sysadmindetail;

import com.sys.auth.api.AuthSystemFeign;
import com.sys.common.web.dto.AdminAuthDTO;
import com.sys.common.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 获取管理员信息服务
 *
 * @author rensf
 * @date 2023/1/13
 */
@Service("sysAdminDetailsService")
@Slf4j
public class SysAdminDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AuthSystemFeign authSystemFeign;

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        SysAdminDetails adminDetails = null;
        Result<AdminAuthDTO> result = authSystemFeign.getAdminByAdminName(adminName);
        if (Result.isSuccess(result)) {
            AdminAuthDTO admin = result.getResult();
            if (Objects.nonNull(admin)) {
                adminDetails = new SysAdminDetails(admin);
            }
        }
        return adminDetails;
    }

}
