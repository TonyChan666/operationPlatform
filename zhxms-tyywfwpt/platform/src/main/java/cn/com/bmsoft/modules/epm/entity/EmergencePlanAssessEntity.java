package cn.com.bmsoft.modules.epm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 应急预案评审表
 * 
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("epm_plan_assess")
public class EmergencePlanAssessEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * b_emergence_plan.id
	 */
	private String rwid;

	/**
	 * epm_plan.id
	 */
	private String epid;
	/**
	 * 预案评估编号
	 */
	private String yapgbh;
	/**
	 * 运维人员id
	 */
	private String ywryid;
	/**
	 * 处理状态
	 */
	private String clzt;
	/**
	 * 评价
	 */
	private String pj;
	/**
	 * 意见建议
	 */
	private String yjjy;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改人
	 */
	private String updateUser;
	/**
	 * 最后修改时间
	 */
	private Date updateTime;
	/**
	 * 是否有效
	 */
	private String status;

	/**
	 * 所属机构名
	 */
	@TableField(exist=false)
	private String providerName;

	/**
	 * 评价内容
	 */
	@TableField(exist=false)
	private String pjName;

	/**
	 * 评价
	 */
	@TableField(exist=false)
	private String name;


}
