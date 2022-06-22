package com.wwinfo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-05-05 9:57
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = com.wwinfo.annotation.IpValidator.class)
public @interface IpCheck {

    /**
     * 校验不通过的message
     */
    String message() default "请输入正确的ip地址";

    /**
     * 分组校验
     */
    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

}
