package com.sys.auth.api;

import com.sys.common.dto.ClientAuthDTO;
import com.sys.common.dto.AdminAuthDTO;
import com.sys.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author rensf
 * @date 2022/12/30
 */
@FeignClient(value = "MALL-SYSTEM-MANAGE", contextId = "auth-client")
public interface AuthClientFeign {

    /**
     * 通过ID获取OAuth2客户端认证信息
     * @param clientId 客户端ID
     * @return 客户端认证DTO
     */
    @GetMapping("/client/getOAuth2ClientById")
    Result<ClientAuthDTO> getOAuth2ClientById(@RequestParam String clientId);

    /**
     * 通过用户名获取用户
     * @param adminName 用户名
     * @return
     */
    @GetMapping("/admin/getAdminByAdminName")
    Result<AdminAuthDTO> getAdminByAdminName(@RequestParam String adminName);

}
