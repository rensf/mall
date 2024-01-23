package com.sys.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * 权限 实体
 *
 * @author rensf
 * @date 2023/10/26
 */
@Data
@TableName("td_sys_permission")
public class Permission {

    /**
     * ID
     */
    @TableId
    private String permissionId;

    /**
     * 权限名称
     */
    @TableField("permission_name")
    private String permissionName;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private String menuId;

    /**
     * URL权限标识
     */
    @TableField("permission_url")
    private String permissionUrl;

    /**
     * 按钮权限标识
     */
    @TableField("permission_btn")
    private String permissionBtn;

    /**
     * 生效标志
     */
    @TableField("flag")
    private Integer flag = 1;

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<String> roles;

}
