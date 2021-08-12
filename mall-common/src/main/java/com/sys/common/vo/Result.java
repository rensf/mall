package com.sys.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/6/24 15:32
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -8558851926265925090L;

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

    public static Result<Object> success(Object o) {
        Result<Object> result = new Result<>();
        result.setResult(o);
        return result;
    }
}
