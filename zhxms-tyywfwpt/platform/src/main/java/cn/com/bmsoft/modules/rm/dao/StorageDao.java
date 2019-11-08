package cn.com.bmsoft.modules.rm.dao;

import cn.com.bmsoft.modules.rm.entity.StorageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-资源存储表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
@Mapper
public interface StorageDao extends BaseMapper<StorageEntity> {

    public void removeToRecoveryByIds(List<Integer> ids);

    public IPage<StorageEntity> getStorageList(IPage<StorageEntity> page, @Param("params") Map<String,Object> params);

    public StorageEntity getStorage(Integer id);

}
