package cn.com.bmsoft.modules.bsm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-10-08
 */
@Data
@TableName("bsm_upgrade")
public class UpgradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String ywfwbm;
	/**
	 * 
	 */
	private String fjid;
	/**
	 * 
	 */
	private String fjmc;
	/**
	 * 
	 */
	private Long sjrid;

	@TableField(exist = false)
	private String sjrmc;
	/**
	 * 
	 */
	private Date sjsj;
	private String ywbm;
	private Long ywid;

}
