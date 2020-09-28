package org.github.json2sql.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.github.json2sql.api.JSONWriter;
import org.github.json2sql.bean.AbstractJSONParser;
import org.github.json2sql.bean.InsertDTO;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


public class DefaultJSONParser extends AbstractJSONParser {
    @SneakyThrows
    @Override
    public void parse(String json, String tableName) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> maps = objectMapper.readValue(json, new TypeReference<List<Map<String, String>>>() {
        });


        Map<String, String> createTableNeedParamMap = createCreateTableMap(maps);
        List<InsertDTO> insertDTOS = createCRUDTableDTO(maps);

        Map<String, Object> sqlParamMap = new HashMap<>();
        sqlParamMap.put("tableName", tableName);
        sqlParamMap.put("tableParam", createTableNeedParamMap);
        sqlParamMap.put("insertParam", insertDTOS);

        JSONWriter jsonWriter = new DefaultJSONWriter();
        jsonWriter.writer(sqlParamMap, null,"test.sql");
    }

    private List<InsertDTO> createCRUDTableDTO(List<Map<String, String>> maps) {
        List<Map<String, String>> tableMaps = createTableMap(maps, t -> t);
        return createInsertDTO(tableMaps);
    }

    private List<InsertDTO> createInsertDTO(List<Map<String, String>> tableMaps) {
        return tableMaps.stream().map(tableMap -> {
            InsertDTO insertDTO = new InsertDTO();
            insertDTO.setKeys(new ArrayList<>(tableMap.keySet()));
            insertDTO.setValues(new ArrayList<>(tableMap.values()));
            return insertDTO;
        }).collect(Collectors.toList());
    }

    private Map<String, String> createCreateTableMap(List<Map<String, String>> maps) {
        Map<String, String> paramMap = maps.get(0);
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
