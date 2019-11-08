package cn.com.bmsoft.modules.bsm.entity;

import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 业务服务管理-应用系统表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("bsm_system")
public class SystemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private int id;

	/**
	 * 业务服务编码
	 */
	private String ywfwbm;
	/**
	 * 业务服务名称
	 */
	private String ywfwmc;
	/**
	 * 版本号
	 */
	private String bbh;
	/**
	 * URL
	 */
	private String url;
	/**
	 * 所属业务警种
	 */
	private String ywjz;
	/**
	 * 业务所属科室
	 */
	private String ywks;

	@TableField(exist = false)
	private String ywry;
	@TableField(exist = false)
	private String azfwq;
	@TableField(exist = false)
	private String ywksmc;
	@TableField(exist = false)
	private String ywjzmc;

	/**
	 * 运维人员id
	 */
	private Long ywryid;
	/**
	 * 概览
	 */
	private String gl;
	/**
	 * 创建人
	 */
	private Long cjrid;
	/**
	 * 创建时间
	 */
	private Date cjsj;
	/**
	 * 最后修改人
	 */
	private Long xgrid;
	/**
	 * 最后修改时间
	 */
	private Date xgsj;

	private String status;

	/**
	 * 是否删除
	 */
	private String delete_flag;

	/**
	 * 运行状态：0正常，1警告
	 */
	private String yxzt;
	/**
	 * 业务组ID
	 */
	@TableField(exist = false)
	private List<Integer> ywzid;
	/**
	 * 业务组名称
	 */
	@TableField(exist = false)
	private String ywzmc;




}
