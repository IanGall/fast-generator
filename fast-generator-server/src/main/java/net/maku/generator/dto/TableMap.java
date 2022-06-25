package net.maku.generator.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TableMap{
    private String length;
    private String columnName;
    private String paramName;
    private String chineseName;
}

