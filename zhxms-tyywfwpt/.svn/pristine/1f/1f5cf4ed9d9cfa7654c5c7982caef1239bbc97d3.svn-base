package cn.com.bmsoft.modules.rm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 资源管理-链路设备表
 * 
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
@Data
@TableName("rm_link_device")
public class LinkDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 链路id
	 */
	private Integer llid;
	/**
	 * 设备表名
	 */
	private String sbbm;
	/**
	 * 设备表名名称
	 */
	private String sbbmmc;
	/**
	 * 设备ID
	 */
	private String sbid;
	/**
	 * 设备名称
	 */
	private String sbmc;
	/**
	 * 是否有效
	 */
	private String sfyx;

}
