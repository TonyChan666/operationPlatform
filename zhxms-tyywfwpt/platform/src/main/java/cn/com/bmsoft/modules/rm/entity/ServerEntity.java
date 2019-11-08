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
 * 服务器登记表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
@Data
@TableName("rm_server")
public class ServerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 服务器编码
	 */
	@NotBlank(message="服务器编码不能为空", groups = {AddGroup.class})
	private String fwqbm;
	/**
	 * 设备名称
	 */
	@NotBlank(message="设备名称不能为空", groups = {AddGroup.class})
	/*message = "设备名称字段值长度过长，请不要超过40个字符"*/
	@Size(max = 40, message = "设备名称"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String sbmc;
	/**
	 * 设备序列号
	 */
	@Size(max = 40, message = "设备序列号"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String sbxlh;
	/**
	 * 设备品牌
	 */
	@Size(max = 32, message = "设备品牌"+ RmParams.FIELD_OVERMAX_TIPS + 32 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String sbpp;
	/**
	 * 设备型号
	 */
	@Size(max = 32, message = "设备品牌"+ RmParams.FIELD_OVERMAX_TIPS + 32 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String sbxh;
	/**
	 * 供应商
	 */
	private Integer gys;
	/**
	 * 维保商
	 */
	@Size(max = 40, message = "维保商"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String wbs;
	/**
	 * 操作系统
	 */
	@NotBlank(message="操作系统不能为空", groups = {AddGroup.class})
	@Size(max = 32, message = "操作系统"+ RmParams.FIELD_OVERMAX_TIPS + 32 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String czxt;
	/**
	 * IP地址
	 */
	@NotBlank(message="IP地址不能为空", groups = {AddGroup.class})
	@Size(max = 40, message = "IP地址"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String ip;
	/**
	 * 所属网络类别
	 */
	@NotBlank(message="网络类别不能为空", groups = {AddGroup.class})
	private String wllb;
	/**
	 * 所属网络类别名称
	 */
	@TableField(exist = false)
	private String wllbmc;
	/**
	 * 所属业务警种
	 */
	@NotBlank(message="业务警种不能为空", groups = {AddGroup.class})
	private String ywjz;
	/**
	 * 所属业务警种名称
	 */
	@TableField(exist = false)
	private String ywjzmc;
	/**
	 * 运维人员id
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
	 * 备注说明
	 */
	@Size(max = 100, message = "备注说明"+ RmParams.FIELD_OVERMAX_TIPS + 100 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String bzsm;
	/**
	 * 总内存容量
	 */
	@NotNull(message="内存容量不能为空", groups = {AddGroup.class})
//	@Size(max = 20, message = "内存容量"+ RmParams.FIELD_OVERMAX_TIPS + 20 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private Integer zncrl;
	/**
	 * 总内存容量单位
	 */
	@NotBlank(message="内存容量单位不能为空", groups = {AddGroup.class})
	private String zncrldw;
	/**
	 * CPU型号
	 */
	@Size(max = 32, message = "CPU型号"+ RmParams.FIELD_OVERMAX_TIPS + 32 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String cpuxh;
	/**
	 * 总CPU核数
	 */
//	@Size(max = 32, message = "总CPU核数"+ RmParams.FIELD_OVERMAX_TIPS + 32 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private Integer zcpuhs;
	/**
	 * 总内置硬盘物理容量
	 */
//	@Size(max = 20, message = "总内置硬盘物理容量"+ RmParams.FIELD_OVERMAX_TIPS + 20 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private Integer zyprl;
	/**
	 * 总内置硬盘物理容量单位
	 */
	private String zyprldw;
	/**
	 * 电源功率
	 */
//	@Size(max = 20, message = "电源功率"+ RmParams.FIELD_OVERMAX_TIPS + 20 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private Integer dygl;
	/**
	 * 电源电压
	 */
	private String dydy;
	/**
	 * 网卡
	 */
	@Size(max = 32, message = "网卡"+ RmParams.FIELD_OVERMAX_TIPS + 32 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String wk;
	/**
	 * 所属机房编号
	 */
	@Size(max = 32, message = "机房编号"+ RmParams.FIELD_OVERMAX_TIPS + 32 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String jfbh;
	/**
	 * 机房位置备注
	 */
	@Size(max = 50, message = "机房位置备注"+ RmParams.FIELD_OVERMAX_TIPS + 50 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String jfwzbz;
	/**
	 * 所属机柜编号
	 */
	@Size(max = 32, message = "机柜编号"+ RmParams.FIELD_OVERMAX_TIPS + 32 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String jgbh;
	/**
	 * 起始U位
	 */
	@Size(max = 20, message = "起始U位"+ RmParams.FIELD_OVERMAX_TIPS + 20 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String qsuw;
	/**
	 * 占用U数
	 */
	@Size(max = 20, message = "占用U数"+ RmParams.FIELD_OVERMAX_TIPS + 20 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String zyus;
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
	 * 创建人
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
	/**
	 * 业务服务个数
	 */
	@TableField(exist = false)
	private Integer ywfw;
	/**
	 * 容器个数
	 */
	@TableField(exist = false)
	private Integer rq;
	/**
	 * 组件个数
	 */
	@TableField(exist = false)
	private Integer zj;
	/**
	 * 存储资源个数
	 */
	@TableField(exist = false)
	private Integer cczy;
	/**
	 * 链路个数
	 */
	@TableField(exist = false)
	private Integer ll;

}
