package cn.com.bmsoft.modules.rm.dao;

import cn.com.bmsoft.modules.rm.entity.ServerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 服务器登记表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
@Mapper
public interface ServerDao extends BaseMapper<ServerEntity> {

    public void removeToRecoveryByIds(List<Integer> ids);

    IPage<ServerEntity> getServerList(IPage<ServerEntity> page,@Param("params") Map<String, Object> params);

    ServerEntity getServer(@Param("params") Map<String, Object> params);
}
