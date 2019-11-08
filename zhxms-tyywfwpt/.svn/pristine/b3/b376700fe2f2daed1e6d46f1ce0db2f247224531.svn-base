package cn.com.bmsoft.modules.rm.dao;

import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @auther zhangyibing  zhangyibing@bmsoft.com.cn
* @Date 2019/9/30
* @desription
**/
@Mapper
public interface DeploymentInfoDao extends BaseMapper<DeploymentInfoEntity> {

    public void saveDeploymentInfo(DeploymentInfoEntity deploymentInfoEntity);

    public void saveDeploymentInfos(List<DeploymentInfoEntity> deploymentInfoEntitys);

    void removeDeploymentInfos(String zyid, String tableName);

    List<DeploymentInfoEntity> serverList(int id);
    List<DeploymentInfoEntity> systemlList(int id);
}
