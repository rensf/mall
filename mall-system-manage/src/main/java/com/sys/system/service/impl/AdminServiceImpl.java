package com.sys.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.dto.AdminAuthDTO;
import com.sys.common.enums.ResultCodeEnum;
import com.sys.common.exception.GlobalException;
import com.sys.common.util.IDUtils;
import com.sys.common.util.MD5Utils;
import com.sys.common.util.TokenUtils;
import com.sys.system.entity.Admin;
import com.sys.system.mapper.AdminMapper;
import com.sys.system.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author rensf
 * @date 2021/5/21 14:52
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public AdminAuthDTO getAdminByAdminName(String adminName) {
        return adminMapper.getAdminByAdminName(adminName);
    }

    @Override
    public Admin loginByNormal(Admin loginInfo) throws Exception {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("flag", 1);
        qw.eq("admin_name", loginInfo.getAdminName());
        Admin admin = adminMapper.selectOne(qw);
        if (Objects.isNull(admin)) {
            throw new GlobalException(ResultCodeEnum.USER_NOT_EXIST);
        }
        if (MD5Utils.makePwd(loginInfo.getPassword()).equals(admin.getPassword())) {
            // 不返回密码
            admin.setPassword("");
            // 设置token
            String token = TokenUtils.generateToken(admin.getAdminId());
            admin.setToken(token);
            return admin;
        } else {
            throw new GlobalException(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }
    }

    @Override
    public Integer addAdmin(Admin admin) {
        admin.setAdminId(IDUtils.generateID());
        return adminMapper.insert(admin);
    }

}
