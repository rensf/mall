package com.sys.auth.api;

import com.sys.common.web.dto.AdminAuthDTO;
import com.sys.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author rensf
 * @date 2022/12/30
 */
@FeignClient(value = "MALL-SYSTEM-MANAGE", contextId = "auth-client")
public interface AuthSystemFeign {

    /**
     * 通过用户名获取用户
     * @param adminName 用户名
     * @return
     */
    @GetMapping("/admin/getAdminByAdminName")
    Result<AdminAuthDTO> getAdminByAdminName(@RequestParam String adminName);

}
