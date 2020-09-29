package org.github.json2sql.api;

import java.util.Map;

/**
 * Description:  <br>
 * 当json中有嵌套对象的时候用此接口处理
 *
 * @author byw
 * @create 2020/9/29
 */
public interface BeanProcessor<T> {
    /**
     * @param t   javabean类型
     * @return
     */
    Map<String, String> processor( T t);
}
