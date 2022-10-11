package com.qingxuan.baize.sensitive.strategy;

/**
 * 脱敏策略
 *
 * @auther: 611001
 * @date: 2022/7/21 11:37
 */
public interface SensitiveStrategy {
    /**
     * 脱敏的具体实现方法
     *
     * @param source 原来对象属性
     * @param begin  内容开始显示的长度
     * @param end    内容末尾显示的长度
     * @return 返回脱敏后的信息
     */
    String desensitization(final String source, int begin, int end);

    /**
     * 脱敏的具体实现方法
     *
     * @param source      原来对象属性
     * @param pattern     内容显示正则
     * @param replaceChar 替换后的字符
     * @return 返回脱敏后的信息
     */
    default String desensitizationByPattern(String source, String pattern, String replaceChar) {
        return SensitiveInfoUtils.patternReplace(source, pattern, replaceChar);
    }
}
