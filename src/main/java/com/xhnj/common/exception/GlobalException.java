package com.xhnj.common.exception;

import com.xhnj.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 @Description 全局异常处理
 *@author kang.li
 *@date 2021/2/19 13:48   
 */
@Slf4j
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult handle(Exception e) {
        if (e instanceof BusinessException) {
            log.error("业务逻辑处理异常：{}", ((BusinessException) e).getMsg());
            e.printStackTrace();
            return CommonResult.failed(((BusinessException) e).getMsg());
        }
        log.error("系统异常：{}", e);
        return CommonResult.failed(e.getMessage());
    }
}
