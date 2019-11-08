package cn.com.bmsoft.modules.epm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 应急预案表
 * 
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("epm_plan")
public class EmergencePlanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 预案制定编号
	 */
	private String yjyabh;
	/**
	 * 应急预案名称
	 */
	private String yjyamc;
	/**
	 * 应急类型
	 */
	private String yjlx;
	/**
	 * 应急响应级别
	 */
	private String yjxyjb;
	/**
	 * 预案内容描述
	 */
	private String yanrms;
	/**
	 * 预案状态
	 */
	private String yazt;
	/**
	 * 是否已评审
	 */
	private String sfyps;
	/**
	 * 是否已评估
	 */
	private String sfypg;
	/**
	 * 附件id
	 */
	private String fjid;
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
	 * 评审人员
	 */
	private String reviewUser;
	/**
	 * 评估人员
	 */
	private String assessUser;

	private String deleteFlag; //是否删除（默认为null,删除是更新为0）

}
