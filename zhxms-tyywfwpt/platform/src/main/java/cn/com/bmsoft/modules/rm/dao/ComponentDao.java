package cn.com.bmsoft.modules.rm.dao;

import cn.com.bmsoft.modules.rm.entity.ComponentEntity;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-资源组件表
 * 
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
@Mapper
public interface ComponentDao extends BaseMapper<ComponentEntity> {

    public void removeToRecoveryByIds(List<Integer> ids);

    public IPage<ComponentEntity> getComponentList(IPage<ComponentEntity> page,@Param("params") Map<String,Object> params);

    public List<DeploymentInfoEntity> getDeploymentInfos(List<Integer> componentIds);

    public ComponentEntity getComponent(Integer id);

}
