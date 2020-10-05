package com.github.biyanwen.json2sql.api;

import com.google.common.base.CaseFormat;

/**
 * Description:  <br>
 *
 * @author byw
 * @create 2020/9/29
 */
public interface KeyConversionStrategy {
    String cover(String key);

    static KeyConversionStrategy hump2UnderscoreLower() {
        return key -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
    }

    static KeyConversionStrategy hump2UnderscoreCapital() {
        return key -> CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, key);
    }

    static KeyConversionStrategy nothing() {
        return key -> key;
    }
}
