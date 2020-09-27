package org.github.json2sql.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.github.json2sql.Main;
import org.github.json2sql.api.JSONWriter;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Description:  <br>
 *
 * @author byw
 * @create 2020/9/27
 */
public class DefaultJSONWriter implements JSONWriter {
    @Override
    public void writer(Map<String, Object> sqlParamMap, String path) {
        //创建freeMarker配置实例
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        Writer out = null;
        try {
            //设置模版路径
            configuration.setClassForTemplateLoading(this.getClass(), "/templates");
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
            configuration.setFallbackOnNullLoopVariable(false);
            //加载模版文件
            Template template = configuration.getTemplate("json2sql.ftl");
            //生成数据
            File docFile = new File(path + "test.sql");
//            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));

            out = new OutputStreamWriter(System.out);
            //输出文件
            template.process(sqlParamMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
