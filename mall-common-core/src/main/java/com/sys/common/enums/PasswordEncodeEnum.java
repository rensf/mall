package com.sys.common.enums;

import lombok.Getter;

/**
 * 密码编码
 * @author rensf
 * @date 2023/1/5
 */
public enum PasswordEncodeEnum {

    // BCRYPT加密
    BCRYPT("{bcrypt}", "BCRYPT加密"),
    // MD5加密
    MD5("{MD5}", "MD5加密"),
    // 明文
    NOOP("{noop}", "明文");

    @Getter
    private String prefix;

    PasswordEncodeEnum(String prefix, String desc) {
        this.prefix = prefix;
    }

}
