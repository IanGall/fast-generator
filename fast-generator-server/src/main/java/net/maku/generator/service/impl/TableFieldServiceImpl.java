package net.maku.generator.service.impl;

import net.maku.generator.common.service.impl.BaseServiceImpl;
import net.maku.generator.dao.TableFieldDao;
import net.maku.generator.entity.TableFieldEntity;
import net.maku.generator.service.TableFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 表
 *
 * @author 阿沐 babamu@126.com
 */
@Service
public class TableFieldServiceImpl extends BaseServiceImpl<TableFieldDao, TableFieldEntity> implements TableFieldService {

    @Autowired
    private TableFieldDao tableFieldDao;

    @Override
    public List<TableFieldEntity> getByTableName(String tableName) {
        return baseMapper.getByTableName(tableName);
    }

    @Override
    public void deleteByTableName(String tableName) {
        baseMapper.deleteByTableName(tableName);
    }

    @Override
    public void deleteBatchTableIds(Long[] tableIds) {
        baseMapper.deleteBatchTableIds(tableIds);
    }

    @Override
    public int deleteByTableId(Long tableId) {
        return tableFieldDao.deleteByTableId(tableId);
    }

    @Override
    public List<TableFieldEntity> getByTableId(Long tableId) {
        return tableFieldDao.getByTableId(tableId);
    }
}