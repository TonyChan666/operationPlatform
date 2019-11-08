package cn.com.bmsoft.modules.bsm.service.impl;

import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.bsm.dao.ServiceDao;
import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.bsm.service.ServiceService;
import org.springframework.transaction.annotation.Transactional;


@Service("serviceService")
public class ServiceServiceImpl extends ServiceImpl<ServiceDao, ServiceEntity> implements ServiceService {
    @Autowired
    private ServiceDao serviceDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServiceEntity> page = new Query<ServiceEntity>().getPage(params);
        PageUtils pageUtils = new PageUtils(serviceDao.getServiceList(page,params));
        List<ServiceEntity> records = (List<ServiceEntity>)pageUtils.getList();
        if(records.isEmpty()){
            return pageUtils;
        }
        records = getDeploymentInfo(records);
        page.setRecords(records);
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void updateBatchByIds(Long[] id) {
        serviceDao.updateBatchByIds(id);
        serviceDao.deleteInstalls(id);
        serviceDao.deleteUpgrades(id);
    }

    @Override
    public List<ServiceEntity> selectUpgrade(String ywfwbm) {
        return serviceDao.selectUpgrade(ywfwbm);
    }

    @Override
    public int getId(String ywfwbm) {
        return serviceDao.getId(ywfwbm);
    }

    @Override
    public List<ServiceEntity> ywfwlist(int appId) {
        return serviceDao.ywfwlist(appId);
    }

    @Override
    public ServiceEntity getServiceById(int id) {
        return serviceDao.getServiceById(id);
    }

    @Override
    public String ywfwbmYz(String ywfwbm) {
        return serviceDao.ywfwbmYz(ywfwbm);
    }


    @Override
    public boolean saveBatch(Collection<ServiceEntity> entityList) {
        return false;
    }

    /**
     * 传入不带部署信息的serviceEntity列表信息，返回带部署信息的列表信息
     * @param records 不带部署信息的serviceEntity列表信息
     * @return 带部署信息的列表信息
     */
    private List<ServiceEntity> getDeploymentInfo(List<ServiceEntity> records) {
        List<Long> serviceIds = new ArrayList<Long>();
        Map<Long, ServiceEntity> deploymentInfo = new HashMap<Long, ServiceEntity>();
        for (ServiceEntity serviceEntity : records) {
            serviceIds.add(serviceEntity.getId());
            deploymentInfo.put(serviceEntity.getId(), serviceEntity);
        }
        records = new ArrayList<ServiceEntity>();
        List<DeploymentInfoEntity> deploymentInfos = serviceDao.getDeploymentInfos(serviceIds);
        for(DeploymentInfoEntity de : deploymentInfos) {
            ServiceEntity service = deploymentInfo.get(Long.valueOf(de.getZyid()));
            service.setAzfwq(StringUtil.isEmpty(service.getAzfwq())?de.getAzfwq():service.getAzfwq()+","+de.getAzfwq());
        }
        for(Long id : deploymentInfo.keySet()) {
            records.add(deploymentInfo.get(id));
        }
        return records;
    }

}