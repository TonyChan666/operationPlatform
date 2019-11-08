package cn.com.bmsoft.modules.rm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-11-03
 */
@Data
@TableName("rm_resource_dept")
public class RmResourceDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 资源id
	 */
	private Integer zyid;
	/**
	 * 业务组id
	 */
	private Integer ywzid;
	/**
	 * 资源表名
	 */
	private String zybm;

}
