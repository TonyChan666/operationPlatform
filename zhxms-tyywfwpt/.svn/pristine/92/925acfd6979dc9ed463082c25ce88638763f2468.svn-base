package cn.com.bmsoft.modules.epm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 应急预案评审催办表
 * 
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("epm_plan_review_urge")
public class EmergencePlanReviewUrgeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * b_emergence_plan_review.id
	 */
	private String yjyapsid;
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

}
