package cn.com.bmsoft.modules.sys.controller;

import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
public abstract class AbstractController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取当前用户信息
	 * @return SysUserEntity
	 */
	protected SysUserEntity getUser() {

		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 获取当前用户ID
	 * @return Long
	 */
	protected Long getUserId() {
		return getUser().getUserId();
	}
}
