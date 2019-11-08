package cn.com.bmsoft.modules.bsm.dao;

import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 业务服务管理-应用服务表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Mapper
public interface ServiceDao extends BaseMapper<ServiceEntity> {

    void updateBatchByIds(Long[] ids);
    IPage<Map<String, Object>> ListPage(IPage<Map<String, Object>> page, @Param("params") Map<String, Object> params);

    List<ServiceEntity> selectUpgrade(String ywfwbm);

    int getId(String ywfwbm);

    List<ServiceEntity> ywfwlist(int appId);

    boolean updateStatus(ServiceEntity serviceEntity);

    int update(UpdateWrapper<ServiceEntity> wrapper);

    boolean deleteInstalls(Long[] ids);

    boolean deleteUpgrades(Long[] ids);

    ServiceEntity getServiceById(int id);

    String ywfwbmYz(String ywfwbm);

    List<DeploymentInfoEntity> getDeploymentInfos(List<Long> serviceIds);

    IPage<ServiceEntity> getServiceList(IPage<ServiceEntity> page, @Param("params")Map<String, Object> params);
}
