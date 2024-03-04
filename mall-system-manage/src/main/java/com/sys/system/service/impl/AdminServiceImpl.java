package com.sys.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.web.dto.AdminAuthDTO;
import com.sys.common.core.util.IDUtils;
import com.sys.common.web.util.TokenUtils;
import com.sys.system.entity.Admin;
import com.sys.system.mapper.AdminMapper;
import com.sys.system.service.IAdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统管理员 服务层
 *
 * @author rensf
 * @date 2021/5/21
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Override
    public AdminAuthDTO getAdminByAdminName(String adminName) {
        return this.baseMapper.getAdminByAdminName(adminName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addAdmin(Admin admin) {
        admin.setAdminId(IDUtils.generateID());
        return this.baseMapper.insert(admin);
    }

    @Override
    public Admin getLoginAdminInfo() {
        return this.getOne(new LambdaQueryWrapper<Admin>()
            .eq(Admin::getAdminId, TokenUtils.getJwtPayload().getString("userId"))
            .select(
                Admin::getAdminId,
                Admin::getAdminName
            ));
    }

}
