package com.sys.order.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rensf
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1251361322610920609L;

    private String code = "0";

    private String msg = "操作成功！";

    private Long timestamp = System.currentTimeMillis();

    private T result;

    public static Result<Object> error(String code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
