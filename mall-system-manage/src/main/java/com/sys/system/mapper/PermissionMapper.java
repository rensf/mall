package com.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.system.entity.Permission;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限 映射层
 *
 * @author rensf
 * @date 2023/10/26
 */
@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查询权限角色列表
     *
     * @return
     */
    @Results(id = "PermRoleMap", value = {
        @Result(column = "permission_id", property = "permissionId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "permission_name", property = "permissionName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "menu_id", property = "menuId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "permission_url", property = "permissionUrl", jdbcType = JdbcType.VARCHAR),
        @Result(column = "permission_btn", property = "permissionBtn", jdbcType = JdbcType.VARCHAR),
        @Result(column = "role_code", property = "roles", jdbcType = JdbcType.ARRAY, many = @Many(select = "com.sys.system.mapper.RoleMapper.getCodeByName"))
    })
    @Select("select " +
            "a.permission_id, " +
            "a.permission_name, " +
            "a.menu_id, " +
            "a.permission_url, " +
            "a.permission_btn, " +
            "c.role_code " +
            "from " +
            "td_sys_permission a " +
            "left join tr_sys_permission_role b on a.permission_id = b.permission_id " +
            "left join td_sys_role c on b.role_id = c.role_id " +
            "where " +
            "a.flag = 1 ")
    List<Permission> listPermRoles();

}
