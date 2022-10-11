package com.qingxuan.baize.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.qingxuan.baize.sensitive.enums.SensitiveStrategyEnum;

import java.lang.annotation.*;

/**
 * 地址脱敏
 *
 * @auther: 611001
 * @date: 2022/7/21 11:49
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SensitiveInfo(strategy = SensitiveInfo.class, pattern = SensitiveStrategyEnum.MOBILE.getPattern(), replaceChar = "$1*****$2")
@JacksonAnnotationsInside
public @interface SensitiveAddress {
}
