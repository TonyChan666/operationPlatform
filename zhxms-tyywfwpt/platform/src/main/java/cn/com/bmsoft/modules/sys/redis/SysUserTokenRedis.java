package cn.com.bmsoft.modules.sys.redis;

import cn.com.bmsoft.modules.sys.entity.SysUserTokenEntity;
import cn.com.bmsoft.utils.RedisKeys;
import cn.com.bmsoft.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户token Redis
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@Component
public class SysUserTokenRedis {

    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(SysUserTokenEntity userToken) {
        if(userToken == null){
            return ;
        }
        String key = RedisKeys.getSysUserTokenKey(userToken.getToken());
        redisUtils.set(key, userToken);
    }

    public void delete(String token) {
        String key = RedisKeys.getSysUserTokenKey(token);
        redisUtils.delete(key);
    }

    public SysUserTokenEntity get(String token){
        String key = RedisKeys.getSysUserTokenKey(token);
        return redisUtils.get(key, SysUserTokenEntity.class);
    }
}
