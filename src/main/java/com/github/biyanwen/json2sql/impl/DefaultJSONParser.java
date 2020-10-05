package com.github.biyanwen.json2sql.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import com.github.biyanwen.json2sql.bean.AbstractJSONParser;
import com.github.biyanwen.json2sql.bean.InsertDTO;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


public class DefaultJSONParser extends AbstractJSONParser {
    @SneakyThrows
    @Override
    public Map<String, Object> parse(String json, String tableName) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> maps = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {
        });

        Map<String, String> createTableNeedParamMap = createCreateTableMap(maps);
        List<InsertDTO> insertDTOS = createCRUDTableDTO(maps);

        Map<String, Object> sqlParamMap = new HashMap<>();
        sqlParamMap.put("tableName", tableName);
        sqlParamMap.put("tableParam", createTableNeedParamMap);
        sqlParamMap.put("insertParam", insertDTOS);

        return sqlParamMap;
    }

    private List<InsertDTO> createCRUDTableDTO(List<Map<String, Object>> maps) {
        List<Map<String, String>> tableMaps = createTableMap(maps, t -> t);
        //如果值为null 就不会生成对应字段的sql语句
        List<Map<String, String>> tableMapsNotNull = filterNotNull(tableMaps);
        return createInsertDTO(tableMapsNotNull);
    }

    private List<Map<String, String>> filterNotNull(List<Map<String, String>> tableMaps) {
        List<Map<String, String>> tmpList = new ArrayList<>(tableMaps);
        Map.Entry<String, String> entry;
        List<String> keys = new ArrayList<>();
        for (Map<String, String> map : tmpList) {
            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                entry = stringStringEntry;
                if (entry.getValue() != null) {
                    continue;
                }
                keys.add(entry.getKey());
            }
            for (String key : keys) {
                map.remove(key);
            }
        }
        return tmpList;
    }

    private List<InsertDTO> createInsertDTO(List<Map<String, String>> tableMaps) {
        return tableMaps.stream().map(tableMap -> {
            InsertDTO insertDTO = new InsertDTO();
            insertDTO.setKeys(new ArrayList<>(tableMap.keySet()));
            insertDTO.setValues(new ArrayList<>(tableMap.values()));
            return insertDTO;
        }).collect(Collectors.toList());
    }

    private Map<String, String> createCreateTableMap(List<Map<String, Object>> maps) {
        Map<String, Object> paramMap = maps.get(0);
        List<Map<String, String>> tableMaps = createTableMap(Collections.singletonList(paramMap), this::createParamType);
        if (CollectionUtils.isEmpty(tableMaps)) {
            return new HashMap<>();
        }
        return tableMaps.get(0);
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
        if (value == null) {
            return false;
        }
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
            if (num == null) {
                return false;
            }
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
