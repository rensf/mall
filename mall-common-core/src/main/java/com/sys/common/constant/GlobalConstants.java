package com.sys.common.constant;

/**
 * 全局变量
 *
 * @author rensf
 * @date 2023/1/16
 */
public interface GlobalConstants {

    /**
     * 全局状态 —— 是
     */
    Integer STATUS_TRUE = 1;

    /**
     * 商城用户角色
     */
    String USER_ROLE = "USER";

    /**
     * [ {接口路径:[角色编码]},...]
     */
    String URL_PERM_ROLES_KEY = "system:perm_roles_rules:url";

    /**
     * [{按钮权限:[角色编码]},...]
     */
    String BTN_PERM_ROLES_KEY = "system:perm_roles_rules:btn";

}
