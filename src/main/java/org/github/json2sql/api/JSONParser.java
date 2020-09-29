package org.github.json2sql.api;

import java.util.Map;

public interface JSONParser {

    Map<String, Object> parse(String json, String tableName);
}
