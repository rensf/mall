package com.sys.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.system.entity.Admin;

/**
 * @author rensf
 * @date 2021/5/21 14:51
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 用户名密码登录
     * @param loginInfo
     * @exception Exception
     * @return
     */
    Admin loginByNormal(Admin loginInfo) throws Exception;

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    Integer addAdmin(Admin admin);

}
