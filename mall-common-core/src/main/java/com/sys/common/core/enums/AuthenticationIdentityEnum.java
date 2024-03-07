package com.sys.common.core.enums;

import lombok.Getter;

/**
 * 身份认证标识
 *
 * @author rensf
 * @date 2023/2/17
 */
@Getter
public enum AuthenticationIdentityEnum {

    // 用户名方式
    USERNAME("username", "用户名"),
    // 手机号方式
    MOBILE("mobile", "手机号");

    private final String value;
    private final String label;

    AuthenticationIdentityEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

}
