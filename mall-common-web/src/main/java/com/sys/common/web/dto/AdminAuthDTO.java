package com.sys.common.web.dto;

import lombok.Data;

import java.util.List;

/**
 * @author rensf
 * @date 2023/1/13
 */
@Data
public class AdminAuthDTO {

    /**
     * 用户ID
     */
    private String adminId;

    /**
     * 用户名
     */
    private String adminName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 生效标志
     */
    private Integer flag;

    /**
     * 角色编码列表
     */
    private List<String> roles;

}
