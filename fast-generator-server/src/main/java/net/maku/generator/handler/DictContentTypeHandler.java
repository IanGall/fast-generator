package net.maku.generator.handler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import net.maku.generator.dto.DataTable;
import net.maku.generator.utils.JSONUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DictContentTypeHandler extends AbstractJsonTypeHandler<List<DataTable>> {

    // private final ObjectMapper objectMapper;


    @Override
    protected List<DataTable> parse(String json) {
        return JSONUtils.string2Obj(json, new TypeReference<List<DataTable>>(){});
    }

    @Override
    protected String toJson(List<DataTable> obj) {
        return JSONUtils.obj2String(obj);
    }
}

