package net.maku.generator.convert;

import net.maku.generator.dto.DataDictionaryVO;
import net.maku.generator.entity.DataDictionaryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
* 数据字典表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-06-25
*/
@Mapper
public interface DataDictionaryConvert {
    DataDictionaryConvert INSTANCE = Mappers.getMapper(DataDictionaryConvert.class);

    DataDictionaryEntity convert(DataDictionaryVO vo);

    DataDictionaryVO convert(DataDictionaryEntity entity);

    List<DataDictionaryVO> convertList(List<DataDictionaryEntity> list);

}