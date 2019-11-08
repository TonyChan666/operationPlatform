package cn.com.bmsoft.modules.rm.entity;

import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 资源管理-探针登记表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
@Data
@TableName("rm_probe")
public class ProbeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 探针编号
	 */
	//@NotBlank(message="探针编号不能为空", groups = {AddGroup.class})
	private String tzbh;
	/**
	 * 探针应用名称
	 */
	@NotBlank(message="探针应用名称不能为空", groups = {AddGroup.class})
	@Size(max = 40, message = "探针应用名称"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String tzyymc;
	/**
	 * 探针配置
	 */
	@NotBlank(message="探针配置不能为空", groups = {AddGroup.class})
	private String tzpz;
	/**
	 * 探针配置名称
	 */
	@TableField(exist = false)
	private String tzpzmc;
	/**
	 * 接入配置
	 */
	@NotBlank(message="接入配置不能为空", groups = {AddGroup.class})
	private String jrpz;
	/**
	 * 接入配置名称
	 */
	@TableField(exist = false)
	private String jrpzmc;
	/**
	 * 业务警种
	 */
	@NotBlank(message="接入业务警种不能为空", groups = {AddGroup.class})
	private String ywjz;
	/**
	 * 业务警种名称
	 */
	@TableField(exist = false)
	private String ywjzmc;
	/**
	 * 配置服务器
	 */
	@Size(max = 50, message = "配置服务器"+ RmParams.FIELD_OVERMAX_TIPS + 50 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String pzfwq;
	/**
	 * AcessKey
	 */
	@Size(max = 50, message = "acessKey"+ RmParams.FIELD_OVERMAX_TIPS + 50 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String acessKey;
	/**
	 * 推送频率
	 */
	@Size(max = 50, message = "推送频率"+ RmParams.FIELD_OVERMAX_TIPS + 50 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String tspl;
	/**
	 * 探针说明
	 */
	@Size(max = 400, message = "探针说明"+ RmParams.FIELD_OVERMAX_TIPS + 400 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
	private String tzsm;
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
