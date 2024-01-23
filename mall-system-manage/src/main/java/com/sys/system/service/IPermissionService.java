package com.sys.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.system.entity.Permission;

/**
 * 权限 服务接口
 *
 * @author rensf
 * @date 2023/10/26
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 刷新Redis缓存中
     * @return
     */
    boolean refreshPermRolesRules();

}
