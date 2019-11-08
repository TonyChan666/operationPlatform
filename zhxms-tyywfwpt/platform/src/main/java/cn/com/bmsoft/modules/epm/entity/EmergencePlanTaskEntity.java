package cn.com.bmsoft.modules.epm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 应急预案任务表
 * 
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("epm_plan_task")
public class EmergencePlanTaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * b_emergence_plan.id
	 */
	private String yjyaid;
	/**
	 * 任务名称
	 */
	private String rwmc;
	/**
	 * 创建人（任务创建者）
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改人（派发给或者发布指定的那些运维人员）
	 */
	private String updateUser;
	/**
	 * 最后修改时间
	 */
	private Date updateTime;
	/**
	 * 是否有效
	 */
	private String status;  //"1"(是未评审，未评估)"0"(已评审，以评估)

	/**
	 * 派发者和发布者名字
	 */
	@TableField(exist=false)
	private String name;

}
