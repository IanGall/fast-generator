package net.maku.generator.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataTable {
    private String length;
    private String columnName;
    private String paramName;
    private String chineseName;
    private String type;
    private String nullable;

    private String relType;
    /**
     * 字符串最大长度
     */
    private String characterMaximumLength;
    /**
     * 数字精度
     */
    private String numericPrecision;
    /**
     * 数字刻度
     */
    private String numericScale;

    private Boolean required = false;

}

