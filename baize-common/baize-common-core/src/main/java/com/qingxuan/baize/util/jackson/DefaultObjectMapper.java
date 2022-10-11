package com.qingxuan.baize.util.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * 默认的Jackson ObjectMapper，处理系统中通用的配置
 *
 * @auther: 611001
 * @date: 2022/7/19 14:20
 */
public class DefaultObjectMapper extends ObjectMapper {
    public DefaultObjectMapper() {
        initConfigure();
    }

    private void initConfigure() {
        //去掉默认的时间戳格式
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //设置为中国上海时区
        this.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        //序列化时，日期的统一格式
        this.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
        //序列化处理
        this.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        this.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
        this.findAndRegisterModules();
        //失败处理
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //反序列化时，属性不存在的兼容处理
        this.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //设置将驼峰命名法转换成下划线的方式输入输出
        this.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        //使用默认的Jackson注解
        this.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
        //处理bigDecimal
        this.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        this.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        //默认的日期时间格式处理
        registerJavaTimeModule();
        //自定义的转换
        registerCustomModule();
    }

    protected void registerJavaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
        javaTimeModule.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
        javaTimeModule.addDeserializer(Date.class, new DateDeserializers.DateDeserializer()); //这里可能有问题
        this.registerModule(javaTimeModule);
    }

    protected void registerCustomModule() {
        SimpleModule customModule = new SimpleModule("CustomModule", PackageVersion.VERSION);
//        customModule.addSerializer();
//        customModule.addDeserializer();

        this.registerModule(customModule);

    }
}
