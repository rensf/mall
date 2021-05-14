package com.sys.config.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/5/12 17:39
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 7624725923885126287L;

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
