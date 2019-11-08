package cn.com.bmsoft.modules.sys.service;

import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.modules.sys.entity.SysUserTokenEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户Token
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
