package cn.com.bmsoft.modules.wom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 工单管理-工单信息表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
@Data
@TableName("wom_information")
public class InformationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 工单编号
	 */
	private String gdbh;
	/**
	 * 工单标题
	 */
	private String gdbt;
	/**
	 * 实例id
	 */
	private String slid;
	/**
	 * 优先级
	 */
	private String yxj;
	/**
	 * 告警等级
	 */
	@TableField(exist = false)
	private String gjdj;
	/**
	 * 工单类型
	 */
	private String gdlx;
	/**
	 * 工单所属资源/服务id
	 */
	private String gdssfwid;
	/**
	 * 工单所属资源/服务名称
	 */
	private String gdssfwmc;
	/**
	 * 工单内容描述
	 */
	private String gdbsnr;
	/**
	 * 工单状态(待提交/待派发/可转派/审核/办结)
	 */
	private String gdzt;
	/**
	 * 附件id
	 */
	private String fjid;

	/**
	 * 附件名称
	 */
	@TableField(exist = false)
	private String fjmc;
	/**
	 * 是否提交
	 */
	private Integer sftj;
	/**
	 * 创建人
	 */
	private Long cjrid;
	/**
	 * 创建人名称
	 */
	@TableField(exist = false)
	private String cjr;
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
	 * 是否删除
	 */
	private String delete_flag;
	/**
	 * 运维人员
	 */
	private Long ywryid;
	/**
	 * 运维人员
	 */
	@TableField(exist = false)
	private int ywid;
	/**
	 * 工单类型名称
	 */
	@TableField(exist = false)
	private String gdlxmc;
	/**
	 * 工单状态名称
	 */
	@TableField(exist = false)
	private String gdztmc;
	/**
	 * 工单优先级名称
	 */
	@TableField(exist = false)
	private String yxjmc;

}
