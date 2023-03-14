package com.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rensf
 * @date 2023/3/8
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户名查询角色编码
     * @param adminName 用户名
     * @return 角色编码集合
     */
    @Select("select " +
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
    List<String> getCodeByName(String adminName);

}
