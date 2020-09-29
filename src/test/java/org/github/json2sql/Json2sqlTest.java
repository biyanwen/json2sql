package org.github.json2sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.github.json2sql.api.BeanProcessor;
import org.github.json2sql.config.Configuration;
import org.github.json2sql.enums.KeyConversionConfig;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

class Json2sqlTest {

    @Test
    public void test() throws JsonProcessingException {
        String str = "[{ \"componentKey\" : \"CMDB_UPDATE_SERVER\", \"componentKey2\": 10023.5432},{ \"componentKey\" : \"CMDB_UPDATE_SERVER\", \"componentKey2\": 10023.5432}]";
        Json2sql.parse(str, "MY_TABLE");
    }

    @Test
    public void test_for_object() throws JsonProcessingException {
        String json = "[{\"createTime\":1596438251000,\"updateTime\":1596438251000,\"operator\":null,\"id\":\"8a8d813873a480c80173b322850c2f24\",\"date\":1595865600000,\"scheduleType\":0,\"tvMeta\":{\"startDateTime\":1595865600000,\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[24665.049000,24418.200000,24131.447000,23784.557000,23502.484000,23217.092000,23050.082000,22788.170000,22499.367000,22278.930000,22110.252000,21920.418000,21707.723000,21551.438000,21400.354000,21265.904000,21215.463000,21111.490000,21115.215000,21239.277000,21713.352000,22153.686000,22611.660000,23015.732000,23525.436000,23784.420000,23933.363000,23992.572000,24371.514000,24437.902000,24731.568000,25049.850000,25779.238000,26295.688000,26673.348000,26903.291000,27103.790000,27446.810000,27725.887000,28018.656000,28375.945000,28707.154000,29184.545000,29813.383000,30458.496000,30536.781000,30077.227000,28623.346000,27940.363000,27933.219000,27952.164000,27868.686000,28079.777000,28180.820000,28545.812000,28835.643000,29436.969000,29662.441000,30138.797000,30245.012000,30546.883000,30666.678000,30845.344000,30853.200000,31102.846000,31213.676000,31281.486000,31416.062000,31423.174000,31547.244000,31417.824000,31389.066000,31251.246000,31238.422000,31000.812000,30677.668000,30385.852000,30194.648000,30024.453000,30090.438000,30177.648000,30278.812000,30232.482000,30195.715000,30344.605000,30220.700000,29977.906000,29821.050000,29758.137000,29394.443000,28776.871000,28011.277000,27316.236000,26531.625000,25877.281000,25267.000000],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"},{\"createTime\":1596438287000,\"updateTime\":1596438287000,\"operator\":null,\"id\":\"8a8d813873a480c80173b32312cf346c\",\"date\":1595865600000,\"scheduleType\":1,\"tvMeta\":{\"startDateTime\":1595865600000,\"delta\":15,\"deltaUnit\":1,\"size\":96},\"tvCurveData\":[23265.00,23094.00,22937.00,22448.00,22331.00,22085.00,22151.00,21949.00,21745.00,21725.00,21359.00,21109.00,21007.00,20905.00,20814.00,20701.00,20565.00,20701.00,21041.00,21155.00,21433.00,22130.00,22335.00,22607.00,22937.00,23453.00,24036.00,24014.00,24238.00,24763.00,25181.00,25316.00,25854.00,26125.00,26488.00,26684.00,26919.00,26094.43,26224.86,26331.24,26630.50,26917.32,27501.22,27777.48,28403.98,28663.32,28323.51,27355.94,26822.06,26705.13,26840.22,26517.01,26922.40,26889.47,27039.62,27432.40,27872.70,28130.11,28489.76,28816.59,29157.44,29263.43,29504.01,29678.15,30021.97,30144.56,30232.35,30411.98,30489.50,30626.17,30577.50,30442.06,30523.13,30512.25,30313.19,30238.06,29917.01,29660.40,29663.70,29401.11,29295.23,29230.12,29176.71,29020.39,29020.17,28792.96,28752.50,28431.21,28376.79,27928.54,27581.31,26864.17,26492.52,25721.60,25285.71,24427.13],\"areaName\":\"河北\",\"areaId\":\"FkNOycgY4s\",\"deviceId\":\"FkNOycgY4s\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> maps = objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {
        });

        BeanProcessor<Map<String, Object>> tvMetaBeanProcess = new TvMetaBeanProcess();
        Configuration configuration = new Configuration();
        configuration.registerBeanProcessor("tvMeta", tvMetaBeanProcess);
        configuration.setKeyConversionConfig(KeyConversionConfig.hump2UnderscoreCapital);
        Json2sql.setConfiguration(configuration);
        Json2sql.parse(json,"EM_AREA_LOAD_FC");
        System.out.println();
    }

    @Test
    public void test_num() throws ParseException {
        final String[] parsePatterns = {"yyyy-MM-dd", "yyyy年MM月dd日",
                "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
                "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd"};

        String value = "22313";
        Date date = DateUtils.parseDate(value, parsePatterns);
        System.out.println(date);
    }

    @Test
    public void test_parse() {
        Double d = Double.valueOf("666");
        BigDecimal decimal = new BigDecimal("12.00");
        System.out.println(d.toString());
        System.out.println(decimal.toString());
    }

}