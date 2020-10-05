package com.github.biyanwen.json2sql.enums;

import com.github.biyanwen.json2sql.api.KeyConversionStrategy;

/**
 * Description:  <br>
 *
 * @author byw
 * @create 2020/9/29
 */
public enum KeyConversionEnum {

    /**
     * 驼峰转下划线——小写
     */
    hump2UnderscoreLower("hump2UnderscoreLower", KeyConversionStrategy.hump2UnderscoreLower()),

    /**
     * 驼峰转下划线——大写
     */
    hump2UnderscoreCapital("hump2UnderscoreCapital", KeyConversionStrategy.hump2UnderscoreCapital()),

    /**
     * 不需要转换
     */
    nothing("nothing", KeyConversionStrategy.nothing());

    String Strategy;

    KeyConversionStrategy keyConversionStrategy;

    KeyConversionEnum(String strategy, KeyConversionStrategy keyConversionStrategy) {
        Strategy = strategy;
        this.keyConversionStrategy = keyConversionStrategy;
    }

    public String getStrategy() {
        return Strategy;
    }

    public KeyConversionStrategy getKeyConversionStrategy() {
        return keyConversionStrategy;
    }
}
