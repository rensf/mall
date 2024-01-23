package com.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.common.dto.AdminAuthDTO;
import com.sys.system.entity.Admin;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统管理员 映射层
 *
 * @author rensf
 * @date 2021/5/21
 */
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 通过用户名获取用户
     * @param adminName 用户名
     * @return 用户
     */
    @Results(id = "AdminAuthMap", value = {
        @Result(column = "admin_id", property = "adminId", jdbcType = JdbcType.VARCHAR),
        @Result(column = "admin_name", property = "adminName", jdbcType = JdbcType.VARCHAR),
        @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
        @Result(column = "flag", property = "flag", jdbcType = JdbcType.INTEGER),
        @Result(column = "role_code", property = "roles", jdbcType = JdbcType.ARRAY, many = @Many(select = "com.sys.system.mapper.RoleMapper.getCodeByName"))
    })
    @Select("select " +
            "tsa.admin_id, " +
            "tsa.admin_name, " +
            "tsa.password, " +
            "tsa.flag, " +
            "tsr.role_code " +
            "from " +
            "td_sys_admin tsa " +
            "left join tr_sys_admin_role tsar " +
            "on " +
            "tsa.admin_id = tsar.admin_id " +
            "left join td_sys_role tsr " +
            "on " +
            "tsar.role_id = tsr.role_id " +
            "where tsa.flag = 1 " +
            "and tsa.admin_name = #{adminName}")
    AdminAuthDTO getAdminByAdminName(String adminName);

}
