package com.sys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.user.entity.User;

import java.util.List;

/**
 * @author rensf
 * @date 2021/6/4 17:10
 */
public interface IUserService extends IService<User> {

    /**
     * 查询详细地址通过用户ID
     * @return
     */
    List queryAddressByUserId();

}
