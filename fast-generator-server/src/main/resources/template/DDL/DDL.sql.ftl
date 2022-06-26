create table ${tableName}
(
sid                            varchar(40)                            not null
primary key,
<#list dataTableList as dataTable>
<#--    <#if dataTable.typeName == 'varchar'>-->
<#--        ${dataTable.columnName} varchar(${dataTable.characterMaximumLength}) <#if dataTable.required>not null</#if><#if dataTable_index != (dataTableList?size-1)>,</#if>-->
<#--    </#if>-->
<#--        ${dataTable.columnName} ${dataTable.relType} <#if dataTable.required>not null</#if><#if dataTable_index != (dataTableList?size-1)>,</#if>-->
        ${dataTable.columnName} ${dataTable.relType} <#if dataTable.required>not null</#if>,
</#list>

trade_code                     varchar(10)                            not null,
insert_user                    varchar(100)                           not null,
insert_time                    timestamp(6) default CURRENT_TIMESTAMP not null,
update_user                    varchar(100),
update_time                    timestamp(6)

);

<#list dataTableList as dataTable>
        comment on column ${tableName}.${dataTable.columnName} is '${dataTable.chineseName}';
</#list>

alter table ${tableName}
owner to ${owner};


