package cn.com.bmsoft.modules.bsm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 业务服务管理-应用安装表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Data
@TableName("bsm_install")
public class InstallEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 应用id
	 */
	private Long fwqid;

	/*
	* 业务服务编码
	* */
	private String ywfwbm;

	/*
	 * 服务器名称
	 * */
	@TableField(exist = false)
	private String azfwq;

	/**
	 * 服务端口号
	 */
	private Long dkh;
	/**
	 * 安装位置
	 */
	private String azwz;
	/**
	 * 版本号
	 */
	private String bbh;
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


}
