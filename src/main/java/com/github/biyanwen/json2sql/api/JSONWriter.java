package com.github.biyanwen.json2sql.api;

import java.util.Map;

/**
 * Description:  <br>
 *
 * @author byw
 * @create 2020/9/27
 */
public interface JSONWriter {
    void writer(Map<String, Object> sqlParamMap, String path,String sqlFileName);


    String writer(Map<String, Object> sqlParamMap);
}
