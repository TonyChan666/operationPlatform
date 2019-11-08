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
 * 数据采集任务表
 * 
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-10-10
 */
@Data
@TableName("l_collection_task")
public class CollectionTask implements Serializable {
	private static final long serialVersionUID = 1L;

	//任务id
	@TableId(value = "id",type= IdType.AUTO)
	private int id;
	//任务名称
	@TableField("table_name")
	private String tableName;
	//采集方式 0:接口采集
	//         1：文件采集
	@TableField("collect_type")
	private int collectType;
	//任务类型 app:软件
	//         device：硬件
	@TableField("mold_type")
	private String moldType;
	//采集频率
	@TableField("prequency")
	private String prequency;
	//任务状态  0:无效
	//          1：有效
	@TableField("status")
	private int status;
	//ip地址
	@TableField("collect_ip")
	private String collectIp;
	//端口
	@TableField("collect_port")
	private int collectPort;
	//文件所在目录
	@TableField("collect_path")
	private String collectPath;
	//文件名
	@TableField("collect_file_name")
	private String collectFileName;
	//服务器用户名
	@TableField("collect_username")
	private String collectUsername;
	//服务器密码
	@TableField("collect_password")
	private String collectPassword;
	//备注
	@TableField("remark")
	private String remark;

}
