package com.sys.system.exception;

import lombok.Data;

/**
 * @author rensf
 * @date 2021/5/21 15:46
 */
@Data
public class GlobalException extends Exception {

    private String code;

    public GlobalException(String code, String message) {
        super(message);
        this.code = code;
    }

}
