package com.qingxuan.baize.validation.internal.constraintvalidators;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.qingxuan.baize.validation.constraints.IdCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @auther: 611001
 * @date: 2022/7/20 10:57
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    @Override
    public void initialize(IdCard annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果身份证为空，默认不校验，即校验通过
        if (StrUtil.isEmpty(value)) {
            return true;
        }
        return Validator.isCitizenId(value);
    }

}
