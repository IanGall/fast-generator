package net.maku.generator.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import net.maku.generator.dto.TableMap;
import net.maku.generator.utils.JSONUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 数据字典表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-06-25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("data_dictionary")
public class DataDictionaryEntity {
	/**
	* id
	*/
	@TableId
	private Long id;

	/**
	* 表名
	*/
	private String tableName;

	/**
	* 数据源ID
	*/
	private Long datasourceId;

	/**
	* key所在column
	*/
	private String keyColumn;

	/**
	* 内容
	*/
	private List<TableMap> content;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	// @Data
	// @EqualsAndHashCode(callSuper=false)
	// public static class TableMap{
	// 	private String length;
	// 	private String columnName;
	// 	private String paramName;
	// 	private String chineseName;
	// }

	// @Component
	// @RequiredArgsConstructor
	// public static class DictContentTypeHandler extends AbstractJsonTypeHandler<List<TableMap>> {
	//
	// 	// private final ObjectMapper objectMapper;
	//
	// 	public ThreadLocal<TypeReference> type = new ThreadLocal<>();
	//
	// 	@Override
	// 	protected List<TableMap> parse(String json) {
	// 		return JSONUtils.string2Obj(json, type.get());
	// 	}
	//
	// 	@Override
	// 	protected String toJson(List<TableMap> obj) {
	// 		return JSONUtils.obj2String(obj);
	// 	}
	// }
}