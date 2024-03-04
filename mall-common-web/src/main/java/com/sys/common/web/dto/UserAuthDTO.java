package com.sys.common.web.dto;

import lombok.Data;

/**
 * @author rensf
 * @date 2023/10/23
 */
@Data
public class UserAuthDTO {

    /**
     * ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户手机号
     */
    private String userTel;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户性别
     */
    private Integer userSex;

    /**
     * 购物车ID
     */
    private String cartId;

    /**
     * 生效标志
     */
    private Integer flag;

}
