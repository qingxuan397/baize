package com.qingxuan.baize.validation.internal.constraintvalidators;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import com.qingxuan.baize.validation.constraints.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @auther: 611001
 * @date: 2022/7/20 10:57
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果手机号为空，默认不校验，即校验通过
        if (StrUtil.isEmpty(value)) {
            return true;
        }
        return PhoneUtil.isPhone(value);
    }

}
