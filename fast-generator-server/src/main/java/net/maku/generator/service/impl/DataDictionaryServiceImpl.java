package net.maku.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import net.maku.generator.common.page.PageResult;
import net.maku.generator.common.query.Query;
import net.maku.generator.common.service.impl.BaseServiceImpl;
import net.maku.generator.dao.DataDictionaryDao;
import net.maku.generator.entity.DataDictionaryEntity;
import net.maku.generator.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 数据源管理
 *
 * @author 阿沐 babamu@126.com
 */
@Service
public class DataDictionaryServiceImpl extends BaseServiceImpl<DataDictionaryDao, DataDictionaryEntity> implements DataDictionaryService {

    @Autowired
    private DataDictionaryDao dataDictionaryDao;

    @Override
    public PageResult<DataDictionaryEntity> page(Query query) {
        IPage<DataDictionaryEntity> page = baseMapper.selectPage(
            getPage(query),
            getWrapper(query)
        );
        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public List<DataDictionaryEntity> getList() {
        return baseMapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public List<DataDictionaryEntity> selectIdAndTableNameByDatasourceId(Long datasourceId) {
        return dataDictionaryDao.selectIdAndTableNameByDatasourceId(datasourceId);
    }
}