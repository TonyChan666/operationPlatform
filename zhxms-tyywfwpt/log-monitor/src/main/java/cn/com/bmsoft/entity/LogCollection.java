package cn.com.bmsoft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 日志采集表
 * 
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-10-10
 */
@Data
@TableName("l_log_collection")
public class LogCollection implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志采集主键
	 */
	@TableId(value = "collectionid",type= IdType.AUTO)
	private Integer collectionid;
	/**
	 * 任务id
	 */
	@TableField("collection_taskid")
	private Integer collectionTaskid;
	/**
	 * 采集开始时间
	 */
	@TableField("collection_startTime")
	private Date collectionStarttime;
	/**
	 * 日志类型   0：应用
            1：硬件
	 */
	@TableField("log_mold")
	private Integer logMold;
	/**
	 * 采集结果  0：失败
            1：成功
            2：采集中
	 */
	@TableField("collection_state")
	private Integer collectionState;
	/**
	 * 采集结束时间
	 */
	@TableField("collection_endTime")
	private Date collectionEndtime;
	/**
	 * 备注
	 */
	@TableField("remark")
	private String remark;

}
