package com.github.biyanwen.json2sql.bean;

import java.util.List;

public class InsertDTO {
    private List<String> keys;

    private List<String> values;

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
