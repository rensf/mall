package com.sys.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.common.constant.GlobalConstants;
import com.sys.system.entity.Permission;
import com.sys.system.mapper.PermissionMapper;
import com.sys.system.service.IPermissionService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author rensf
 * @date 2023/10/26
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean refreshPermRolesRules() {
        // 先清除已缓存的权限角色集合
        redisTemplate.delete(Arrays.asList(GlobalConstants.URL_PERM_ROLES_KEY, GlobalConstants.BTN_PERM_ROLES_KEY));
        // 查询权限角色集合
        List<Permission> permissionList = this.baseMapper.listPermRoles();
        if (CollectionUtil.isNotEmpty(permissionList)) {
            // 初始化URL【权限-角色】集合
            List<Permission> urlPermList = permissionList.stream()
                .filter(item -> StrUtil.isNotBlank(item.getPermissionUrl()))
                .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(urlPermList)) {
                Map<String, List<String>> urlPermRoleMap = new HashMap<>();
                urlPermList.forEach(item -> {
                    String perm = item.getPermissionUrl();
                    List<String> roles = item.getRoles();
                    urlPermRoleMap.put(perm, roles);
                });
                redisTemplate.opsForHash().putAll(GlobalConstants.URL_PERM_ROLES_KEY, urlPermRoleMap);
            }

            // 初始化按钮【权限-角色】集合
            List<Permission> btnPermList = permissionList.stream()
                .filter(item -> StrUtil.isNotBlank(item.getPermissionBtn()))
                .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(btnPermList)) {
                Map<String, List<String>> btnPermRoleMap = new HashMap<>();
                btnPermList.forEach(item -> {
                    String perm = item.getPermissionBtn();
                    List<String> roles = item.getRoles();
                    btnPermRoleMap.put(perm, roles);
                });
                redisTemplate.opsForHash().putAll(GlobalConstants.BTN_PERM_ROLES_KEY, btnPermRoleMap);
            }
        }
        return true;
    }

}
