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
@TableName("am_app_running")
public class AppRunningEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 应用ID
	 */
	private String appCode;
	/**
	 * 应用名称
	 */
	private String appName;
	/**
	 * 应用所在机器IP地址
	 */
	private String ipAddress;
	/**
	 * 应用所在机器MAC地址
	 */
	private String macAddress;
	/**
	 * 应用占用内存
	 */
	private String memUse;
	/**
	 * 应用占用内存使用率
	 */
	private String memUsePencent;
	/**
	 * 逻辑删除
	 */
	private Integer deleteFlag;

}
