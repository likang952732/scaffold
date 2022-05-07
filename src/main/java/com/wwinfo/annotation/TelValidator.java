package com.wwinfo.annotation;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-05-05 10:51
 */
public class TelValidator implements ConstraintValidator<TelCheck, String>  {

    @Value("${telregex}")
    private String telregex;

    @Override
    public boolean isValid(String tel, ConstraintValidatorContext constraintValidatorContext) {
        if(StrUtil.isBlank(tel))
            return true;
        return tel.matches("\\d{3}-\\d{7}");
    }

}
