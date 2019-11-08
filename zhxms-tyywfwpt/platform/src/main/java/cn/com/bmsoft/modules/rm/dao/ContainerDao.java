package cn.com.bmsoft.modules.rm.dao;

import cn.com.bmsoft.modules.rm.entity.ContainerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-资源容器表
 * 
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
@Mapper
public interface ContainerDao extends BaseMapper<ContainerEntity> {
	public void removeToRecoveryByIds(List<Integer> ids);

	public IPage<ContainerEntity> getContainerList(IPage<ContainerEntity> page,@Param("params") Map<String,Object> params);

	public ContainerEntity getContainer(Integer id);
}
