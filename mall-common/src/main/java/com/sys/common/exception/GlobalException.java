package com.sys.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/5/8 17:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalException extends Exception implements Serializable {

    private static final long serialVersionUID = 6311265381909020028L;

    private String code;

    public GlobalException(String code, String message) {
        super(message);
        this.code = code;
    }

}
