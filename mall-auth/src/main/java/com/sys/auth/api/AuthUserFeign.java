package com.sys.auth.api;

import com.sys.common.web.dto.UserAuthDTO;
import com.sys.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author rensf
 * @date 2023/10/23
 */
@FeignClient(value = "MALL-USER-MANAGE", contextId = "auth-user")
public interface AuthUserFeign {

    /**
     * 通过用户信息获取用户
     * @param userName 用户信息
     * @return
     */
    @GetMapping("/user/loginByNormal")
    Result<UserAuthDTO> loginByNormal(@RequestParam String userName);

}
