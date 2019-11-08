package cn.com.bmsoft.modules.serial.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 公共流水号表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-10-10
 */
@Data
@TableName("c_serial_number")
public class SerialNumberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 规则
	 */
	private String rule;
	/**
	 * 规则示例
	 */
	private String ruleDemo;
	/**
	 * 格式编码
	 */
	private String formatCode;
	/**
	 * 下一个流水号
	 */
	private Integer nextValue;
	/**
	 * 流水号增长值
	 */
	private Integer increaseValue;
	/**
	 * 流水号长度
	 */
	private Integer length;
	/**
	 * 是否有效 0：无效  1：有效
	 */
	private String status;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
