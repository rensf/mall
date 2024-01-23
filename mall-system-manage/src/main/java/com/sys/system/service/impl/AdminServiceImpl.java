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

}
