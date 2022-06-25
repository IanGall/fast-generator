package net.maku.generator.handler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import net.maku.generator.dto.TableMap;
import net.maku.generator.entity.DataDictionaryEntity;
import net.maku.generator.utils.JSONUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DictContentTypeHandler extends AbstractJsonTypeHandler<List<TableMap>> {

    // private final ObjectMapper objectMapper;


    @Override
    protected List<TableMap> parse(String json) {
        return JSONUtils.string2Obj(json, new TypeReference<List<TableMap>>(){});
    }

    @Override
    protected String toJson(List<TableMap> obj) {
        return JSONUtils.obj2String(obj);
    }
}

