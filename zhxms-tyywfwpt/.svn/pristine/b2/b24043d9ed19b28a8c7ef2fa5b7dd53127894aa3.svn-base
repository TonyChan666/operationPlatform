package cn.com.bmsoft.modules.wom.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 工单管理-工单过程表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
@Data
@TableName("wom_process")
public class ProcessEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

	/**
	 * 工单环节id
	 */

	private String gdhjid;
	/**
	 * 工单编号
	 */
	private String gdbh;
	/**
	 * 办理时限
	 */
	private Integer blsx;
	/**
	 * 工单状态（0待派发，1可转派，2待审核）
	 */
	private String gdzt;
	/**
	 * 工单说明
	 */
	private String gdsm;
	/**
	 * 通知渠道
	 */
	private String tzqd;
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
	 * 工单接收人
	 */
	private Long gdjsrid;
	/**
	 * 是否删除
	 */
	private String delete_flag;
	/**
	 * 派发人
	 */
	@TableField(exist = false)
	private String pfrid;
	/**
	 * 被派发人
	 */
	@TableField(exist = false)
	private String bpfrid;
	@TableField(exist = false)
	private Date pfsj;


}
