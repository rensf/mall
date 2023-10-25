package com.sys.common.result;

import com.sys.common.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

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

    private T result;

    public static <T> Result<T> error(String code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(ResultCodeEnum resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMessage());
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T o) {
        Result<T> result = new Result<>();
        result.setResult(o);
        return result;
    }

    public static boolean isSuccess(Result<?> result) {
        return Objects.nonNull(result) && ResultCodeEnum.SUCCESS.getCode().equals(result.getCode());
    }

}
