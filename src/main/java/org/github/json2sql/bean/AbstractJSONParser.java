package org.github.json2sql.bean;

import org.github.json2sql.Json2sql;
import org.github.json2sql.api.JSONParser;
import org.github.json2sql.config.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.github.json2sql.Json2sql.*;

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

    protected List<Map<String, String>> createTableMap(List<Map<String, String>> maps, Function<String, String> function) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Map<String, String> map : maps) {
            Map<String, String> tableMap = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String paramType;
                paramType = function.apply(entry.getValue());
                tableMap.put(entry.getKey(), paramType);
            }
            mapList.add(tableMap);
        }
        return mapList;
    }


}
