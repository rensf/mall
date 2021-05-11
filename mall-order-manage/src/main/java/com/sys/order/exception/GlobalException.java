package com.sys.order.exception;

import lombok.Data;

/**
 * @author rensf
 * @date 2021/5/8 17:00
 */
@Data
public class GlobalException extends Exception {

    private String code;

    public GlobalException(String code, String message) {
        super(message);
        this.code = code;
    }

}
