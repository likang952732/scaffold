package com.wwinfo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-05-05 10:50
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = com.wwinfo.annotation.TelValidator.class)
public @interface TelCheck {

    /**
     * 校验不通过的message
     */
    String message() default "请输入正确的电话号码";

    /**
     * 分组校验
     */
    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

}
