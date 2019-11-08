package cn.com.bmsoft.modules.rm.entity;

import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 资源管理-资源容器表
 * 
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
@Data
@TableName("rm_container")
public class ContainerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 容器编码
	 */
	@NotBlank(message="容器编码不能为空", groups = {AddGroup.class})
	private String rqbm;
	/**
	 * 容器名称
	 */
	@NotBlank(message="容器名称不能为空", groups = {AddGroup.class})
	@Size(max = 40, message = "容器名称"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String rqmc;
	/**
	 * 所属业务警种
	 */
	@NotBlank(message="所属业务警种不能为空", groups = {AddGroup.class})
	private String ywjz;
	/**
	 * 所属业务警种名称
	 */
	@TableField(exist = false)
	private String ywjzmc;
	/**
	 * 运维人员
	 */
	@NotNull(message="运维人员不能为空", groups = {AddGroup.class})
	private Integer ywryid;
	/**
	 * 运维人员姓名
	 */
	@TableField(exist = false)
	private String ywry;
	/**
	 * 运维人员姓名(多余字段，前端需要)
	 */
	@TableField(exist = false)
	private String ywrymc;
	/**
	 * 业务组ID
	 */
	@TableField(exist = false)
	private List<Integer> ywzid;
	/**
	 * 业务组名称
	 */
	@TableField(exist = false)
	private String ywzmc;
	/**
	 * 备注说明
	 */
	@Size(max = 400, message = "备注说明"+ RmParams.FIELD_OVERMAX_TIPS + 400 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String bzsm;
	/**
	 * 创建人id
	 */
	private Integer cjrid;
	/**
	 * 创建时间
	 */
	private Date cjsj;
	/**
	 * 最后修改人
	 */
	private Integer xgrid;
	/**
	 * 最后修改时间
	 */
	private Date xgsj;
	/**
	 * 是否有效
	 */
	private String sfyx;
	/**
	 * 是否删除
	 */
	private String deleteFlag;

	/*部署信息*/
	/**
	 * 版本号
	 */
	@TableField(exist = false)
	private String bbh;
	/**
	 * 安装服务器id
	 */
	@TableField(exist = false)
	private Integer fwqid;
	/**
	 * 安装服务器
	 */
	@TableField(exist = false)
	private String azfwq;
	/**
	 * 安装位置
	 */
	@TableField(exist = false)
	private String azwz;
}
