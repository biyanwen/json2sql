<#if tableParam??>
    CREATE TABLE ${tableName}(
    <#list tableParam?keys as key>
        ${key!} ${tableParam[key]!}<#if key_has_next>,</#if>
    </#list>
    );
</#if>

<#if insertParam??>
    <#list insertParam as insertDTO>
        INSERT INTO ${tableName} (
        <#list insertDTO.keys as key>
            ${key}<#if key_has_next>,</#if>
        </#list>
        )
        VALUES (
        <#list insertDTO.values as value>
            <#if value??>
                '${value}'<#if value_has_next>,</#if>
            <#else>
                null<#if value_has_next>,</#if>
            </#if>

        </#list>
        );
    </#list>
</#if>
