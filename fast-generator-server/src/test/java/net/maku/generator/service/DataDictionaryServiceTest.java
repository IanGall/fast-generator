package net.maku.generator.service;

import net.maku.generator.common.enumration.DataDictionary;
import net.maku.generator.dto.DataTable;
import net.maku.generator.entity.DataDictionaryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class DataDictionaryServiceTest {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private GeneratorService generatorService;

    @Test
    void page() {

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher("18.5");
        while (matcher.find()) {
            // int i1 = matcher.groupCount();
            // for(int i = 0; i<= i1; i++){
            //     System.out.println(i+":"+matcher.group(i));
            // }
            System.out.println(1+":"+matcher.group());
        }

    }

    @Test
    void getList() {
        List<DataDictionaryEntity> list;
        try {
            // JSONUtils.type.set(new TypeReference<List<TableMap>>(){});
            for (DataDictionaryEntity dataDictionaryEntity : list = dataDictionaryService.getList()) {
                List<DataTable> contentObj = dataDictionaryEntity.getContentObj(DataDictionary.CLEARANCE_INFO.getTypeReference());
                System.out.println(contentObj);


            }
        } finally {
            // JSONUtils.type.remove();
        }
        // System.out.println(list);
    }

    @Test
    void name() throws Exception {
        generatorService.generatorDDL(2L,"edi");
    }
}