package cn.com.bmsoft.modules.wom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 工单管理-工单业务绑定表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-10-12
 */
@Data
@TableName("wom_business_binding")
public class BusinessBindingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 工单业务名称
	 */
	private String gdywmc;
	/**
	 * 模板id
	 */
	private String mbid;
	/**
	 * 模板名称
	 */
	private String mbmc;
	/**
	 * 是否删除
	 */
	private String delete_flag;
}
