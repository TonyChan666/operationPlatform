package cn.com.bmsoft.modules.sys.dao;

import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

	/**
	 * 选择运维人员
	 */
	IPage<SysUserEntity> selectOperations(IPage<SysUserEntity> page, @Param("params") Map<String, Object> params);

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

}
