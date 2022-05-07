package com.wwinfo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-05-05 14:33
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = com.wwinfo.annotation.NumValidator.class)
public @interface NumCheck {

    /**
     * 校验不通过的message
     */
    String message() default "请输入合法的数字";

    /**
     * 分组校验
     */
    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

}
