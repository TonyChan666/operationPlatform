package cn.com.bmsoft.modules.am.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 告警记录表
 * 
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@Data
@TableName("am_record")
public class RecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 告警记录类型
	 */
	private String alarmRecordType;
	/**
	 * 资源告警编号
	 */
	private String alarmRecordCode;
	/**
	 * 资源类别
	 */
	private String resourceType;
	/**
	 * 业务类别
	 */
//	private String businessType;
	/**
	 * 告警对象
	 */
	private String alarmObject;
	/**
	 * 告警对象名
	 */
	private String alarmObjectName;
	/**
	 * 所属服务器名
	 */
	@TableField(exist = false)
	private String serverName;
	/**
	 * 问题分析
	 */
	private String reasonAnalysis;
	/**
	 * 告警指标
	 */
	private String alarmTarget;
	/**
	 * 告警等级
	 */
	private String alarmGrade;
	/**
	 * 告警值
	 */
	private String alarmValue;
	/**
	 * 告警时间
	 */
	private Date alarmTime;
	/**
	 * 所属网络类别
	 */
	private String networkType;
	/**
	 * IP地址
	 */
	private String ipAddress;
	/**
	 * 所属业务警种
	 */
	private String policeType;
	/**
	 * 处理时间
	 */
	private Date handleTime;
	/**
	 * 处理人id
	 */
	private Long handleUserId;
	/**
	 * 处理人
	 */
	@TableField(exist = false)
	private String handleUser;
	/**
	 * 服务故障分析
	 */
	private String problemAnalysis;
	/**
	 * 工单编码
	 */
	private String orderCode;
	/**
	 * 短信编码
	 */
	private String emailCode;
	/**
	 * 邮件编码
	 */
	private String messageCode;
	/**
	 * 余时/超时
	 */
	private String lastTime;
	/**
	 * 耗时
	 */
	private String costTime;
	/**
	 * 解决状态
	 */
	private String handleStatus;
	/**
	 * 进度
	 */
	private String planSpeed;
	/**
	 * 告警通知方式
	 */
	private String alarmNoticeType;
	/**
	 * 运维人员id
	 */
	private Long operationUserId;
	/**
	 * 运维人员
	 */
	@TableField(exist = false)
	private String operationUser;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 解决方案
     */
    private String handlePlan;
    /**
     * 处理限时
     */
    private String timeLimit;
    /**
     * 逻辑删除
     */
    private Integer deleteFlag;
}
