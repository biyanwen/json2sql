package org.github.json2sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class Json2sqlTest {

    @Test
    public void test() throws JsonProcessingException {
        String str = "[{ \"componentKey\" : \"CMDB_UPDATE_SERVER\", \"componentKey2\":\"CMDB_UPDATE_SERVER\"},{ \"componentKey\" : \"CMDB_UPDATE_SERVER\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> maps = objectMapper.readValue(str, new TypeReference<List<Map<String, String>>>() {
        });
        for (Map<String, String> map : maps) {
            map.forEach((k, v) -> System.out.println(k + " " + v));
        }

        System.out.println();

    }

    @Test
    public void test_num() throws ParseException {
        final String[] parsePatterns = {"yyyy-MM-dd","yyyy年MM月dd日",
                "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
                "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd"};

        String value = "22313";
        Date date = DateUtils.parseDate(value, parsePatterns);
        System.out.println(date);
    }

}