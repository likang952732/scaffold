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
 * DateTime: 2022-05-05 9:58
 */
public class IpValidator implements ConstraintValidator<IpCheck, String> {

    @Value("${ipregex}")
    private String ipregex;

    @Override
    public boolean isValid(String ip, ConstraintValidatorContext constraintValidatorContext) {
        if(StrUtil.isBlank(ip))
            return true;
        return ip.matches(ipregex);
    }
}

