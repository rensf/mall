package com.sys.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.common.dto.AdminAuthDTO;
import com.sys.system.entity.Admin;

/**
 * @author rensf
 * @date 2021/5/21 14:51
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

}
