package net.maku.generator.enumration;

import com.fasterxml.jackson.core.type.TypeReference;
import net.maku.generator.dto.DataTable;

import java.util.List;

public enum DataDicType {

    CLEARANCE_INFO(new TypeReference<List<DataTable>>() {
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
