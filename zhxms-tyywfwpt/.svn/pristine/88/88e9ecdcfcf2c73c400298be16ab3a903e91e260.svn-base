package cn.com.bmsoft.modules.dictionary.entity;

import cn.com.bmsoft.validator.group.AddGroup;
import cn.com.bmsoft.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 字典项表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-22
 */
@Data
@TableName("c_dictionary_item")
public class DictionaryItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 字典编码
	 */
	@NotNull(message="字典编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String dictCode;
	/**
	 * 字典项值
	 */
	@NotBlank(message="字典项值不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String value;
	/**
	 * 字典项名
	 */
	@NotBlank(message="字典项名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 排序值
	 */
	private Integer orderNum;
	/**
	 * 备注
	 */
	private String remark;

}
