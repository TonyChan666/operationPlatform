package cn.com.bmsoft.modules.rm.service;

import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 资源管理-部署信息表
 *
 * @author zhangyibing zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
public interface DeploymentInfoService extends IService<DeploymentInfoEntity> {

    public void saveDeploymentInfo(DeploymentInfoEntity deploymentInfoEntity);

    public void saveDeploymentInfos(List<DeploymentInfoEntity> deploymentInfoEntitys);

    public void removeDeploymentInfos(String zyid, String tableName);


    List<DeploymentInfoEntity> serverList(int id);
    List<DeploymentInfoEntity> systemlList(int id);
}

