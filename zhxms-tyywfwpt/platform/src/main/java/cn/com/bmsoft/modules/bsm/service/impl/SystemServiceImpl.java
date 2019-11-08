package cn.com.bmsoft.modules.bsm.service.impl;

import cn.com.bmsoft.modules.bsm.dao.ServiceDao;
import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.bsm.dao.SystemDao;
import cn.com.bmsoft.modules.bsm.entity.SystemEntity;
import cn.com.bmsoft.modules.bsm.service.SystemService;
import org.springframework.transaction.annotation.Transactional;


@Service("systemService")
public class SystemServiceImpl extends ServiceImpl<SystemDao, SystemEntity> implements SystemService {
    @Autowired
    private SystemDao bsmSystemDao;
    @Autowired
    private ServiceDao serviceDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SystemEntity> page = new Query<SystemEntity>().getPage(params);
        PageUtils pageUtils = new PageUtils(bsmSystemDao.getSystemList(page,params));
        List<SystemEntity> records = (List<SystemEntity>)pageUtils.getList();
        if(records.isEmpty()){
            return pageUtils;
        }
        records = getDeploymentInfo(records);
        page.setRecords(records);

        return new PageUtils(page);
    }



    @Override
    @Transactional
    public void updateBatchByIds(Long[] ids) {
        bsmSystemDao.updateBatchByIds(ids);
        serviceDao.deleteInstalls(ids);
        serviceDao.deleteUpgrades(ids);
    }



    @Override
    public SystemEntity getSystemById(int id) {
        return bsmSystemDao.getSystemById(id);
    }

    @Override
    public String ywfwbmYz(String ywfwbm) {
        return bsmSystemDao.ywfwbmYz(ywfwbm);
    }
    /**
     * 传入不带部署信息的serviceEntity列表信息，返回带部署信息的列表信息
     * @param records 不带部署信息的serviceEntity列表信息
     * @return 带部署信息的列表信息
     */
    private List<SystemEntity> getDeploymentInfo(List<SystemEntity> records) {
        List<Integer> serviceIds = new ArrayList<Integer>();
        Map<Integer, SystemEntity> deploymentInfo = new HashMap<Integer, SystemEntity>();
        for (SystemEntity systemEntity : records) {
            serviceIds.add(systemEntity.getId());
            deploymentInfo.put(systemEntity.getId(), systemEntity);
        }
        records = new ArrayList<SystemEntity>();
        List<DeploymentInfoEntity> deploymentInfos = bsmSystemDao.getDeploymentInfos(serviceIds);
        for(DeploymentInfoEntity de : deploymentInfos) {
            SystemEntity service = deploymentInfo.get(Integer.valueOf(de.getZyid()));
            service.setAzfwq(StringUtil.isEmpty(service.getAzfwq())?de.getAzfwq():service.getAzfwq()+","+de.getAzfwq());
        }
        for(Integer id : deploymentInfo.keySet()) {
            records.add(deploymentInfo.get(id));
        }
        return records;
    }

}