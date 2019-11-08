package cn.com.bmsoft.modules.rm.service;

import cn.com.bmsoft.modules.rm.entity.ServerEntity;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 服务器登记表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
public interface ServerService extends IService<ServerEntity> {

    PageUtils queryPage(Map<String, Object> params, SysUserEntity user);

    public void removeToRecoveryByIds(List<Integer> ids);

    public ServerEntity getServerEntity(String id, SysUserEntity user);

    public R updateServer(ServerEntity serverEntity);

    void saveServer(ServerEntity server);
}

