package cn.com.bmsoft.modules.am.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 告警处理表
 * 
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@Data
@TableName("am_handle")
public class HandleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id",type = IdType.INPUT)
	private Integer id;
	/**
	 * 处理时间
	 */
	private Date handleTime;
	/**
	 * 处理人
	 */
	private Long handleUserId;
	/**
	 * 处理结果
	 */
	private String handleResult;
	/**
	 * 原因分析
	 */
	private String reasonAanalysis;
	/**
	 * 解决方案
	 */
	private String handlePlan;
	/**
	 * 告警记录ID
	 */
	private Integer alarmRecodId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 创建人
	 */
	private Long createUserId;
	/**
	 * 修改人
	 */
	private Long updateUserId;
	/**
	 * 逻辑删除
	 */
	private Integer deleteFlag;

}
