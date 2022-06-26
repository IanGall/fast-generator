package net.maku.generator.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.*;
import net.maku.generator.dto.ClearanceInfoTable;
import net.maku.generator.enumration.DataDicType;
import net.maku.generator.utils.JSONUtils;

import java.util.Date;
import java.util.List;

/**
 * 数据字典表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-06-25
 */
@Data
@EqualsAndHashCode(callSuper= false)
@TableName("data_dictionary")
public class DataDictionaryEntity  {
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
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 内容
	 */
	private String content;

	@TableField(exist = false)
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Object contentObj = null;

	public <T> T getContentObj(TypeReference<T> typeReference) {
		if (contentObj == null) {
			contentObj = JSONUtils.string2Obj(content, typeReference);
		}
		return (T) contentObj;
	}

}