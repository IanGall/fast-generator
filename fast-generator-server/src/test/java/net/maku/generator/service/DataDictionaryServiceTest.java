package net.maku.generator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import net.maku.generator.dto.TableMap;
import net.maku.generator.entity.DataDictionaryEntity;
import net.maku.generator.utils.JSONUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DataDictionaryServiceTest {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Test
    void page() {
    }

    @Test
    void getList() {
        List<DataDictionaryEntity> list;
        try {
            // JSONUtils.type.set(new TypeReference<List<TableMap>>(){});
            for (DataDictionaryEntity dataDictionaryEntity : list = dataDictionaryService.getList()) {
                List<TableMap> contentObj = dataDictionaryEntity.getContentObj(new TypeReference<List<TableMap>>() {
                });
                System.out.println(contentObj);


            }
        } finally {
            // JSONUtils.type.remove();
        }
        // System.out.println(list);
    }
}