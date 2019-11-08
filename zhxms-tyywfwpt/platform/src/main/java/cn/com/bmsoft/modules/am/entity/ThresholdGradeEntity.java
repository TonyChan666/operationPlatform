package cn.com.bmsoft.modules.am.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 阈值等级表
 * 
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@Data
@TableName("am_threshold_grade")
public class ThresholdGradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id",type = IdType.INPUT)
	private Integer id;
	/**
	 * 阈值等级
	 */
	private String thresholdLevel;
	/**
	 * 要求处理时限
	 */
	private String requirestHandleTime;
	/**
	 * 告警方式
	 */
	private String alarmType;
	/**
	 * 邮件模板ID
	 */
	private Integer emailModeId;
	/**
	 * 告警次数
	 */
	private Integer alarmNumber;
	/**
	 * 告警频率
	 */
	private String alarmFrequency;
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
	 * 阈值
	 */
	private String threshold;
	/**
	 * 监控频率
	 */
	private String monitorFrequency;
	/**
	 * 轮询次数
	 */
	private String pollTime;
	/**
	 * 告警策略ID
	 */
	private Integer strategyId;
	/**
	 * 是否生成工单
	 */
	private Integer isOrder;
	/**
	 * 条件
	 */
	private String condtion;
	/**
	 * 消息模板ID
	 */
	private Integer messageModelId;
	/**
	 * 逻辑删除
	 */
	private Integer deleteFlag;
}
