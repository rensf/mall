package com.sys.common.core.enums;

import lombok.Getter;

/**
 * 控件类型
 *
 * @author rensf
 * @date 2024/3/6
 */
@Getter
public enum WidgetTypeEnum {

    // 普通选择器
    NORMAL_PICKER(1, "普通选择器"),
    // 颜色选择器
    COLOR_PICKER(2, "颜色选择器"),
    // 尺寸选择器
    SIZE_PICKER(3, "尺寸选择器");

    private final Integer code;
    private final String text;

    WidgetTypeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

}
