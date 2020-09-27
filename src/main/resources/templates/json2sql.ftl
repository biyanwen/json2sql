<#if tableParam??>
    CREATE TABLE ${tableName}{
    <#list tableParam?keys as key>
        ${key!} ${tableParam[key]!}
    <#-- 方法二: value:${hsmap.get(key)!}   我没有测试成功-->
    </#list>
    };
</#if>
