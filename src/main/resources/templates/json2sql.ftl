<#if tableParam??>
    CREATE TABLE ${tableName}(
    <#list tableParam?keys as key>
        ${key!} ${tableParam[key]!}
    <#-- 方法二: value:${hsmap.get(key)!}   我没有测试成功-->
    </#list>
    );
</#if>

<#if insertParam??>
    <#list insertParam as insertDTO>

        INSERT INTO ${tableName} (
        <#list insertDTO.keys as key>
            ${key}
        </#list>
        )
        VALUES (

        <#list insertDTO.values as value>
            ${value}
        </#list>
        );
    </#list>
</#if>
