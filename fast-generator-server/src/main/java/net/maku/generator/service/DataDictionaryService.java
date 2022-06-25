package net.maku.generator.service;

import net.maku.generator.common.page.PageResult;
import net.maku.generator.common.query.Query;
import net.maku.generator.common.service.BaseService;
import net.maku.generator.entity.DataDictionaryEntity;

import java.util.List;

/**
 * 表
 *
 * @author 阿沐 babamu@126.com
 */
public interface DataDictionaryService extends BaseService<DataDictionaryEntity> {

    PageResult<DataDictionaryEntity> page(Query query);

    List<DataDictionaryEntity> getList();

    List<DataDictionaryEntity> selectIdAndTableNameByDatasourceId(Long datasourceId);
}