package com.sys.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.user.entity.User;
import com.sys.user.mapper.UserMapper;
import com.sys.user.service.IUserService;

import java.util.List;

/**
 * @author rensf
 * @date 2021/6/4 17:10
 */
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public List queryAddressByUserId() {
        return null;
    }
}
