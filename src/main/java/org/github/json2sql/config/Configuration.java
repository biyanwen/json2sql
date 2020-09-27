package org.github.json2sql.config;

public class Configuration {
    /**
     * varchar 类型的长度 默认 255
     */
    private String varcharLength = "(255)";

    /**
     * decimal 类型精度 默认 (10,4)
     */
    private String decimalPrecision = "(10,4)";


    public String getVarcharLength() {
        return varcharLength;
    }

    public void setVarcharLength(String varcharLength) {
        this.varcharLength = varcharLength;
    }

    public String getDecimalPrecision() {
        return decimalPrecision;
    }

    public void setDecimalPrecision(String decimalPrecision) {
        this.decimalPrecision = decimalPrecision;
    }
}
