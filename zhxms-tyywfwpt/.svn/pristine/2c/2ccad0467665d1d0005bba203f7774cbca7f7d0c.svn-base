package cn.com.bmsoft.modules.bm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 备份管理-备份策略表
 * 
 * @author sufang  luyuwei@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("bm_strategy")
public class StrategyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private int id;
	/**
	 * 备份编号
	 */
	private String bfbh;
	/**
	 * 备份名称
	 */
	private String bfmc;
	/**
	 * 备份路径
	 */
	private String bflj;
	/**
	 * 恢复操作说明
	 */
	private String hfczsm;
	/**
	 * 备份巡检时间
	 */
	private String bfsdsj;
	/**
	 * 所属服务器id
	 */
	private Integer fwqid;
	/**
	 * 所属服务器编号
	 */
	@TableField(exist = false)
	private String fwqbh;
	/**
	 * 所属服务器名称
	 */
	@TableField(exist = false)
	private String fwqmc;
	/**
	 * 所属业务服务id
	 */
	private Long ywfwid;
	/**
	 * 所属业务服务
	 */
	@TableField(exist = false)
	private String ywfw;
	/**
	 * 备份异常工单处理时限
	 */
	private Integer bfycgdclsx;
	/**
	 * 通知渠道
	 */
	private String tzqd;
	/**
	 * 运维人员id
	 */
	private Long ywryid;
	/**
	 * 运维人员
	 */
	@TableField(exist = false)
	private String ywrymc;
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
	/**
	 * 是否有效
	 */
	private String sfyx;
	/**
	 * 备份策略是否有效
	 */
	private String status;
	private String fjid;
	private String fjmc;

	/**
	 * 是否删除
	 */
	private String delete_flag;


	/**
	 * 开始时间
	 *//*
	private Date startTime;
	*//**
	 * 结束时间
	 *//*
	private Date endTime;*/

}
