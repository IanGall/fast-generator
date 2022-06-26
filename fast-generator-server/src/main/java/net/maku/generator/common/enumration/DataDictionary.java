package net.maku.generator.common.enumration;

import com.fasterxml.jackson.core.type.TypeReference;
import net.maku.generator.dto.ClearanceInfoTable;

import java.util.ArrayList;
import java.util.List;

public enum DataDictionary {

    CLEARANCE_INFO(new TypeReference<List<ClearanceInfoTable>>() {
    }),
    ;

    DataDictionary(TypeReference<?> typeReference) {
        this.typeReference = typeReference;
    }

    private final TypeReference<?> typeReference;

    public <T> TypeReference<T> getTypeReference() {
        return (TypeReference<T>) typeReference;
    }
}
