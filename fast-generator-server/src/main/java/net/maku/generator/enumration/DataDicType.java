package net.maku.generator.enumration;

import com.fasterxml.jackson.core.type.TypeReference;
import net.maku.generator.dto.ClearanceInfoTable;

import java.util.List;

public enum DataDicType {

    CLEARANCE_INFO(new TypeReference<List<ClearanceInfoTable>>() {
    }),

    ;

    private final TypeReference<?> typeReference;

    DataDicType(TypeReference<?> listTypeReference) {
        typeReference = listTypeReference;
    }

    public TypeReference<?> getTypeReference() {
        return typeReference;
    }
}
