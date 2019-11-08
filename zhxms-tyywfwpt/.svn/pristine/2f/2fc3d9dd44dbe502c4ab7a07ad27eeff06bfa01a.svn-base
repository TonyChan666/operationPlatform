package cn.com.bmsoft.modules.bsm.dao;

import cn.com.bmsoft.modules.bsm.entity.SystemEntity;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 业务服务管理-应用系统表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Mapper
public interface SystemDao extends BaseMapper<SystemEntity> {

    void updateBatchByIds(Long[] ids);

    IPage<Map<String, Object>> ListPage(IPage<Map<String, Object>> page, Map<String, Object> params);
    boolean updateStatus(SystemEntity serviceEntity);

    SystemEntity getSystemById(int id);

    String ywfwbmYz(String ywfwbm);

    IPage<SystemEntity> getSystemList(IPage<SystemEntity> page, @Param("params") Map<String, Object> params);

    List<DeploymentInfoEntity> getDeploymentInfos(List<Integer> serviceIds);
}
