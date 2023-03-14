package com.sys.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author rensf
 * @date 2023/3/7
 */
@Data
@Component
@TableName("td_sys_role")
public class Role {

    /**
     * ID
     */
    @TableId
    private String roleId;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 序号
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 生效标志
     */
    @TableField("flag")
    private Integer flag = 1;

}
