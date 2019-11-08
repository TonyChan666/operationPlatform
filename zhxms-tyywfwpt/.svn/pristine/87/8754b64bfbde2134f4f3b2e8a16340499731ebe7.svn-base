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
 * 资源管理-网络设备表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
@Data
@TableName("rm_network_device")
public class NetworkDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 设备编号
	 */
	@NotBlank(message="设备编号不能为空", groups = {AddGroup.class})
	private String sbbh;
	/**
	 * 设备名称
	 */
	@NotBlank(message="设备名称不能为空", groups = {AddGroup.class})
	@Size(max = 40, message = "设备名称"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String sbmc;
	/**
	 * 设备类别
	 */
	@NotBlank(message="设备类别不能为空", groups = {AddGroup.class})
	private String sblb;
	/**
	 * 设备类别名称
	 */
	@TableField(exist = false)
	private String sblbmc;
	/**
	 * 所属网络类别
	 */
	@NotBlank(message="所属网络类别不能为空", groups = {AddGroup.class})
	private String wllb;
	/**
	 * 所属网络类别名称
	 */
	@TableField(exist = false)
	private String wllbmc;
	/**
	 * 机房编号
	 */
	@Size(max = 32, message = "机房编号"+ RmParams.FIELD_OVERMAX_TIPS + 32 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String jfbh;
	/**
	 * 设备序列号
	 */
	@NotBlank(message="设备序列号不能为空", groups = {AddGroup.class})
	@Size(max = 40, message = "设备序列号"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String sbxlh;
	/**
	 * IP地址
	 */
	@NotBlank(message="IP地址不能为空", groups = {AddGroup.class})
	@Size(max = 40, message = "IP地址"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String ip;
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
	 * 修改人id
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
}
