package cn.com.bmsoft.modules.bm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 备份管理-恢复登记表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("bm_recover_record")
public class RecoverRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private int id;
	/**
	 * 恢复登记编号
	 */
	private String hfclbh;
	/**
	 * 备份策略编号
	 */
	private Integer bfid;
	/**
	 * 备份策略编号
	 */
	@TableField(exist = false)
	private String bfbh;

	/**
	 * 备份策略名称
	 */
	@TableField(exist = false)
	private String bfmc;
	/**
	 * 所属业务服务id
	 */
	private Long ywfwid;
	/**
	 * 所属业务服务名称
	 */
	@TableField(exist = false)
	private String ywfw;
	/**
	 * 所属服务器id
	 */
	private Integer fwqid;
	/**
	 * 所属服务器名称
	 */
	@TableField(exist = false)
	private String fwqmc;
	/**
	 * 恢复登记名称
	 */
	private String hfclmc;
	/**
	 * 运维人员
	 */
	private Long ywryid;
	/**
	 * 运维人员名称
	 */
	@TableField(exist = false)
	private String ywry;
	/**
	 * 恢复过程记录
	 */
	private String hfgcjl;
	/**
	 * 恢复时间
	 */
	private Date hfsj;
	/**
	 * 恢复原因
	 */
	private String hfyy;
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

	private String fjid;
	@TableField(exist = false)
	private String fjmc;

	/**
	 * 是否删除
	 */
	private String delete_flag;

}
