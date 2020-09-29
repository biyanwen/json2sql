package org.github.json2sql.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.github.json2sql.api.JSONWriter;

import java.io.*;
import java.util.Map;

/**
 * Description:  <br>
 *
 * @author byw
 * @create 2020/9/27
 */
public class DefaultJSONWriter implements JSONWriter {
    @Override
    public void writer(Map<String, Object> sqlParamMap, String path, String sqlFileName) {
        String outPath = System.getProperty("user.dir");
        if (StringUtils.isNotEmpty(path)) {
            outPath = path;
        }
        Writer out = null;
        try {
            Template template = configTemplate();
            //生成数据
            File docFile = new File(outPath + File.separatorChar + sqlFileName);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
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

    @SneakyThrows
    @Override
    public String writer(Map<String, Object> sqlParamMap) {
        Template template = configTemplate();
        try (StringWriter result = new StringWriter();) {
            template.process(sqlParamMap, result);
            return result.toString();
        }
    }

    @SneakyThrows
    private Template configTemplate() {
        //创建freeMarker配置实例
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        //设置模版路径
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setFallbackOnNullLoopVariable(false);
        //加载模版文件
        return configuration.getTemplate("json2sql.ftl");
    }
}
