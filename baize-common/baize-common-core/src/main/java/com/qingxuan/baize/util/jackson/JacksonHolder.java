package com.qingxuan.baize.util.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 在整个系统使用单例方式来创建
 *
 * @author: 611001
 * @date: 2022/7/19 15:54
 */
public class JacksonHolder {

    private ObjectMapper objectMapper;

    private JacksonHolder() {
        objectMapper = new DefaultObjectMapper();
    }

    /**
     * 取得ObjectMapper
     *
     * @return
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 设置ObjectMapper
     */
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    public static class ObjectMapperInstance {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static JacksonHolder instance = new JacksonHolder();
    }

    /**
     * 延迟加载ObjectMapperInstance对象
     *
     * @return
     */
    public static JacksonHolder getInstance() {
        return ObjectMapperInstance.instance;
    }
}
