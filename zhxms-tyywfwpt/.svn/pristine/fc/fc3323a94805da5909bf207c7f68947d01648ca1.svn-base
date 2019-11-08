package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.DeploymentInfoDao;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.modules.rm.service.DeploymentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("deploymentInfoService")
public class DeploymentInfoServiceImpl extends ServiceImpl<DeploymentInfoDao, DeploymentInfoEntity> implements DeploymentInfoService {

    @Autowired
    private DeploymentInfoDao deploymentInfoDao;

    @Override
    public void saveDeploymentInfo(DeploymentInfoEntity deploymentInfoEntity) {
        this.save(deploymentInfoEntity);
    }

    @Override
    public void saveDeploymentInfos(List<DeploymentInfoEntity> deploymentInfoEntitys) {
        deploymentInfoDao.saveDeploymentInfos(deploymentInfoEntitys);
    }

    @Override
    public void removeDeploymentInfos(String zyid, String tableName){
        deploymentInfoDao.removeDeploymentInfos(zyid, tableName);
    }

    @Override
    public List<DeploymentInfoEntity> serverList(int id) {
        return  deploymentInfoDao.serverList(id);
    }
    @Override
    public List<DeploymentInfoEntity> systemlList(int id) {
        return  deploymentInfoDao.systemlList(id);
    }
}