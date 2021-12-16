package com.github.biyanwen.json2sql.config;

import com.github.biyanwen.json2sql.api.BeanProcessor;
import com.github.biyanwen.json2sql.enums.KeyConversionConfig;

import java.util.*;

public class Configuration {
    /**
     * varchar 类型的长度 默认 255
     */
    private String varcharLength = "(255)";

    /**
     * decimal 类型精度 默认 (10,4)
     */
    private String decimalPrecision = "(10,4)";

    /**
     * json key转成sql时如何转换
     */
    private KeyConversionConfig keyConversionConfig = KeyConversionConfig.nothing;

    /**
     * list类型表示可以注册多个处理器
     * map 的key为要处理javabean的key
     */
    private List<Map<String, BeanProcessor>> beanProcessorMaps = new ArrayList<>();

    /**
     * 要忽略不处理的字段
     */
    private List<String> ignoreKeys = new ArrayList<>();

    /**
     * 是否为 null 值生成 sql 。默认为 false
     */
    private boolean generateNull = false;

    public boolean isGenerateNull() {
        return generateNull;
    }

    public void setGenerateNull(boolean generateNull) {
        this.generateNull = generateNull;
    }

    public void setBeanProcessorMaps(List<Map<String, BeanProcessor>> beanProcessorMaps) {
        this.beanProcessorMaps = beanProcessorMaps;
    }

    public void setIgnoreKeys(List<String> keys) {
        ignoreKeys.addAll(keys);
    }

    public void setIgnoreKeys(String key) {
        ignoreKeys.add(key);
    }

    public List<String> getIgnoreKeys() {
        return ignoreKeys;
    }

    public List<Map<String, BeanProcessor>> getBeanProcessorMaps() {
        return beanProcessorMaps;
    }

    public void registerBeanProcessor(String key, BeanProcessor beanProcessor) {
        Map<String, BeanProcessor> beanProcessorMap = new HashMap<>();
        beanProcessorMap.put(key, beanProcessor);
        this.registerBeanProcessor(beanProcessorMap);
    }

    public void registerBeanProcessor(Map<String, BeanProcessor> beanProcessorMap) {
        this.registerBeanProcessorList(Collections.singletonList(beanProcessorMap));
    }

    public void registerBeanProcessorList(List<Map<String, BeanProcessor>> beanProcessorMaps) {
        this.beanProcessorMaps.addAll(beanProcessorMaps);
    }

    public KeyConversionConfig getKeyConversionConfig() {
        return keyConversionConfig;
    }

    public void setKeyConversionConfig(KeyConversionConfig keyConversionConfig) {
        this.keyConversionConfig = keyConversionConfig;
    }

    public String getVarcharLength() {
        return varcharLength;
    }

    public void setVarcharLength(String varcharLength) {
        if (varcharLength.contains("(") && varcharLength.contains(")")) {
            this.varcharLength = varcharLength;
        } else {
            this.varcharLength = "(" + varcharLength + ")";
        }
    }

    public String getDecimalPrecision() {
        return decimalPrecision;
    }

    public void setDecimalPrecision(String decimalPrecision) {
        if (decimalPrecision.contains("(") && decimalPrecision.contains(")")) {
            this.decimalPrecision = decimalPrecision;
        } else {
            this.decimalPrecision = "(" + decimalPrecision + ")";
        }
    }
}
