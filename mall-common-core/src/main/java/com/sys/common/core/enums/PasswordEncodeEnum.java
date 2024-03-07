package com.sys.common.core.enums;

import lombok.Getter;

/**
 * 密码编码
 *
 * @author rensf
 * @date 2023/1/5
 */
@Getter
public enum PasswordEncodeEnum {

    // BCRYPT加密
    BCRYPT("{bcrypt}", "BCRYPT加密"),
    // MD5加密
    MD5("{MD5}", "MD5加密"),
    // 明文
    NOOP("{noop}", "明文");

    private final String prefix;
    private final String desc;

    PasswordEncodeEnum(String prefix, String desc) {
        this.prefix = prefix;
        this.desc = desc;
    }

}
