package org.github.json2sql;

import org.github.json2sql.api.JSONParser;
import org.github.json2sql.config.Configuration;
import org.github.json2sql.impl.DefaultJSONParser;

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
     * @param path      生成sql文件存储路径
     */
    public static void parse(String json, String tableName, String... path) {
        JSONParser parser = new DefaultJSONParser();
        parser.parse(json, tableName);
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        Json2sql.configuration = configuration;
    }
}
