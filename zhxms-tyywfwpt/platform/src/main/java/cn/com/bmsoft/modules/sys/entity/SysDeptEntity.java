package cn.com.bmsoft.modules.sys.entity;

import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 组织机构表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-26
 */
@Data
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 机构ID
	 */
	@TableId
	private Integer deptId;
	/**
	 * 机构名称
	 */
	@NotBlank(message="机构名称不能为空", groups = {AddGroup.class})
	private String deptName;
	/**
	 * 机构上级ID
	 */
	@NotNull(message="机构父级组织不能为空", groups = {AddGroup.class})
	private Integer deptParentId;
	/**
	 * 机构编码
	 */
	private String deptCode;
	/**
	 * 机构描述
	 */
	private String deptDesc;
	/**
	 * 创建人ID
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long createUserId;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 最后修改人
	 */
	private Long updateUserId;
	/**
	 * 最后修改时间
	 */
	private Date updateTime;
	/**
	 * 子节点集合
	 */
	@TableField(exist=false)
	private List<SysDeptEntity> children;
	/**
	 * 父机构名称
	 */
	@TableField(exist=false)
	private String deptParentName;

	/**
	 * 删除标识 0：逻辑删除
	 */
	private Integer deleteFlag;
}
