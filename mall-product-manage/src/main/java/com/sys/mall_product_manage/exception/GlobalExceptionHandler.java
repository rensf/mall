package com.sys.mall_product_manage.exception;

import com.sys.mall_product_manage.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = GlobalException.class)
    public Result<?> errorHandler(GlobalException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

}
