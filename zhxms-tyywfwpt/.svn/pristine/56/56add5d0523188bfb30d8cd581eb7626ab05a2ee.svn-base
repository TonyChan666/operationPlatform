package cn.com.bmsoft.modules.dictionary.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 字典表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-22
 */
@Data
@TableName("c_dictionary")
public class DictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 字典编码
	 */
	private String code;
	/**
	 * 字典名称
	 */
	private String name;
	/**
	 * 是否启用
	 */
	private String status;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 字典项列表
	 */
	@TableField(exist=false)
	private List<DictionaryItemEntity> items;

}
