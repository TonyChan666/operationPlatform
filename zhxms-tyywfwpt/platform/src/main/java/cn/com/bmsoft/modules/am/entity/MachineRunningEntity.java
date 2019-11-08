package cn.com.bmsoft.modules.am.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@Data
@TableName("am_machine_running")
public class MachineRunningEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 服务器ID
	 */
	private String machineCode;
	/**
	 * 服务器IP地址
	 */
	private String ipAddress;
	/**
	 * 服务器mac地址
	 */
	private String macAddress;
	/**
	 * CPU使用率
	 */
	private String cpuUsePencent;
	/**
	 * 内存使用率
	 */
	private String memUsePencent;
	/**
	 * 硬盘使用率
	 */
	private String romUsePencent;
	/**
	 * 逻辑删除
	 */
	private Integer deleteFlag;

}
