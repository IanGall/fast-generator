package net.maku.generator.service;

import net.maku.generator.config.DataSourceInfo;
import net.maku.generator.entity.TableFieldEntity;
import net.maku.generator.entity.TableInfoEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 代码生成
 *
 * @author 阿沐 babamu@126.com
 */
public interface GeneratorService {

    DataSourceInfo getDataSourceInfo(Long datasourceId);

    void datasourceTable(TableInfoEntity tableInfo);

    void updateTableField(Long tableId, List<TableFieldEntity> tableFieldList);

    void generatorCode(TableInfoEntity tableInfo) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    void generatorDDL(Long dataDicId, String owner) throws Exception;

    List<String> templatePaths();
}
