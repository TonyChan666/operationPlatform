package cn.com.bmsoft.modules.sys.service;

import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
public interface SysUserService extends IService<SysUserEntity> {

	/**
	 * 选择运维人员
	 */
	PageUtils selectOperations(Map<String, Object> queryMap);




	/**
	 * 获取用户详细信息
	 * @param id
	 * @return
	 */
	SysUserEntity getById(Long id);

	/**
	 * 分页列表
	 * @param params
	 * @return
	 */
	PageUtils queryPage(Map<String, Object> params);

	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	/**
	 * 保存用户
	 */
	void saveUser(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);

	/**
	 * 删除用户
	 */
	void deleteBatch(Long[] userIds);

	/**
	 * 验证用户名是否存在
	 */
	boolean verifyUsername(String username);

	/**
	 * 验证姓名是否存在
	 */
	boolean verifyName(String name);

	/**
	 * 系统重置密码
	 */
	boolean sysResetPassword(SysUserEntity user);

	/**
	 * 用户自己修改密码
	 */
	boolean updatePassword(Long userId, String password, String newPassword);

    Integer getUserByRoleId(Long[] roleId);
}
