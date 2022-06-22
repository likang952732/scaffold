package com.wwinfo.common;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 * Description: 处理@RequestMapping时的异常
 *
 * @Author: lk
 * DateTime: 2022-04-07 21:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult methodNotSupportExceptionHandler(HttpRequestMethodNotSupportedException ex){
        return CommonResult.failed(ex.getMessage() + " Check your url method");
    }

}
