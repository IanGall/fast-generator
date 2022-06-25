package net.maku.generator.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClearanceInfoTable {
    private String length;
    private String columnName;
    private String paramName;
    private String chineseName;
}

