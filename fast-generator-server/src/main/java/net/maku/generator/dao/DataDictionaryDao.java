package net.maku.generator.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.maku.generator.entity.DataDictionaryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 数据字典表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-06-25
*/
@Mapper
public interface DataDictionaryDao extends BaseMapper<DataDictionaryEntity> {

    List<DataDictionaryEntity> selectIdAndTableNameByDatasourceId(@Param("datasourceId")Long datasourceId);


}