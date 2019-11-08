package cn.com.bmsoft.modules.sys.service.impl;

import cn.com.bmsoft.exception.RRException;
import cn.com.bmsoft.modules.sys.dao.SysUserDao;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.redis.SysUserRedis;
import cn.com.bmsoft.modules.sys.service.SysRoleService;
import cn.com.bmsoft.modules.sys.service.SysUserDeptService;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.Constant;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 系统用户
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserDeptService sysUserDeptService;
	@Autowired
	private SysUserRedis sysUserRedis;


	@Override
	public PageUtils selectOperations(Map<String, Object> queryMap){
		IPage<SysUserEntity> page = new Query<SysUserEntity>().getPage(queryMap);
		IPage<SysUserEntity> sysUserEntityPage = baseMapper.selectOperations(page, queryMap);
		List<Long> userIds = new ArrayList<>();
		for(SysUserEntity user : page.getRecords()) {
			userIds.add(user.getUserId());
		}
		if(!userIds.isEmpty()) {
			Map<Integer, String> deptNamesMap = sysUserDeptService.queryDeptNameByUserIds(userIds);
			for (Integer key : deptNamesMap.keySet()) {
				for (SysUserEntity user : page.getRecords()) {
					if (user.getUserId().intValue() == key) {
						user.setDeptmc(deptNamesMap.get(key));
					}
				}
			}
		}
		return new PageUtils(sysUserEntityPage);
	}


	@Override
	public SysUserEntity getById(Long id){
		return this.getOne(new QueryWrapper<SysUserEntity>().eq("user_id", id)
				.select("user_id",
						"username",
						"name",
						"email",
						"mobile",
						"remark",
						"create_user_id",
						"create_time",
						"update_user_id",
						"update_time",
						"status",
						"salt",
						"provider_id",
						"flag_send_msg",
						"role_id",
						"(select t.name from c_dictionary_item t where t.dict_code='ryzt' and t.value=sys_user.status) status_name",
						"(select t.provider_name from sys_provider t where t.provider_id=sys_user.provider_id) provider_name"));
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		String name = (String)params.get("name");
		String mobile = (String)params.get("mobile");

		QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
		wrapper.select("user_id",
				"username",
				"name",
				"email",
				"mobile",
				"remark",
				"create_user_id",
				"create_time",
				"update_user_id",
				"update_time",
				"status",
				"salt",
				"role_id",
				"flag_send_msg",
				"(select t.name from c_dictionary_item t where t.dict_code='ryzt' and t.value=sys_user.status) status_name",
				"(select t.provider_name from sys_provider t where t.provider_id=sys_user.provider_id) provider_name")
				.like(StringUtils.isNotBlank(username),"username", username)
				.like(StringUtils.isNotBlank(name),"name", name)
				.like(StringUtils.isNotBlank(mobile),"mobile", mobile)
				.eq(params.get("status") != null,"status", params.get("status"))
				.eq(params.get("providerId") != null,"provider_id", params.get("providerId"));
		if(params.get("deptId") != null) {
			wrapper.inSql("user_id" , "select user_id from sys_user_dept where dept_id = '"+params.get("deptId")+"'");
		}

		IPage<SysUserEntity> page = this.page(
			new Query<SysUserEntity>().getPage(params),
				wrapper
		);
		List<Long> userIds = new ArrayList<>();
		for(SysUserEntity user : page.getRecords()) {
			userIds.add(user.getUserId());
		}
		if(!userIds.isEmpty()) {
			Map<Integer, String> deptNamesMap = sysUserDeptService.queryDeptNameByUserIds(userIds);
			for (Integer key : deptNamesMap.keySet()) {
				for(SysUserEntity user : page.getRecords()) {
					if(user.getUserId().intValue() == key){
						user.setDeptmc(deptNamesMap.get(key));
					}
				}
			}
		}
		return new PageUtils(page);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	@Transactional
	public void saveUser(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		this.save(user);
		
		//检查角色是否越权
		checkRole(user, false);
		
		//保存用户与业务组关系
		sysUserDeptService.saveOrUpdate(user.getUserId().intValue(),user.getDeptids());
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		this.updateById(user);
		Long roleId = user.getRoleId();
		List<Integer> deptids = user.getDeptids();
		user = getById(user.getUserId());
		//检查角色是否越权
		checkRole(user, true);
		
		//保存用户与业务组的关系
		sysUserDeptService.saveOrUpdate(user.getUserId().intValue(),deptids);

		//redis更新
		sysUserRedis.saveOrUpdate(user);

	}

	@Override
	public void deleteBatch(Long[] userId) {
		this.removeByIds(Arrays.asList(userId));
	}

	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user, boolean isUpdate){
		if(user.getRoleId() == null ){
			return;
		}
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户创建的角色列表
		List<Long> roleIdList;
		if(isUpdate) {
			//更新时根据更新人获取角色列表
			roleIdList = sysRoleService.queryRoleIdList(user.getUpdateUserId());
		} else {
			//添加用户时根据添加人获取角色列表
			roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());
		}

		//判断是否越权
		if(!roleIdList.contains(user.getRoleId())){
			throw new RRException("用户所选角色不是本人创建");
		}
	}

	/**
	 * 验证用户名是否存在
	 */
	@Override
	public boolean verifyUsername(String username){
		QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<SysUserEntity>();
		queryWrapper.eq(StringUtils.isNotBlank(username),"username", username);
		SysUserEntity sysUserEntity = this.getOne(queryWrapper);
		if(null == sysUserEntity){
			return false;
		}
		return true;
	}

	/**
	 * 验证姓名是否存在
	 */
	@Override
	public boolean verifyName(String name){
		QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<SysUserEntity>();
		queryWrapper.eq(StringUtils.isNotBlank(name),"name", name);
		SysUserEntity sysUserEntity = this.getOne(queryWrapper);
		if(null == sysUserEntity){
			return false;
		}
		return true;
	}

	/**
	 * 系统重置密码
	 */
	@Override
	public boolean sysResetPassword(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		return this.updateById(user);
	}

	/**
	 * 用户自己重置密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 * @return
	 */
	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
	}

	@Override
	public Integer getUserByRoleId(Long[] roleId){
		QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
		wrapper.in("role_id",roleId);
		return this.count(wrapper);
	}

}