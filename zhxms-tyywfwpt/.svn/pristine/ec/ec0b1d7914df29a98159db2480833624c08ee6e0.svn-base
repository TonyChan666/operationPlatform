package cn.com.bmsoft.modules.am.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 告警模板表
 * 
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@Data
@TableName("am_model")
public class ModelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 通知方式名称
	 */
	private String sendTypeName;
	/**
	 * 通知方式编码
	 */
	private String sendTypeCode;
	/**
	 * 通知渠道
	 */
	private String sendChannel;
	/**
	 * 通知渠道名称
	 */
	@TableField(exist = false)
	private String sendChannelName;
	/**
	 * 告警模板内容
	 */
	private String modelContent;
	/**
	 * 告警模板内容
	 */
	private String emailTitle;
	/**
	 * 合适告警类型
	 */
	private String alarmType;
	/**
	 * 是否有效
	 */
	private String isActive;
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
