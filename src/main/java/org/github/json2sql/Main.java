package org.github.json2sql;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.github.json2sql.bean.InsertDTO;

import java.io.*;
import java.util.*;

/**
 * Description:  <br>
 *
 * @author byw
 * @create 2020/9/27
 */
public class Main {
    public static void main(String[] args) {
//        Map<String,Object> root = new HashMap<>();
//        Map<String, String> requestAttributes = new HashMap<>();
//        requestAttributes.put("1","2");
//        requestAttributes.put("2","2");
//        requestAttributes.put("3","3");
//        requestAttributes.put("4","4");
//        requestAttributes.put("user","4");
//
//        root.put("root",requestAttributes);
//        root.put("tableName","test");

        Map<String,Object> root = new HashMap<>();
        List<String> keys = Arrays.asList("a", "b", "c");
        List<String> values = Arrays.asList("1", "2", "3");
        List<InsertDTO> insertDTOS = new ArrayList<>();
        InsertDTO insertDTO = new InsertDTO();
        insertDTO.setKeys(keys);
        insertDTO.setValues(values);
        insertDTOS.add(insertDTO);
        root.put("insertParam",insertDTOS);
        root.put("tableName","MY_TABLE");


        //创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            //设置模版路径
            configuration.setClassForTemplateLoading(Main.class, "/templates");
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            configuration.setLogTemplateExceptions(false);

            configuration.setWrapUncheckedExceptions(true);

            configuration.setFallbackOnNullLoopVariable(false);
            //加载模版文件
            Template template = configuration.getTemplate("json2sql.ftl");
            //生成数据
            File docFile = new File("D:\\Work\\json2sql\\src\\main\\java\\org\\github\\json2sql" + "\\" + "test.sql");
//            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));

            out = new OutputStreamWriter(System.out);
            //输出文件
            template.process(root, out);
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
