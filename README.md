# json2sql
English | [中文](README_ZH.md)

feature: **Json turn to a SQL**

## 一、Why do this?
It is mainly for **Data migration**，because my project conducts an integration test with an **H2 database**, and the project uses a **Mysql database** in a production environment, but it is embarrassing that the SQL syntax of the two databases is not same. so I build this tool to avoid overwriting the SQL that suits the H2 database.

## 二、maven address
```
<dependency>
    <groupId>com.github.biyanwen</groupId>
    <artifactId>json2sql</artifactId>
    <version>1.0.3</version>
</dependency>
```

## 三、Example 

### 1、Create a SQL file with JSON string.
**Json2sql** is the entry, and the parse method can create a SQL file, and it will create a file folder when the file folder is non-existent. It will create the SQL file at the current project path when a user is not input the path parameter.
```
        String json = "[{\"createTime\":\"2020-08-03 15:04:11\",\"updateTime\":\"2020-08-03 15:04:11\",\"operator\":null,\"id\":\"8a8d813873a480c80173b322850c2f24\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":0,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[24665.049000,24418.200000,24131.447000,23784.557000,23502.484000,23217.092000,23050.082000,22788.170000,22499.367000,22278.930000,22110.252000,21920.418000,21707.723000,21551.438000,21400.354000,21265.904000,21215.463000,21111.490000,21115.215000,21239.277000,21713.352000,22153.686000,22611.660000,23015.732000,23525.436000,23784.420000,23933.363000,23992.572000,24371.514000,24437.902000,24731.568000,25049.850000,25779.238000,26295.688000,26673.348000,26903.291000,27103.790000,27446.810000,27725.887000,28018.656000,28375.945000,28707.154000,29184.545000,29813.383000,30458.496000,30536.781000,30077.227000,28623.346000,27940.363000,27933.219000,27952.164000,27868.686000,28079.777000,28180.820000,28545.812000,28835.643000,29436.969000,29662.441000,30138.797000,30245.012000,30546.883000,30666.678000,30845.344000,30853.200000,31102.846000,31213.676000,31281.486000,31416.062000,31423.174000,31547.244000,31417.824000,31389.066000,31251.246000,31238.422000,31000.812000,30677.668000,30385.852000,30194.648000,30024.453000,30090.438000,30177.648000,30278.812000,30232.482000,30195.715000,30344.605000,30220.700000,29977.906000,29821.050000,29758.137000,29394.443000,28776.871000,28011.277000,27316.236000,26531.625000,25877.281000,25267.000000],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"},{\"createTime\":\"2020-08-03 15:04:47\",\"updateTime\":\"2020-08-03 15:04:47\",\"operator\":null,\"id\":\"8a8d813873a480c80173b32312cf346c\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":1,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[23265.00,23094.00,22937.00,22448.00,22331.00,22085.00,22151.00,21949.00,21745.00,21725.00,21359.00,21109.00,21007.00,20905.00,20814.00,20701.00,20565.00,20701.00,21041.00,21155.00,21433.00,22130.00,22335.00,22607.00,22937.00,23453.00,24036.00,24014.00,24238.00,24763.00,25181.00,25316.00,25854.00,26125.00,26488.00,26684.00,26919.00,26094.43,26224.86,26331.24,26630.50,26917.32,27501.22,27777.48,28403.98,28663.32,28323.51,27355.94,26822.06,26705.13,26840.22,26517.01,26922.40,26889.47,27039.62,27432.40,27872.70,28130.11,28489.76,28816.59,29157.44,29263.43,29504.01,29678.15,30021.97,30144.56,30232.35,30411.98,30489.50,30626.17,30577.50,30442.06,30523.13,30512.25,30313.19,30238.06,29917.01,29660.40,29663.70,29401.11,29295.23,29230.12,29176.71,29020.39,29020.17,28792.96,28752.50,28431.21,28376.79,27928.54,27581.31,26864.17,26492.52,25721.60,25285.71,24427.13],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"}]";
        //the first parameter：JSON string；the second parameter：table name（the name of SQL file name）;the third parameter：At the place, you can find the SQL file.
        Json2sql.parse2String(json, "EM_AREA_LOAD_FC","/home/work");
```

### 2、Create a SQL string with JSON string.
```
        String json = "[{\"createTime\":\"2020-08-03 15:04:11\",\"updateTime\":\"2020-08-03 15:04:11\",\"operator\":null,\"id\":\"8a8d813873a480c80173b322850c2f24\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":0,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[24665.049000,24418.200000,24131.447000,23784.557000,23502.484000,23217.092000,23050.082000,22788.170000,22499.367000,22278.930000,22110.252000,21920.418000,21707.723000,21551.438000,21400.354000,21265.904000,21215.463000,21111.490000,21115.215000,21239.277000,21713.352000,22153.686000,22611.660000,23015.732000,23525.436000,23784.420000,23933.363000,23992.572000,24371.514000,24437.902000,24731.568000,25049.850000,25779.238000,26295.688000,26673.348000,26903.291000,27103.790000,27446.810000,27725.887000,28018.656000,28375.945000,28707.154000,29184.545000,29813.383000,30458.496000,30536.781000,30077.227000,28623.346000,27940.363000,27933.219000,27952.164000,27868.686000,28079.777000,28180.820000,28545.812000,28835.643000,29436.969000,29662.441000,30138.797000,30245.012000,30546.883000,30666.678000,30845.344000,30853.200000,31102.846000,31213.676000,31281.486000,31416.062000,31423.174000,31547.244000,31417.824000,31389.066000,31251.246000,31238.422000,31000.812000,30677.668000,30385.852000,30194.648000,30024.453000,30090.438000,30177.648000,30278.812000,30232.482000,30195.715000,30344.605000,30220.700000,29977.906000,29821.050000,29758.137000,29394.443000,28776.871000,28011.277000,27316.236000,26531.625000,25877.281000,25267.000000],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"},{\"createTime\":\"2020-08-03 15:04:47\",\"updateTime\":\"2020-08-03 15:04:47\",\"operator\":null,\"id\":\"8a8d813873a480c80173b32312cf346c\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":1,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[23265.00,23094.00,22937.00,22448.00,22331.00,22085.00,22151.00,21949.00,21745.00,21725.00,21359.00,21109.00,21007.00,20905.00,20814.00,20701.00,20565.00,20701.00,21041.00,21155.00,21433.00,22130.00,22335.00,22607.00,22937.00,23453.00,24036.00,24014.00,24238.00,24763.00,25181.00,25316.00,25854.00,26125.00,26488.00,26684.00,26919.00,26094.43,26224.86,26331.24,26630.50,26917.32,27501.22,27777.48,28403.98,28663.32,28323.51,27355.94,26822.06,26705.13,26840.22,26517.01,26922.40,26889.47,27039.62,27432.40,27872.70,28130.11,28489.76,28816.59,29157.44,29263.43,29504.01,29678.15,30021.97,30144.56,30232.35,30411.98,30489.50,30626.17,30577.50,30442.06,30523.13,30512.25,30313.19,30238.06,29917.01,29660.40,29663.70,29401.11,29295.23,29230.12,29176.71,29020.39,29020.17,28792.96,28752.50,28431.21,28376.79,27928.54,27581.31,26864.17,26492.52,25721.60,25285.71,24427.13],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"}]";
        String emAreaLoadFc = Json2sql.parse2String(json, "EM_AREA_LOAD_FC");
```
### 3、Custom configuration

```
public class Configuration {
    /**
     * A length of the varchar type, it is default 255
     */
    private String varcharLength = "(255)";

    /**
     * A precision of decimal type, it is default (10,4)
     */
    private String decimalPrecision = "(10,4)";

    /**
     * This config is a rule that the JSON key is in upper case or lower case.
     */
    private KeyConversionConfig keyConversionConfig = KeyConversionConfig.nothing;

    /**
     * This config can register many handlers to manage the rule that how to create the SQL.
     * Key: The key of Javabean
     */
    private List<Map<String, BeanProcessor>> beanProcessorMaps = new ArrayList<>();

    /**
     * You can ignore some field.
     */
    private List<String> ignoreKeys = new ArrayList<>();

   //getter setter ...
}
```
#### 3.1 The example of the custom configuration.
You can use the ConfigurationBuilder when create the Configuration.

```
        String json = "[{\"createTime\":\"2020-08-03 15:04:11\",\"updateTime\":\"2020-08-03 15:04:11\",\"operator\":null,\"id\":\"8a8d813873a480c80173b322850c2f24\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":0,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[24665.049000,24418.200000,24131.447000,23784.557000,23502.484000,23217.092000,23050.082000,22788.170000,22499.367000,22278.930000,22110.252000,21920.418000,21707.723000,21551.438000,21400.354000,21265.904000,21215.463000,21111.490000,21115.215000,21239.277000,21713.352000,22153.686000,22611.660000,23015.732000,23525.436000,23784.420000,23933.363000,23992.572000,24371.514000,24437.902000,24731.568000,25049.850000,25779.238000,26295.688000,26673.348000,26903.291000,27103.790000,27446.810000,27725.887000,28018.656000,28375.945000,28707.154000,29184.545000,29813.383000,30458.496000,30536.781000,30077.227000,28623.346000,27940.363000,27933.219000,27952.164000,27868.686000,28079.777000,28180.820000,28545.812000,28835.643000,29436.969000,29662.441000,30138.797000,30245.012000,30546.883000,30666.678000,30845.344000,30853.200000,31102.846000,31213.676000,31281.486000,31416.062000,31423.174000,31547.244000,31417.824000,31389.066000,31251.246000,31238.422000,31000.812000,30677.668000,30385.852000,30194.648000,30024.453000,30090.438000,30177.648000,30278.812000,30232.482000,30195.715000,30344.605000,30220.700000,29977.906000,29821.050000,29758.137000,29394.443000,28776.871000,28011.277000,27316.236000,26531.625000,25877.281000,25267.000000],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"},{\"createTime\":\"2020-08-03 15:04:47\",\"updateTime\":\"2020-08-03 15:04:47\",\"operator\":null,\"id\":\"8a8d813873a480c80173b32312cf346c\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":1,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[23265.00,23094.00,22937.00,22448.00,22331.00,22085.00,22151.00,21949.00,21745.00,21725.00,21359.00,21109.00,21007.00,20905.00,20814.00,20701.00,20565.00,20701.00,21041.00,21155.00,21433.00,22130.00,22335.00,22607.00,22937.00,23453.00,24036.00,24014.00,24238.00,24763.00,25181.00,25316.00,25854.00,26125.00,26488.00,26684.00,26919.00,26094.43,26224.86,26331.24,26630.50,26917.32,27501.22,27777.48,28403.98,28663.32,28323.51,27355.94,26822.06,26705.13,26840.22,26517.01,26922.40,26889.47,27039.62,27432.40,27872.70,28130.11,28489.76,28816.59,29157.44,29263.43,29504.01,29678.15,30021.97,30144.56,30232.35,30411.98,30489.50,30626.17,30577.50,30442.06,30523.13,30512.25,30313.19,30238.06,29917.01,29660.40,29663.70,29401.11,29295.23,29230.12,29176.71,29020.39,29020.17,28792.96,28752.50,28431.21,28376.79,27928.54,27581.31,26864.17,26492.52,25721.60,25285.71,24427.13],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"}]";
        BeanProcessor<Map<String, Object>> tvMetaBeanProcess = new TvMetaBeanProcess();
        Configuration configuration = ConfigurationBuilder.config()
                .withBeanProcessorMap("tvMeta", tvMetaBeanProcess)//withBeanProcessorMap 
                .withKeyConversionConfig(KeyConversionConfig.hump2UnderscoreCapital)
                .withVarcharLength("5000")
                .withDecimalPrecision("10,2")
                .withIgnoreKeys("deviceId")
                .build(); 

        Json2sql.setConfiguration(configuration);
        String emAreaLoadFc = Json2sql.parse2String(json, "EM_AREA_LOAD_FC");

```
## 4、The result of the tool:
```
CREATE TABLE EM_AREA_LOAD_FC(
        areaId VARCHAR(255),
        startDateTime TIMESTAMP,
        size INTEGER,
        areaName VARCHAR(255),
        delta INTEGER,
        deltaUnit INTEGER,
        deviceId VARCHAR(255)
    );

        INSERT INTO EM_AREA_LOAD_FC (
            areaId,
            startDateTime,
            size,
            areaName,
            delta,
            deltaUnit,
            deviceId
        )
        VALUES (
            'FkNOycgY4s',
            '2020-07-28 00:00:00',
            '96',
            '河北',
            '15',
            '1',
            'FkNOycgY4s'
        );
        INSERT INTO EM_AREA_LOAD_FC (
            areaId,
            startDateTime,
            size,
            areaName,
            delta,
            deltaUnit,
            deviceId
        )
        VALUES (
            'FkNOycgY4s',
            '2020-07-28 00:00:00',
            '96',
            '河北',
            '15',
            '1',
            'FkNOycgY4s'
        );
```

### 5、PS:
This tool just can create 'create table' and 'insert' SQL, if you have other demands or questions, welcome to discuss the issue.

