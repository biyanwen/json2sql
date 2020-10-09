package com.github.biyanwen.json2sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.biyanwen.json2sql.api.BeanProcessor;
import com.github.biyanwen.json2sql.enums.KeyConversionConfig;
import com.github.biyanwen.json2sql.config.Configuration;
import com.github.biyanwen.json2sql.config.ConfigurationBuilder;
import com.google.common.base.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Json2sqlTest {

    @Test
    public void test_for_parse() throws JsonProcessingException {
        String json = "[{\"createTime\":\"2020-08-03 15:04:11\",\"updateTime\":\"2020-08-03 15:04:11\",\"operator\":null,\"id\":\"8a8d813873a480c80173b322850c2f24\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":0,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[24665.049000,24418.200000,24131.447000,23784.557000,23502.484000,23217.092000,23050.082000,22788.170000,22499.367000,22278.930000,22110.252000,21920.418000,21707.723000,21551.438000,21400.354000,21265.904000,21215.463000,21111.490000,21115.215000,21239.277000,21713.352000,22153.686000,22611.660000,23015.732000,23525.436000,23784.420000,23933.363000,23992.572000,24371.514000,24437.902000,24731.568000,25049.850000,25779.238000,26295.688000,26673.348000,26903.291000,27103.790000,27446.810000,27725.887000,28018.656000,28375.945000,28707.154000,29184.545000,29813.383000,30458.496000,30536.781000,30077.227000,28623.346000,27940.363000,27933.219000,27952.164000,27868.686000,28079.777000,28180.820000,28545.812000,28835.643000,29436.969000,29662.441000,30138.797000,30245.012000,30546.883000,30666.678000,30845.344000,30853.200000,31102.846000,31213.676000,31281.486000,31416.062000,31423.174000,31547.244000,31417.824000,31389.066000,31251.246000,31238.422000,31000.812000,30677.668000,30385.852000,30194.648000,30024.453000,30090.438000,30177.648000,30278.812000,30232.482000,30195.715000,30344.605000,30220.700000,29977.906000,29821.050000,29758.137000,29394.443000,28776.871000,28011.277000,27316.236000,26531.625000,25877.281000,25267.000000],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"},{\"createTime\":\"2020-08-03 15:04:47\",\"updateTime\":\"2020-08-03 15:04:47\",\"operator\":null,\"id\":\"8a8d813873a480c80173b32312cf346c\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":1,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[23265.00,23094.00,22937.00,22448.00,22331.00,22085.00,22151.00,21949.00,21745.00,21725.00,21359.00,21109.00,21007.00,20905.00,20814.00,20701.00,20565.00,20701.00,21041.00,21155.00,21433.00,22130.00,22335.00,22607.00,22937.00,23453.00,24036.00,24014.00,24238.00,24763.00,25181.00,25316.00,25854.00,26125.00,26488.00,26684.00,26919.00,26094.43,26224.86,26331.24,26630.50,26917.32,27501.22,27777.48,28403.98,28663.32,28323.51,27355.94,26822.06,26705.13,26840.22,26517.01,26922.40,26889.47,27039.62,27432.40,27872.70,28130.11,28489.76,28816.59,29157.44,29263.43,29504.01,29678.15,30021.97,30144.56,30232.35,30411.98,30489.50,30626.17,30577.50,30442.06,30523.13,30512.25,30313.19,30238.06,29917.01,29660.40,29663.70,29401.11,29295.23,29230.12,29176.71,29020.39,29020.17,28792.96,28752.50,28431.21,28376.79,27928.54,27581.31,26864.17,26492.52,25721.60,25285.71,24427.13],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"}]";
        BeanProcessor<Map<String, Object>> tvMetaBeanProcess = new TvMetaBeanProcess();
        Configuration configuration = ConfigurationBuilder.config()
                .withBeanProcessorMap("tvMeta", tvMetaBeanProcess)
                .withKeyConversionConfig(KeyConversionConfig.hump2UnderscoreCapital)
                .withIgnoreKeys("deviceId").build();

        Json2sql.setConfiguration(configuration);
        String emAreaLoadFc = Json2sql.parse2String(json, "EM_AREA_LOAD_FC");
        boolean equals = emAreaLoadFc.equals("    CREATE TABLE EM_AREA_LOAD_FC(\n" +
                "        DATE TIMESTAMP,\n" +
                "        T_DELTA_UNIT INTEGER,\n" +
                "        TV_CURVE_DATA VARCHAR(255),\n" +
                "        T_START_DATE_TIME TIMESTAMP,\n" +
                "        UPDATE_TIME TIMESTAMP,\n" +
                "        T_SIZE INTEGER,\n" +
                "        OPERATOR VARCHAR(255),\n" +
                "        AREA_ID VARCHAR(255),\n" +
                "        SCHEDULE_TYPE INTEGER,\n" +
                "        T_DELTA INTEGER,\n" +
                "        CREATE_TIME TIMESTAMP,\n" +
                "        AREA_NAME VARCHAR(255),\n" +
                "        ID VARCHAR(255)\n" +
                "    );\n" +
                "\n" +
                "        INSERT INTO EM_AREA_LOAD_FC (\n" +
                "            DATE,\n" +
                "            T_DELTA_UNIT,\n" +
                "            TV_CURVE_DATA,\n" +
                "            T_START_DATE_TIME,\n" +
                "            UPDATE_TIME,\n" +
                "            T_SIZE,\n" +
                "            AREA_ID,\n" +
                "            SCHEDULE_TYPE,\n" +
                "            T_DELTA,\n" +
                "            CREATE_TIME,\n" +
                "            AREA_NAME,\n" +
                "            ID\n" +
                "        )\n" +
                "        VALUES (\n" +
                "            '2020-07-28 00:00:00',\n" +
                "            '1',\n" +
                "            '[24665.049, 24418.2, 24131.447, 23784.557, 23502.484, 23217.092, 23050.082, 22788.17, 22499.367, 22278.93, 22110.252, 21920.418, 21707.723, 21551.438, 21400.354, 21265.904, 21215.463, 21111.49, 21115.215, 21239.277, 21713.352, 22153.686, 22611.66, 23015.732, 23525.436, 23784.42, 23933.363, 23992.572, 24371.514, 24437.902, 24731.568, 25049.85, 25779.238, 26295.688, 26673.348, 26903.291, 27103.79, 27446.81, 27725.887, 28018.656, 28375.945, 28707.154, 29184.545, 29813.383, 30458.496, 30536.781, 30077.227, 28623.346, 27940.363, 27933.219, 27952.164, 27868.686, 28079.777, 28180.82, 28545.812, 28835.643, 29436.969, 29662.441, 30138.797, 30245.012, 30546.883, 30666.678, 30845.344, 30853.2, 31102.846, 31213.676, 31281.486, 31416.062, 31423.174, 31547.244, 31417.824, 31389.066, 31251.246, 31238.422, 31000.812, 30677.668, 30385.852, 30194.648, 30024.453, 30090.438, 30177.648, 30278.812, 30232.482, 30195.715, 30344.605, 30220.7, 29977.906, 29821.05, 29758.137, 29394.443, 28776.871, 28011.277, 27316.236, 26531.625, 25877.281, 25267.0]',\n" +
                "            '2020-07-28 00:00:00',\n" +
                "            '2020-08-03 15:04:11',\n" +
                "            '96',\n" +
                "            'FkNOycgY4s',\n" +
                "            '0',\n" +
                "            '15',\n" +
                "            '2020-08-03 15:04:11',\n" +
                "            '河北',\n" +
                "            '8a8d813873a480c80173b322850c2f24'\n" +
                "        );\n" +
                "        INSERT INTO EM_AREA_LOAD_FC (\n" +
                "            DATE,\n" +
                "            T_DELTA_UNIT,\n" +
                "            TV_CURVE_DATA,\n" +
                "            T_START_DATE_TIME,\n" +
                "            UPDATE_TIME,\n" +
                "            T_SIZE,\n" +
                "            AREA_ID,\n" +
                "            SCHEDULE_TYPE,\n" +
                "            T_DELTA,\n" +
                "            CREATE_TIME,\n" +
                "            AREA_NAME,\n" +
                "            ID\n" +
                "        )\n" +
                "        VALUES (\n" +
                "            '2020-07-28 00:00:00',\n" +
                "            '1',\n" +
                "            '[23265.0, 23094.0, 22937.0, 22448.0, 22331.0, 22085.0, 22151.0, 21949.0, 21745.0, 21725.0, 21359.0, 21109.0, 21007.0, 20905.0, 20814.0, 20701.0, 20565.0, 20701.0, 21041.0, 21155.0, 21433.0, 22130.0, 22335.0, 22607.0, 22937.0, 23453.0, 24036.0, 24014.0, 24238.0, 24763.0, 25181.0, 25316.0, 25854.0, 26125.0, 26488.0, 26684.0, 26919.0, 26094.43, 26224.86, 26331.24, 26630.5, 26917.32, 27501.22, 27777.48, 28403.98, 28663.32, 28323.51, 27355.94, 26822.06, 26705.13, 26840.22, 26517.01, 26922.4, 26889.47, 27039.62, 27432.4, 27872.7, 28130.11, 28489.76, 28816.59, 29157.44, 29263.43, 29504.01, 29678.15, 30021.97, 30144.56, 30232.35, 30411.98, 30489.5, 30626.17, 30577.5, 30442.06, 30523.13, 30512.25, 30313.19, 30238.06, 29917.01, 29660.4, 29663.7, 29401.11, 29295.23, 29230.12, 29176.71, 29020.39, 29020.17, 28792.96, 28752.5, 28431.21, 28376.79, 27928.54, 27581.31, 26864.17, 26492.52, 25721.6, 25285.71, 24427.13]',\n" +
                "            '2020-07-28 00:00:00',\n" +
                "            '2020-08-03 15:04:47',\n" +
                "            '96',\n" +
                "            'FkNOycgY4s',\n" +
                "            '1',\n" +
                "            '15',\n" +
                "            '2020-08-03 15:04:47',\n" +
                "            '河北',\n" +
                "            '8a8d813873a480c80173b32312cf346c'\n" +
                "        );\n");
        Assertions.assertTrue(equals);
    }

    @Test
    public void test_for_nest() {
        String json = "[{\n" +
                "\t\"tvMeta\": {\n" +
                "\t\t\"startDateTime\": \"2020-07-28 00:00:00\",\n" +
                "\t\t\"delta\": 15,\n" +
                "\t\t\"deltaUnit\": 1,\n" +
                "\t\t\"size\": 96\n" +
                "\t},\n" +
                "\t\"areaName\": \"河北\",\n" +
                "\t\"areaId\": \"FkNOycgY4s\",\n" +
                "\t\"deviceId\": \"FkNOycgY4s\"\n" +
                "}, {\n" +
                "\t\"tvMeta\": {\n" +
                "\t\t\"startDateTime\": \"2020-07-28 00:00:00\",\n" +
                "\t\t\"delta\": 15,\n" +
                "\t\t\"deltaUnit\": 1,\n" +
                "\t\t\"size\": 96\n" +
                "\t},\n" +
                "\t\"areaName\": \"河北\",\n" +
                "\t\"areaId\": \"FkNOycgY4s\",\n" +
                "\t\"deviceId\": \"FkNOycgY4s\"\n" +
                "}]";

        String emAreaLoadFc = Json2sql.parse2String(json, "EM_AREA_LOAD_FC");
        boolean startDateTime_timestamp = emAreaLoadFc.contains("startDateTime TIMESTAMP");
        boolean size = emAreaLoadFc.contains("size INTEGER,");
        boolean delta = emAreaLoadFc.contains("delta INTEGER,");
        boolean deltaUnit = emAreaLoadFc.contains("deltaUnit INTEGER,");
        Assertions.assertTrue(startDateTime_timestamp && size && delta && deltaUnit);
    }

    @Test
    public void test_for_config_builder() {
        Map<String, BeanProcessor> map = new HashMap<>();
        BeanProcessor<Map<String, Object>> tvMetaBeanProcess = new TvMetaBeanProcess();
        map.put("tvMeta", tvMetaBeanProcess);
        List<Map<String, BeanProcessor>> beanProcessorMaps = Collections.singletonList(map);
        Configuration configuration = ConfigurationBuilder.config()
                .withVarcharLength("5000")
                .withDecimalPrecision("10,2")
                .withBeanProcessorMaps(beanProcessorMaps)
                .build();

        Assertions.assertEquals("(5000)", configuration.getVarcharLength());
        Assertions.assertEquals("(10,2)", configuration.getDecimalPrecision());
        Assertions.assertEquals(1, configuration.getBeanProcessorMaps().get(0).size());
        Assertions.assertTrue(configuration.getBeanProcessorMaps().get(0).containsKey("tvMeta"));
    }

    @Test
    public void test_for_Parser_get() {
        String json = "[{\n" +
                "\t\"tvMeta\": {\n" +
                "\t\t\"startDateTime\": \"2020-07-28\",\n" +
                "\t\t\"delta\": 15,\n" +
                "\t\t\"deltaUnit\": 1,\n" +
                "\t\t\"size\": 96\n" +
                "\t},\n" +
                "\t\"load\": \"1313.1313\",\n" +
                "\t\"areaId\": \"FkNOycgY4s\",\n" +
                "\t\"deviceId\": \"FkNOycgY4s\"\n" +
                "}, {\n" +
                "\t\"tvMeta\": {\n" +
                "\t\t\"startDateTime\": \"2020-07-28\",\n" +
                "\t\t\"delta\": 15,\n" +
                "\t\t\"deltaUnit\": 1,\n" +
                "\t\t\"size\": 96\n" +
                "\t},\n" +
                "\t\"load\": \"1313.1313\",\n" +
                "\t\"areaId\": \"FkNOycgY4s\",\n" +
                "\t\"deviceId\": \"FkNOycgY4s\"\n" +
                "}]";
        String emAreaLoadFc = Json2sql.parse2String(json, "EM_AREA_LOAD_FC");

        boolean load = emAreaLoadFc.contains("load DECIMAL(10,4),");
        boolean date = emAreaLoadFc.contains("startDateTime DATE,");
        Assertions.assertTrue(load && date);
    }

    @Test
    public void test_for_config() {
        BeanProcessor<Map<String, Object>> tvMetaBeanProcess = new TvMetaBeanProcess();
        Configuration configuration = new Configuration();
        configuration.setIgnoreKeys("id");
        configuration.registerBeanProcessor("tvMeta", tvMetaBeanProcess);
        configuration.setVarcharLength("500");
        configuration.setDecimalPrecision("10,2");

        Assertions.assertEquals("id", configuration.getIgnoreKeys().get(0));
        Assertions.assertTrue(configuration.getBeanProcessorMaps().get(0).containsKey("tvMeta"));
        Assertions.assertEquals("(500)",configuration.getVarcharLength());
        Assertions.assertEquals("(10,2)",configuration.getDecimalPrecision());
    }

    @Test
    public void test_for_single_field(){
        String json = "[{\"createTime\":\"2020-08-03 15:04:11\",\"updateTime\":\"2020-08-03 15:04:11\",\"operator\":null,\"id\":\"8a8d813873a480c80173b322850c2f24\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":0,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[24665.049000,24418.200000,24131.447000,23784.557000,23502.484000,23217.092000,23050.082000,22788.170000,22499.367000,22278.930000,22110.252000,21920.418000,21707.723000,21551.438000,21400.354000,21265.904000,21215.463000,21111.490000,21115.215000,21239.277000,21713.352000,22153.686000,22611.660000,23015.732000,23525.436000,23784.420000,23933.363000,23992.572000,24371.514000,24437.902000,24731.568000,25049.850000,25779.238000,26295.688000,26673.348000,26903.291000,27103.790000,27446.810000,27725.887000,28018.656000,28375.945000,28707.154000,29184.545000,29813.383000,30458.496000,30536.781000,30077.227000,28623.346000,27940.363000,27933.219000,27952.164000,27868.686000,28079.777000,28180.820000,28545.812000,28835.643000,29436.969000,29662.441000,30138.797000,30245.012000,30546.883000,30666.678000,30845.344000,30853.200000,31102.846000,31213.676000,31281.486000,31416.062000,31423.174000,31547.244000,31417.824000,31389.066000,31251.246000,31238.422000,31000.812000,30677.668000,30385.852000,30194.648000,30024.453000,30090.438000,30177.648000,30278.812000,30232.482000,30195.715000,30344.605000,30220.700000,29977.906000,29821.050000,29758.137000,29394.443000,28776.871000,28011.277000,27316.236000,26531.625000,25877.281000,25267.000000],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"},{\"createTime\":\"2020-08-03 15:04:47\",\"updateTime\":\"2020-08-03 15:04:47\",\"operator\":null,\"id\":\"8a8d813873a480c80173b32312cf346c\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":1,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[23265.00,23094.00,22937.00,22448.00,22331.00,22085.00,22151.00,21949.00,21745.00,21725.00,21359.00,21109.00,21007.00,20905.00,20814.00,20701.00,20565.00,20701.00,21041.00,21155.00,21433.00,22130.00,22335.00,22607.00,22937.00,23453.00,24036.00,24014.00,24238.00,24763.00,25181.00,25316.00,25854.00,26125.00,26488.00,26684.00,26919.00,26094.43,26224.86,26331.24,26630.50,26917.32,27501.22,27777.48,28403.98,28663.32,28323.51,27355.94,26822.06,26705.13,26840.22,26517.01,26922.40,26889.47,27039.62,27432.40,27872.70,28130.11,28489.76,28816.59,29157.44,29263.43,29504.01,29678.15,30021.97,30144.56,30232.35,30411.98,30489.50,30626.17,30577.50,30442.06,30523.13,30512.25,30313.19,30238.06,29917.01,29660.40,29663.70,29401.11,29295.23,29230.12,29176.71,29020.39,29020.17,28792.96,28752.50,28431.21,28376.79,27928.54,27581.31,26864.17,26492.52,25721.60,25285.71,24427.13],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"}]";
        BeanProcessor<Map<String, Object>> singleFiledProcess = new SingleFiledProcess();
        Configuration configuration = ConfigurationBuilder.config()
                .withBeanProcessorMap("scheduleType", singleFiledProcess)
                .build();
        Json2sql.setConfiguration(configuration);
        String em_area_load_fc = Json2sql.parse2String(json, "EM_AREA_LOAD_FC");
        boolean contains = em_area_load_fc.contains("t_scheduleType INTEGER");
        Assertions.assertTrue(contains);
    }

    @Test
    public void test_for_single_json(){
        String json = "{\"createTime\":\"2020-08-03 15:04:11\",\"updateTime\":\"2020-08-03 15:04:11\",\"operator\":null,\"id\":\"8a8d813873a480c80173b322850c2f24\",\"date\":\"2020-07-28 00:00:00\",\"scheduleType\":0,\"tvMeta\":{\"startDateTime\":\"2020-07-28 00:00:00\",\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[24665.049000,24418.200000,24131.447000,23784.557000,23502.484000,23217.092000,23050.082000,22788.170000,22499.367000,22278.930000,22110.252000,21920.418000,21707.723000,21551.438000,21400.354000,21265.904000,21215.463000,21111.490000,21115.215000,21239.277000,21713.352000,22153.686000,22611.660000,23015.732000,23525.436000,23784.420000,23933.363000,23992.572000,24371.514000,24437.902000,24731.568000,25049.850000,25779.238000,26295.688000,26673.348000,26903.291000,27103.790000,27446.810000,27725.887000,28018.656000,28375.945000,28707.154000,29184.545000,29813.383000,30458.496000,30536.781000,30077.227000,28623.346000,27940.363000,27933.219000,27952.164000,27868.686000,28079.777000,28180.820000,28545.812000,28835.643000,29436.969000,29662.441000,30138.797000,30245.012000,30546.883000,30666.678000,30845.344000,30853.200000,31102.846000,31213.676000,31281.486000,31416.062000,31423.174000,31547.244000,31417.824000,31389.066000,31251.246000,31238.422000,31000.812000,30677.668000,30385.852000,30194.648000,30024.453000,30090.438000,30177.648000,30278.812000,30232.482000,30195.715000,30344.605000,30220.700000,29977.906000,29821.050000,29758.137000,29394.443000,28776.871000,28011.277000,27316.236000,26531.625000,25877.281000,25267.000000],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"}";
        String em_area_load_fc = Json2sql.parse2String(json, "EM_AREA_LOAD_FC");
        boolean nullOrEmpty = Strings.isNullOrEmpty(em_area_load_fc);
        Assertions.assertFalse(nullOrEmpty);
    }
}