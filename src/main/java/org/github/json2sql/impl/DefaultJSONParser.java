package org.github.json2sql.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.github.json2sql.api.JSONWriter;
import org.github.json2sql.bean.AbstractJSONParser;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DefaultJSONParser extends AbstractJSONParser {
    @SneakyThrows
    @Override
    public void parse(String json, String tableName) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> maps = objectMapper.readValue(json, new TypeReference<List<Map<String, String>>>() {
        });


        Map<String, String> createTableNeedParamMap = createCreateTableMap(maps);

        Map<String, Object> sqlParamMap = new HashMap<>();
        sqlParamMap.put("tableName", tableName);
        sqlParamMap.put("tableParam", createTableNeedParamMap);

        JSONWriter jsonWriter = new DefaultJSONWriter();
        jsonWriter.writer(sqlParamMap, "");
    }

    private Map<String, String> createCreateTableMap(List<Map<String, String>> maps) {
        Map<String, String> paramMap = maps.get(0);
        Map<String, String> tableMap = new LinkedHashMap<>();
        int size = paramMap.size();
        int mark = 0;
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            String paramType;
            //满足条件说明是最后一个元素
            if (mark == size - 1) {
                paramType = createParamType(entry.getValue());
            } else {
                paramType = createParamType(entry.getValue()) + ",";
            }
            tableMap.put(entry.getKey(), paramType);
            mark++;
        }
        return tableMap;
    }


    private String createParamType(String value) {
        if (ifNumType(value)) {
            return createNumSqlType(value);
        } else {
            if (ifDateSqlType(value)) {
                return createDateSqlType(value);
            } else {
                return getVarchar();
            }
        }
    }

    @SneakyThrows
    private boolean ifDateSqlType(String value) {
        final String[] parsePatterns = {"yyyy-MM-dd", "yyyy年MM月dd日",
                "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
                "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd"};
        try {
            DateUtils.parseDate(value, parsePatterns);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean ifNumType(String num) {
        try {
            BigDecimal bigDecimal = new BigDecimal(num);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private String createDateSqlType(String date) {
        if (date.contains(":")) {
            return getTimeStamp();
        } else {
            return getDate();
        }
    }

    private String createNumSqlType(String num) {
        if (num.contains(".")) {
            return getDecimal();
        } else {
            return getInteger();
        }
    }
}
