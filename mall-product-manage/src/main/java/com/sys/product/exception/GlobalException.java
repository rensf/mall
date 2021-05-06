package com.sys.product.exception;

import lombok.Data;

/**
 * @author rensf
 */
@Data
public class GlobalException extends Exception {

    private String code;

    public GlobalException(String code, String message) {
        super(message);
        this.code = code;
    }

}
