package com.sys.common.web.exception;

import com.sys.common.core.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author rensf
 * @date 2021/5/8
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = GlobalException.class)
    public Result<?> errorHandler(GlobalException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

}
