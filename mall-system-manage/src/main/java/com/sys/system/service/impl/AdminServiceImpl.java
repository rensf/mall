package com.sys.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.exception.GlobalException;
import com.sys.common.utils.GenerateID;
import com.sys.common.utils.Md5Encode;
import com.sys.common.utils.OperateToken;
import com.sys.system.entity.Admin;
import com.sys.system.mapper.AdminMapper;
import com.sys.system.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author rensf
 * @date 2021/5/21 14:52
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private AdminMapper adminMapper;
    private StringRedisTemplate redisTemplate;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper, StringRedisTemplate redisTemplate) {
        this.adminMapper = adminMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Admin loginByNormal(Admin loginInfo) throws Exception {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("flag", 1);
        qw.eq("admin_name", loginInfo.getAdminName());
        Admin admin = adminMapper.selectOne(qw);
        if (Objects.isNull(admin)) {
            throw new GlobalException("10001", "用户不存在！");
        }
        if (Md5Encode.makePwd(loginInfo.getAdminName(), loginInfo.getPassword()).equals(admin.getPassword())) {
            // 不返回密码
            admin.setPassword("");
            // 设置token
            String token = OperateToken.generateToken(admin.getAdminId());
            admin.setToken(token);
            redisTemplate.expire(token, 30, TimeUnit.MINUTES);
            return admin;
        } else {
            throw new GlobalException("10000", "用户名或密码错误！");
        }
    }

    @Override
    public Integer addAdmin(Admin admin) {
        admin.setAdminId(GenerateID.generateID());
        return adminMapper.insert(admin);
    }

}
