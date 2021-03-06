package net.maku.generator.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 表信息
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@TableName("gen_table_info")
public class TableInfoEntity {
    @TableId
    private Long id;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表名
     */
    private String tableOwner;
    /**
     * 实体类名称
     */
    private String className;
    /**
     * 功能名
     */
    private String tableComment;
    /**
     * 项目包名
     */
    private String packageName;
    /**
     * 项目版本号
     */
    private String version;
    /**
     * 模板路径
     */
    private String templatePath;
    /**
     * 数据字典id
     */
    private Long dataDicId;
    /**
     * 作者
     */
    private String author;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 后端生成路径
     */
    private String backendPath;
    /**
     * 前端生成路径
     */
    private String frontendPath;
    /**
     * 模块名
     */
    private String moduleName;
    /**
     * 子模块名
     */
    private String subModuleName;
    /**
     * 数据源ID
     */
    private Long datasourceId;
    /**
     * 基类ID
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long baseclassId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 表字段
     */
    @TableField(exist = false)
    private List<TableFieldEntity> fields;
    /**
     * 主键
     */
    @TableField(exist = false)
    private TableFieldEntity key;
    /**
     * 标签
     */
    @TableField(exist = false)
    private String label;

    public String getLabel() {
        if (label == null) {
            if (StringUtils.isNotBlank(tableOwner)) {
                label = tableOwner + "." + tableName;
            } else {
                label = tableName;
            }
        }
        return label;
    }

    // public String getLabel() {
    //         if (StringUtils.isNotBlank(tableOwner)) {
    //             tableName = tableOwner + "." + tableName;
    //         }
    //     return label;
    // }
}
