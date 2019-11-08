package cn.com.bmsoft.modules.sys.controller;

import cn.com.bmsoft.annotation.SysLog;
import cn.com.bmsoft.modules.sys.entity.SysRoleEntity;
import cn.com.bmsoft.modules.sys.service.SysRoleMenuService;
import cn.com.bmsoft.modules.sys.service.SysRoleService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 */
	@GetMapping("/list")
	// @RequiresPermissions("sys:role:list")
	public R list(@RequestParam Map<String, Object> params){

		PageUtils page = sysRoleService.queryPage(params);
		return R.ok().put("page", page);
	}
	
	/**
	 * 角色列表
	 */
	@GetMapping("/select")
	// @RequiresPermissions("sys:role:select")
	public R select(){

		Map<String, Object> map = new HashMap<>();
		List<SysRoleEntity> list = (List<SysRoleEntity>) sysRoleService.listByMap(map);
		return R.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 */
	@GetMapping("/info/{roleId}")
	// @RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.getById(roleId);
		if(null == role){
			return R.ok("未找到该角色");
		}
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return R.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@PostMapping("/save")
	// @RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role){
		role.setCreateUserId(getUserId());
		role.setCreateTime(new Date());
		ValidatorUtils.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.saveRole(role);
		
		return R.ok();
	}
	
	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@PostMapping("/update")
	// @RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleEntity role){
		role.setUpdateTime(new Date());
		role.setUpdateUserId(getUserId());
		ValidatorUtils.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.update(role);
		
		return R.ok();
	}
	
	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@PostMapping("/delete")
	// @RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		Integer count = sysRoleService.getUserByRoleId(roleIds);
		if(count == null || count >= 0){
			return R.error("该角色下存在人员，请先删除对应人员后再删除角色");
		}
		sysRoleService.deleteBatch(roleIds);
		return R.ok();
	}
}
