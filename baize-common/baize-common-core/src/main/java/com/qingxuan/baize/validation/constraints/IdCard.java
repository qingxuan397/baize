package com.qingxuan.baize.validation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 身份证校验
 *
 * @auther: 611001
 * @date: 2022/7/20 10:43
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {}
)
public @interface IdCard {

    String message() default "身份证格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}