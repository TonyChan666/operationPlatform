package cn.com.bmsoft.modules.bsm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 业务服务管理-应用服务表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("bsm_service")
public class ServiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
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
	 * 接口协议
	 */
	private String jkxy;
	/**
	 * URL
	 */
	private String url;
	/**
	 * 账号
	 */
	private String zh;
	/**
	 * 密码
	 */
	private String mm;
	/**
	 * 所属业务警种
	 */
	private String ywjz;
	/**
	 * 业务所属科室
	 */
	private String ywks;
	/**
	 * 运维人员名称
	 */
	@TableField(exist = false)
	private String ywry;
	@TableField(exist = false)
	private String ywksmc;
	@TableField(exist = false)
	private String ywjzmc;
	@TableField(exist = false)
	private String jkxymc;
	/**
	 * 运维人员id
	 */
	private Long ywryid;
	/**
	 * 概览
	 */
	private String gl;
	/**
	 * 安装服务器
	 */
	@TableField(exist = false)
	private String azfwq;
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
	 * 所属业务组
	 */
	private String ssywz;

	/**
	 * 所属业务系统id
	 */
	private Long ssywxtid;
	/**
	 * 支撑服务类型
	 */
	private  int zcfwlx;
	/**
	 * 服务类型
	 */
	private  String fwlx;
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
