package net.maku.generator.config.query;

import net.maku.generator.utils.DbType;
import org.apache.commons.lang.StringUtils;

/**
 * PostgreSql查询
 *
 * @author 阿沐 babamu@126.com
 */
public class PostgreSqlQuery implements AbstractQuery {

    @Override
    public DbType dbType() {
        return DbType.PostgreSQL;
    }

    @Override
    public String tablesSql(String tableName) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t1.tablename,t1.tableowner, obj_description(relfilenode, 'pg_class') as comments from pg_tables t1, pg_class t2 ");
        sql.append("where t1.tablename not like 'pg%' and t1.tablename not like 'sql_%' and t1.tablename = t2.relname ");
        //表名查询
        if(StringUtils.isNotBlank(tableName)){
            sql.append("and t1.tablename = '").append(tableName).append("' ");
        }

        return sql.toString();
    }

    @Override
    public String tableFieldsSql() {
        return "select a.attname                                         as                                           columnName,\n" +
                "       t.typname                                         as                                           dataType,\n" +
                "       col_description(a.attrelid, a.attnum)             as                                           columnComment,\n" +
                "       (CASE t3.contype WHEN 'p' THEN 'PRI' ELSE '' END) as                                           columnKey,\n" +
                "       a.attnotnull                                      AS                                           is_null_able,\n" +
                "       split_part(btrim(SUBSTRING(format_type(a.atttypid, a.atttypmod) from '\\(.*\\)'), '()'), ',', 2) numeric_scale,\n" +
                "       split_part(btrim(SUBSTRING(format_type(a.atttypid, a.atttypmod) from '\\(.*\\)'), '()'), ',', 1) numeric_precision,\n" +
                "       split_part(btrim(SUBSTRING(format_type(a.atttypid, a.atttypmod) from '\\(.*\\)'), '()'), ',',\n" +
                "                  1)                                                                                  character_maximum_length\n" +
                "from (select *, pg_get_userbyid(relowner) as owner\n" +
                "      from pg_class) as c,\n" +
                "     pg_attribute as a\n" +
                "         inner join pg_type t on t.oid = a.atttypid\n" +
                "         left join pg_constraint t3 on a.attnum = t3.conkey[1] and a.attrelid = t3.conrelid\n" +
                "where c.relname = '%s'\n" +
                "  and c.owner = '%s'\n" +
                "  and a.attrelid = c.oid\n" +
                "  and a.attnum > 0";
    }


    @Override
    public String tableName() {
        return "tablename";
    }

    @Override
    public String tableComment() {
        return "comments";
    }

    @Override
    public String fieldName() {
        return "columnName";
    }

    @Override
    public String fieldType() {
        return "dataType";
    }

    @Override
    public String fieldComment() {
        return "columnComment";
    }

    @Override
    public String fieldKey() {
        return "columnKey";
    }
}
