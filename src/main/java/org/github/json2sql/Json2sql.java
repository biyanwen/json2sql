package org.github.json2sql;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.github.json2sql.api.JSONParser;
import org.github.json2sql.impl.DefaultJSONParser;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Json2sql {
    public static final String DATE = "DATE";
    public static final String DECIMAL = "DECIMAL";
    public static final String INTEGER = "INTEGER";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String VARCHAR = "VARCHAR";

    /**
     * 将json解析成sql
     *
     * @param json      json字符串
     * @param tableName 表名字
     * @param path      生成sql文件存储路径
     */
    public static void parse(String json, String tableName, String... path) {
        JSONParser parser = new DefaultJSONParser();
        parser.parse(json, tableName);
    }


}
