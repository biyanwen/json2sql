package org.github.json2sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

class Json2sqlTest {

    @Test
    public void test() throws JsonProcessingException {
        String str = "[{ \"componentKey\" : \"CMDB_UPDATE_SERVER\", \"componentKey2\": 10023.5432},{ \"componentKey\" : \"CMDB_UPDATE_SERVER\", \"componentKey2\": 10023.5432}]";
        Json2sql.parse(str, "MY_TABLE");
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
        String str = "[{ \"componentKey\" : \"CMDB_UPDATE_SERVER\", \"componentKey2\":\"CMDB_UPDATE_SERVER\"},{ \"componentKey\" : \"CMDB_UPDATE_SERVER\"}]";
        Json2sql.parse(str, null);
    }

}