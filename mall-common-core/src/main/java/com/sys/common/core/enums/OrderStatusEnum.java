package com.sys.common.core.enums;

import lombok.Getter;

/**
 * @author rensf
 * @date 2022/10/8
 */
@Getter
public enum OrderStatusEnum {

    // 待支付
    TO_BE_PAID(101, "待支付"),
    // 用户取消
    USER_CANCELED(102, "用户取消"),
    // 待发货
    TO_BE_SHIPPED(201, "待发货"),
    // 已发货
    SHIPPED(301, "已发货"),
    // 交易完成
    DEAL_DONE(501, "交易完成"),
    // 售后
    AFTER_SALES(601, "售后");

    private Integer code;

    private String text;

    OrderStatusEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

}
