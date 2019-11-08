package cn.com.bmsoft.modules.sys.entity;

import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 供应商管理
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-27
 */
@Data
@TableName("sys_provider")
public class SysProviderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer providerId;
	/**
	 * 供应商编号
	 */
	private String providerCode;
	/**
	 * 供应商名称
	 */
	@NotBlank(message="供应商名称不能为空", groups = {AddGroup.class})
	private String providerName;
	/**
	 * 供应商描述
	 */
	private String providerDesc;
	/**
	 * 供应商人数
	 */
	@TableField(exist=false)
	private Integer providerPeopleNum;
	/**
	 * 是否有效
	 */
	private String status;
	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long createUserId;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 修改人
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long updateUserId;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date updateTime;

	/**
	 * 删除标识 0：逻辑删除
	 */
	private Integer deleteFlag;
}
