package com.github.biyanwen.json2sql;

import com.github.biyanwen.json2sql.api.BeanProcessor;

import java.util.HashMap;
import java.util.Map;

public class SingleFiledProcess implements BeanProcessor<Map<String, Object>> {
    @Override
    public Map<String, String> processor(Map<String, Object> stringObjectMap) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Object> stringObjectEntry : stringObjectMap.entrySet()) {
            result.put("t_" +  stringObjectEntry.getKey(), stringObjectEntry.getValue().toString());
        }
        return result;
    }
}
