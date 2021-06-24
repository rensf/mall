package com.sys.system.exception;

import com.sys.common.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rensf
 * @date 2021/5/21 16:10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = GlobalException.class)
    public Result<?> errorHandler(GlobalException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

}
