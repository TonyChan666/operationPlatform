package cn.com.bmsoft.modules.am.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 告警策略表
 * 
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@Data
@TableName("am_strategy")
public class AmStrategyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id",type = IdType.INPUT)
	private Integer id;
	/**
	 * 告警项目类型
	 */
	private String alarmProjectType;
	/**
	 * 告警策略名称
	 */
	private String alarmStrategyName;
    /**
     *告警策略编码
     */
    private String alarmStrategyCode;
	/**
	 * 资源类别
	 */
	private String resourceType;
	/**
	 * 业务类别
	 *//*
	private String businessType;*/
	/**
	 * 资源清单
	 */
	private String resourceList;
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
	 * 指标名称
	 */
	private String targetCode;
	/**
	 * 指标描述
	 */
	private String targetDescribe;
	/**
	 * 资源清单名称
	 */
	private String resourceName;
	/**
	 * 逻辑删除
	 */
	private Integer deleteFlag;

}
