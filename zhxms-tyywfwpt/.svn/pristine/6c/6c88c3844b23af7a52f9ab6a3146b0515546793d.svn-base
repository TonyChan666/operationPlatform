package cn.com.bmsoft.modules.sys.entity;

import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空", groups = {AddGroup.class})
	private String username;

	/**
	 * 姓名
	 */
	@NotBlank(message="姓名不能为空", groups = {AddGroup.class})
	private String name;

	/**
	 * 密码
	 */
	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	@Pattern(regexp="^.{8,}$", message="密码长度不能少于8位", groups = {AddGroup.class})
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class})
	@Email(message="邮箱格式不正确", groups = {AddGroup.class})
	private String email;

	/**
	 * 手机号
	 */
	private String mobile;

//	/**
//	 * 所属机构ID
//	 */
//	private Integer deptId;
//	/**
//	 * 所属机构名
//	 */
//	@TableField(exist=false)
//	private String deptName;

	/**
	 * 所属供应商ID
	 */
	private Integer providerId;
	/**
	 * 所属供应商名
	 */
	@TableField(exist=false)
	private String providerName;

	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;
	@TableField(exist=false)
	private String statusName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 是否发送短信（0：不发送，1：发送）
	 */
	private String flagSendMsg;

	/**
	 * 所属业务组id
	 */
	@TableField(exist=false)
	private List<Integer> deptids;

	/**
	 * 所属业务组名称
	 */
	@TableField(exist=false)
	private String deptmc;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 创建人ID
	 */
	private Long createUserId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改人ID
	 */
	private Long updateUserId;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 角色信息
	 */
	@TableField(exist=false)
	private SysRoleEntity sysRoleEntity;

}
