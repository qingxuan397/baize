package com.qingxuan.baize.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Key Value 的键值对
 *
 * @auther: 611001
 * @date: 2022/7/19 10:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue<K, V> {
    private K key;
    private V value;
}
