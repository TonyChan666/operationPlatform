package cn.com.bmsoft.modules.sys.redis;

import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.utils.RedisKeys;
import cn.com.bmsoft.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户信息 Redis
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@Component
public class SysUserRedis {

    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(SysUserEntity sysUser) {
        if(sysUser == null){
            return ;
        }
        String key = RedisKeys.getSysUserKey(sysUser.getUserId());
        redisUtils.set(key, sysUser);
    }

    public void delete(Long userId) {
        String key = RedisKeys.getSysUserKey(userId);
        redisUtils.delete(key);
    }

    public SysUserEntity get(Long userId){
        String key = RedisKeys.getSysUserKey(userId);
        return redisUtils.get(key, SysUserEntity.class);
    }
}
