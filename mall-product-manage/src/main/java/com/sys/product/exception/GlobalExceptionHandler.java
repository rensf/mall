package com.sys.product.exception;

import com.sys.common.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rensf
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = GlobalException.class)
    public Result<?> errorHandler(GlobalException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

}
