package net.maku.generator.handler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import net.maku.generator.dto.ClearanceInfoTable;
import net.maku.generator.utils.JSONUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DictContentTypeHandler extends AbstractJsonTypeHandler<List<ClearanceInfoTable>> {

    // private final ObjectMapper objectMapper;


    @Override
    protected List<ClearanceInfoTable> parse(String json) {
        return JSONUtils.string2Obj(json, new TypeReference<List<ClearanceInfoTable>>(){});
    }

    @Override
    protected String toJson(List<ClearanceInfoTable> obj) {
        return JSONUtils.obj2String(obj);
    }
}

