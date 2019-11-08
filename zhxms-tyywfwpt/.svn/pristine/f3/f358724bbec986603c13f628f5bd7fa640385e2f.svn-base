package cn.com.bmsoft.modules.sys.controller;

import cn.com.bmsoft.annotation.SysLog;
import cn.com.bmsoft.modules.sys.entity.SysProviderEntity;
import cn.com.bmsoft.modules.sys.entity.SysRoleEntity;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.form.PasswordForm;
import cn.com.bmsoft.modules.sys.service.*;
import cn.com.bmsoft.utils.Constant;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.utils.StringUtil;
import cn.com.bmsoft.validator.ValidatorUtils;
import cn.com.bmsoft.validator.group.AddGroup;
import cn.com.bmsoft.validator.group.UpdateGroup;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.com.bmsoft.utils.Constant.GENERAL_OPERATIONS_PERSONNEL_ROLE;
import static cn.com.bmsoft.utils.Constant.OPERATIONS_GROUP_LEADER_ROLE;

/**
 * 系统用户
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;
//	@Autowired
//	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserDeptService sysUserDeptService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysProviderService sysProviderService;

	private static final String USERNAME_EXIST_WARN = "用户名已存在,请加序号区别: user1";
    private static final String NAME_EXIST_WARN = "姓名已存在,请加序号区别: 用户1";


    /**
	 * 验证用户名是否存在
	 */
	@GetMapping("/verifyUsername")
	public R verifyUsername(@Valid @RequestParam String username){
		boolean result = sysUserService.verifyUsername(username);
		if(result){
			return R.ok().put("data", R.error(0, USERNAME_EXIST_WARN));
		}
		return R.ok();
	}

	/**
	 * 验证姓名是否存在
	 */
	@GetMapping("/verifyName")
	public R verifyName(@Valid @RequestParam String name){
		boolean result = sysUserService.verifyName(name);
		if(result){
            return R.ok().put("data", R.error(0, NAME_EXIST_WARN));
		}
		return R.ok();
	}

	/**
	 * 所有用户分页列表
	 */
	@GetMapping("/list")
	// @RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}



	@GetMapping("/select/operations")
	public R selectOperations(@RequestParam Map<String, Object> queryMap){
		//设置角色选择的范围
		Long[] roleIds = {OPERATIONS_GROUP_LEADER_ROLE,
				GENERAL_OPERATIONS_PERSONNEL_ROLE};
		queryMap.put("roleIds", roleIds);
		PageUtils userPage = sysUserService.selectOperations(queryMap);
		return R.ok().put("page", userPage);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){

		SysUserEntity sysUserEntity = getUser();
		//获取用户所属的角色列表
//		List<Long> roleIdList = sysUserRoleService.queryRoleId(getUser().getUserId());
		Long roleId = getUser().getRoleId();
		//获取角色信息
		if(roleId != null) {
			SysRoleEntity sysRoleEntity = sysRoleService.getById(roleId);
			sysUserEntity.setSysRoleEntity(sysRoleEntity);
		}
		//获取业务组信息
//		SysDeptEntity sysDeptEntity = sysDeptService.getById(getUser().getDeptId());
//		if(sysDeptEntity != null){
//			sysUserEntity.setDeptName(sysDeptEntity.getDeptName());
//		}
		//获取供应商信息
		SysProviderEntity sysProviderEntity = sysProviderService.getById(getUser().getProviderId());
		if(sysProviderEntity != null){
			sysUserEntity.setProviderName(sysProviderEntity.getProviderName());
		}
		return R.ok().put("user", sysUserEntity);
	}
	
	/**
	 * 用户自己重置密码
	 */
	@SysLog("用户修改密码")
	@PostMapping("/userResetPassword")
	public R userResetPassword(@RequestBody PasswordForm form){
		ValidatorUtils.validateEntity(form, UpdateGroup.class);
		
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}

	/**
	 * 系统重置密码
	 */
	@SysLog("系统修改密码")
	@PostMapping("/sysResetPassword")
	public R sysResetPassword(@RequestBody SysUserEntity user){

		user.setUpdateUserId(getUserId());
		boolean flag = sysUserService.sysResetPassword(user);
		if(!flag){
			return R.error();
		}
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	// @RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);
		
		//获取用户所属的业务组列表
		List<Integer> deptIdList = sysUserDeptService.queryDeptIdList(userId);
		if(deptIdList != null && deptIdList.size() != 0){
			user.setDeptids(deptIdList);
		}
		String deptMc = sysUserDeptService.queryDeptName(userId);
		if (!StringUtil.isEmpty(deptMc)) {
			user.setDeptmc(deptMc);
		}

		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	// @RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		user.setCreateTime(new Date());
		user.setCreateUserId(getUserId());
		//非空判断
		ValidatorUtils.validateEntity(user, AddGroup.class);
		//用户名判断
		boolean usernameResult = sysUserService.verifyUsername(user.getUsername());
		if(usernameResult){
            return R.ok().put("data", R.error(0, USERNAME_EXIST_WARN));
		}
		//姓名判断
		boolean nameResult = sysUserService.verifyName(user.getName());
		if(nameResult){
            return R.ok().put("data", R.error(0, NAME_EXIST_WARN));
		}
		user.setCreateUserId(getUserId());
		sysUserService.saveUser(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	// @RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		user.setUpdateUserId(getUserId());
		user.setUpdateTime(new Date());
		user.setUpdateUserId(getUserId());
		sysUserService.update(user);

		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	 @SysLog("删除用户")
	 @PostMapping("/delete")
	// @RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		return R.ok();
	}

	@SysLog("更新用户状态")
	@PostMapping("/updateStatus")
//    @RequiresPermissions("sys:user:updateStatus")
	public R updateStatus(@RequestBody SysUserEntity user){
		Long id = user.getUserId();
		if(StringUtil.isEmpty(id+"")){
			return R.error("请指定要更新的用户");
		}
		user.setUpdateUserId(getUserId());
		user.setCreateTime(new Date());
		sysUserService.updateById(user);
		return R.ok();
	}
}
