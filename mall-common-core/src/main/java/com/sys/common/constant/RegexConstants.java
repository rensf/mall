package com.sys.common.constant;

/**
 * 正则表达式常量
 *
 * @author rensf
 * @date 2022/10/21
 */
public interface RegexConstants {

    String TEL_REGEX = "/^1[345789]\\d{9}$/";

    String EMAIL_REGEX = "/^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$/";

}
