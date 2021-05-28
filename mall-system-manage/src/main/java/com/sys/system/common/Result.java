package com.sys.system.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rensf
 * @date 2021/5/21 11:31
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -5062496266498147252L;

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
