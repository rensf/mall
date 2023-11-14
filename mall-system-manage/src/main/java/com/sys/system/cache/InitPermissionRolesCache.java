package com.sys.system.cache;

import com.sys.system.service.IPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化权限角色缓存
 *
 * @author rensf
 * @date 2023/10/25
 */
@Component
@AllArgsConstructor
public class InitPermissionRolesCache implements CommandLineRunner {

    private final IPermissionService permissionService;

    @Override
    public void run(String... args) {
        permissionService.refreshPermRolesRules();
    }

}
