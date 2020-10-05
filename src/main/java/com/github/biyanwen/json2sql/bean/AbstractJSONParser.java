package com.github.biyanwen.json2sql.bean;

import com.github.biyanwen.json2sql.impl.DefaultBeanProcessor;
import org.apache.commons.collections4.CollectionUtils;
import com.github.biyanwen.json2sql.Json2sql;
import com.github.biyanwen.json2sql.api.BeanProcessor;
import com.github.biyanwen.json2sql.api.JSONParser;
import com.github.biyanwen.json2sql.config.Configuration;
import com.github.biyanwen.json2sql.enums.KeyConversionEnum;

import java.util.*;
import java.util.function.Function;

import static com.github.biyanwen.json2sql.Json2sql.*;

public abstract class AbstractJSONParser implements JSONParser {
    private final Configuration configuration = Json2sql.getConfiguration();

    protected String getVarchar() {
        return VARCHAR + configuration.getVarcharLength();
    }

    protected String getDecimal() {
        return DECIMAL + configuration.getDecimalPrecision();
    }

    protected String getDate() {
        return DATE;
    }

    protected String getInteger() {
        return INTEGER;
    }

    protected String getTimeStamp() {
        return TIMESTAMP;
    }

    /**
     * 将根据json解析出的map 生成sql需要的数据类型
     *
     * @param maps
     * @return
     */

    protected List<Map<String, String>> createTableMap(List<Map<String, Object>> maps, Function<String, String> function) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Map<String, String> stringMap = mapProcessor(map);
            Map<String, String> tableMap = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                if (ifIgnore(entry.getKey())) {
                    continue;
                }
                String key = KeyConversionEnum.valueOf(configuration.getKeyConversionConfig().toString()).getKeyConversionStrategy().cover(entry.getKey());
                String paramType;
                paramType = function.apply(entry.getValue());
                tableMap.put(key, paramType);
            }
            mapList.add(tableMap);
        }
        return mapList;
    }

    private boolean ifIgnore(String key) {
        List<String> ignoreKeys = configuration.getIgnoreKeys();
        return ignoreKeys.contains(key);
    }

    private Map<String, String> mapProcessor(Map<String, Object> map) {

        List<Map<String, BeanProcessor>> beanProcessorMaps = configuration.getBeanProcessorMaps();
        Map<String, String> processNestedResult = processNestedBean(beanProcessorMaps, map);
        return processNestedResult;
    }

    private Map<String, String> processNestedBean(List<Map<String, BeanProcessor>> beanProcessorMaps, Map<String, Object> map) {
        Map<String, Object> temp = new HashMap<>(map);
        Map<String, String> stringMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(beanProcessorMaps)) {
            //用javabean处理json中的嵌套对象
            for (Map<String, BeanProcessor> beanProcessorMap : beanProcessorMaps) {
                for (Map.Entry<String, BeanProcessor> entry : beanProcessorMap.entrySet()) {
                    Object obj = temp.get(entry.getKey());
                    if (obj == null) {
                        continue;
                    }
                    BeanProcessor beanProcessor = entry.getValue();
                    Map<String, String> beanProcessorResultMap = beanProcessor.processor(obj);
                    stringMap.putAll(beanProcessorResultMap);
                    //处理完移除原来的元素
                    temp.remove(entry.getKey());
                }
            }
        }

        //如果使用者没有手动处理嵌套对象就自动处理
        Map<String, Object> autoProcessResult = autoProcess(temp);

        //剩下的元素都转换成String
        for (Map.Entry<String, Object> stringObjectEntry : autoProcessResult.entrySet()) {
            String value = null;
            if (stringObjectEntry.getValue() != null) {
                value = stringObjectEntry.getValue().toString();
            }
            stringMap.put(stringObjectEntry.getKey(), value);
        }
        return stringMap;
    }

    protected Map<String, Object> autoProcess(Map<String, Object> temp) {
        Map<String, Object> result = new HashMap<>(temp);

        for (Map.Entry<String, Object> entry : temp.entrySet()) {
            if (!(entry.getValue() instanceof Map)) {
                continue;
            }
            BeanProcessor<Map<String, Object>> beanProcessor = new DefaultBeanProcessor();
            Map<String, String> processorResult = beanProcessor.processor((Map<String, Object>) entry.getValue());
            result.remove(entry.getKey());
            result.putAll(processorResult);
        }
        return result;
    }
}
