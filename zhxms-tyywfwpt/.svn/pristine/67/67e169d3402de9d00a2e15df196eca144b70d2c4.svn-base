package cn.com.bmsoft.modules.bsm.service;

import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 业务服务管理-应用服务表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
public interface ServiceService extends IService<ServiceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateBatchByIds(Long[] ywfwbm);

    List<ServiceEntity> selectUpgrade(String ywfwbm);

    int getId(String ywfwbm);

    List<ServiceEntity> ywfwlist(int appId);


    ServiceEntity getServiceById(int id);

    String ywfwbmYz(String ywfwbm);
}

