package com.sys.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.system.entity.Admin;
import com.sys.system.exception.GlobalException;
import com.sys.system.mapper.AdminMapper;
import com.sys.system.service.IAdminService;
import com.sys.system.util.GenerateID;
import com.sys.system.util.Md5Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rensf
 * @date 2021/5/21 14:52
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin loginByNormal(Admin loginInfo) throws Exception {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("flag", 1);
        qw.eq("admin_name", loginInfo.getAdminName());
        Admin admin = adminMapper.selectOne(qw);
        if (admin.getPassword().equals(Md5Encode.makePwd(loginInfo.getAdminName(), loginInfo.getPassword()))) {
            // 不返回密码
            admin.setPassword("");
            return admin;
        } else {
            throw new GlobalException("10001", "密码错误！");
        }
    }

    @Override
    public Integer addAdmin(Admin admin) {
        admin.setAdminId(GenerateID.generateID());
        return adminMapper.insert(admin);
    }

}
