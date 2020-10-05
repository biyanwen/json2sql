package com.github.biyanwen.json2sql;

import com.github.biyanwen.json2sql.api.JSONWriter;
import com.github.biyanwen.json2sql.impl.DefaultJSONParser;
import com.github.biyanwen.json2sql.impl.DefaultJSONWriter;
import com.github.biyanwen.json2sql.api.JSONParser;
import com.github.biyanwen.json2sql.config.Configuration;

import java.util.Map;

public class Json2sql {
    public static final String DATE = "DATE";
    public static final String DECIMAL = "DECIMAL";
    public static final String INTEGER = "INTEGER";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String VARCHAR = "VARCHAR";

    private static Configuration configuration = new Configuration();

    /**
     * 将json解析成sql
     *
     * @param json      json字符串
     * @param tableName 表名字
     * @param path      生成sql文件存储路径 如果不传默认生成在当前项目目录
     */
    public static void parse(String json, String tableName, String... path) {
        Map<String, Object> sqlParamMap = parser(json, tableName);
        String outPath = null;
        if (path != null && path.length > 0) {
            outPath = path[0];
        }
        JSONWriter jsonWriter = new DefaultJSONWriter();
        jsonWriter.writer(sqlParamMap, outPath, tableName + ".sql");
    }

    public static String parse2String(String json, String tableName) {
        Map<String, Object> sqlParamMap = parser(json, tableName);
        JSONWriter jsonWriter = new DefaultJSONWriter();
        return jsonWriter.writer(sqlParamMap);
    }

    private static Map<String, Object> parser(String json, String tableName) {
        JSONParser parser = new DefaultJSONParser();
        return parser.parse(json, tableName);
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        Json2sql.configuration = configuration;
    }
}
