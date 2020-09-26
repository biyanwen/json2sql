package org.github.json2sql.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.github.json2sql.api.JSONParser;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.github.json2sql.Json2sql.*;

public class DefaultJSONParser implements JSONParser {
    @SneakyThrows
    @Override
    public void parse(String json, String tableName) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> maps = objectMapper.readValue(json, new TypeReference<List<Map<String, String>>>() {
        });

        Map<String, String> tableMap = new HashMap<>();
        for (Map<String, String> map : maps) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String paramType = createParamType(entry.getValue());
                tableMap.put(entry.getKey(),paramType);
            }
        }
    }


    private String createParamType(String value) {
        if (ifNumType(value)) {
            return createNumSqlType(value);
        } else {
            if (ifDateSqlType(value)) {
                return createDateSqlType(value);
            } else {
                return VARCHAR;
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
            return TIMESTAMP;
        } else {
            return DATE;
        }
    }

    private String createNumSqlType(String num) {
        if (num.contains(".")) {
            return DECIMAL;
        } else {
            return INTEGER;
        }
    }
}
