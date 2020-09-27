package org.github.json2sql.bean;

import org.github.json2sql.Json2sql;
import org.github.json2sql.api.JSONParser;
import org.github.json2sql.config.Configuration;

import static org.github.json2sql.Json2sql.*;

public abstract class AbstractJSONParser implements JSONParser{
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
}
