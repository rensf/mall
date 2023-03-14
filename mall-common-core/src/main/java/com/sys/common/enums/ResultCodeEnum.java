package com.sys.common.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2022/6/18 15:50
 */
public enum ResultCodeEnum implements Serializable {

    // 调取成功
    SUCCESS("0", "调取成功！"),
    // 用户不存在
    USER_NOT_EXIST("10000", "用户不存在！"),
    // 用户名或密码错误
    USERNAME_OR_PASSWORD_ERROR("10001", "用户名或密码错误！"),
    // token无效或已过期
    TOKEN_INVALID_OR_EXPIRED("10002", "token无效或已过期！"),
    // 无权限访问
    ACCESS_UNAUTHORIZED("10003", "访问未授权！"),
    // token被禁止
    TOKEN_ACCESS_FORBIDDEN("10004", "token已被禁止访问！"),
    // 客户端认证失败
    CLIENT_AUTHENTICATION_FAILED("10005", "客户端认证失败！");

    @Getter
    private String code;
    @Getter
    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
