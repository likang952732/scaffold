package com.wwinfo.annotation;

import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-05-05 14:34
 */
public class NumValidator implements ConstraintValidator<NumCheck, String> {

    @Override
    public boolean isValid(String numStr, ConstraintValidatorContext constraintValidatorContext) {
        if(StrUtil.isBlank(numStr))
            return true;
        return numStr.matches("[0-9]+(.[0-9]+)?");
    }

}
