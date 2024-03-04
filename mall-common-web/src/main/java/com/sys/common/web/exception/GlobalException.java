package com.sys.common.web.exception;

import com.sys.common.core.enums.ResultCodeEnum;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/5/8
 */
@Getter
public class GlobalException extends Exception implements Serializable {

    private static final long serialVersionUID = 6311265381909020028L;

    private String code;

    public GlobalException(String code, String message) {
        super(message);
        this.code = code;
    }

    public GlobalException(ResultCodeEnum resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

}
