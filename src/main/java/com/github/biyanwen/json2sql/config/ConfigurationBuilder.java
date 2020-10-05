package com.github.biyanwen.json2sql.config;

import com.github.biyanwen.json2sql.api.BeanProcessor;
import com.github.biyanwen.json2sql.enums.KeyConversionConfig;

import java.util.*;

/**
 * Description:  <br>
 *
 * @author byw
 * @create 2020/9/30
 */
public final class ConfigurationBuilder {
    private String varcharLength = "(255)";
    private String decimalPrecision = "(10,4)";
    private KeyConversionConfig keyConversionConfig = KeyConversionConfig.nothing;
    private List<String> ignoreKeys = new ArrayList<>();
    private List<Map<String, BeanProcessor>> beanProcessorMaps = new ArrayList<>();

    private ConfigurationBuilder() {
    }

    public static ConfigurationBuilder config() {
        return new ConfigurationBuilder();
    }

    public ConfigurationBuilder withVarcharLength(String varcharLength) {
        this.varcharLength = "(" + varcharLength + ")";
        return this;
    }

    public ConfigurationBuilder withDecimalPrecision(String decimalPrecision) {
        this.decimalPrecision = "(" + decimalPrecision + ")";
        return this;
    }

    public ConfigurationBuilder withKeyConversionConfig(KeyConversionConfig keyConversionConfig) {
        this.keyConversionConfig = keyConversionConfig;
        return this;
    }

    public ConfigurationBuilder withIgnoreKeys(List<String> ignoreKeys) {
        this.ignoreKeys = ignoreKeys;
        return this;
    }

    public ConfigurationBuilder withIgnoreKeys(String key) {
        this.withIgnoreKeys(Collections.singletonList(key));
        return this;
    }

    public ConfigurationBuilder withBeanProcessorMap(String key, BeanProcessor beanProcessor) {
        Map<String, BeanProcessor> beanProcessorMap = new HashMap<>();
        beanProcessorMap.put(key, beanProcessor);
        this.withBeanProcessorMap(beanProcessorMap);
        return this;
    }

    public ConfigurationBuilder withBeanProcessorMap(Map<String, BeanProcessor> beanProcessorMap) {
        this.beanProcessorMaps.add(beanProcessorMap);
        return this;
    }

    public ConfigurationBuilder withBeanProcessorMaps(List<Map<String, BeanProcessor>> beanProcessorMaps) {
        this.beanProcessorMaps.addAll(beanProcessorMaps);
        return this;
    }

    public Configuration build() {
        Configuration configuration = new Configuration();
        configuration.setVarcharLength(varcharLength);
        configuration.setDecimalPrecision(decimalPrecision);
        configuration.setKeyConversionConfig(keyConversionConfig);
        configuration.setIgnoreKeys(ignoreKeys);
        configuration.setBeanProcessorMaps(beanProcessorMaps);
        return configuration;
    }
}
