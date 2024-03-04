package com.sys.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.common.web.dto.AdminAuthDTO;
import com.sys.system.entity.Admin;

/**
 * 系统管理员 服务接口
 *
 * @author rensf
 * @date 2021/5/21
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 通过用户名获取用户
     * @param adminName 用户名
     * @return 用户
     */
    AdminAuthDTO getAdminByAdminName(String adminName);

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    Integer addAdmin(Admin admin);

    /**
     * 获取登录管理员信息
     * @return 管理员信息
     */
    Admin getLoginAdminInfo();
}
