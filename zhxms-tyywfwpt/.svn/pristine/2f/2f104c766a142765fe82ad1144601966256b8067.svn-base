package cn.com.bmsoft.modules.wom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 工单管理-工单处理详情表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
@Data
@TableName("wom_handle_information")
public class HandleInformationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 工单详情主键
	 */
	@TableId
	private Long id;
	/**
	 * 工单环节id
	 */
	private String gdhjid;
	/**
	 * 工单处理结果（已解决/部分解决/未解决）（审核通过/审核不通过））（办结/未办结）
	 */
	private String gdclzt;
	/**
	 * 问题分析
	 */
	private String wtfx;
	/**
	 * 解决方案
	 */
	private String jjfa;
	/**
	 * 处理说明(办结说明/审核说明)
	 */
	private String clsm;
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
	 * 是否删除
	 */
	private String delete_flag;

}
