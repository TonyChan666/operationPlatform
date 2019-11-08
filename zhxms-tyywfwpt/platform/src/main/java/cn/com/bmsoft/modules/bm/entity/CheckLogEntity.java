package cn.com.bmsoft.modules.bm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import lombok.Data;

/**
 * 备份管理-备份巡查日志表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("bm_check_log")
public class CheckLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志id
	 */
	@TableId
	private int id;
	/**
	 * 备份策略ID
	 */

	private int bfid;

	//@TableField(exist = false)
	private String bfrzbh;
	/**
	 * 备份时间
	 */
	@TableField(exist = false)
	private Date bfxjsj;
	/**
	 * 所属服务器id
	 */
	@TableField(exist = false)
	private Integer fwqid;
	/**
	 * 所属服务器名称
	 */
	@TableField(exist = false)
	private String fwqmc;
	/**
	 * 所属业务服务id
	 */
	@TableField(exist = false)
	private Long ywfwid;
	/**
	 * 所属业务服务
	 */
	@TableField(exist = false)
	private String ywfw;
	/**
	 * 备份状态
	 */
	private Integer bfzt;
	/**
	 * 备份路径
	 */
	@TableField(exist = false)
	private String bflj;
	/**
	 * 备份编号
	 */
	@TableField(exist = false)
	private String bfbh;
	/**
	 * 备份名称
	 */
	@TableField(exist = false)
	private String bfmc;
	/**
	 * 备份设定时间
	 */
	@TableField(exist = false)
	private String bfsdsj;
	/**
	 * 运维人员id
	 */
	@TableField(exist = false)
	private Long ywryid;
	/**
	 * 运维人员
	 */
	@TableField(exist = false)
	private String ywry;
	/**
	 * 创建时间
	 */
	private Date cjsj;

}
