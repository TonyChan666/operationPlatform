package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.StorageDao;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;
import cn.com.bmsoft.modules.rm.entity.StorageEntity;
import cn.com.bmsoft.modules.rm.service.DeploymentInfoService;
import cn.com.bmsoft.modules.rm.service.RmResourceDeptService;
import cn.com.bmsoft.modules.rm.service.StorageService;
import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.utils.StringUtil;
import cn.com.bmsoft.validator.ValidatorUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("storageService")
public class StorageServiceImpl extends ServiceImpl<StorageDao, StorageEntity> implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Autowired
    private DeploymentInfoService deploymentInfoService;

    @Autowired
    private RmResourceDeptService rmResourceDeptService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StorageEntity> page = new Query<StorageEntity>().getPage(params);
        return new PageUtils(storageDao.getStorageList(page,params));
    }

    @Override
    public void removeToRecoveryByIds(List<Integer> ids) {
        storageDao.removeToRecoveryByIds(ids);
    }

    @Override
    @Transactional
    public void saveStorage(StorageEntity storageEntity){
        DeploymentInfoEntity deploymentInfoEntity = new DeploymentInfoEntity();
        BeanUtils.copyProperties(storageEntity,deploymentInfoEntity);
        this.save(storageEntity);
        deploymentInfoEntity.setZyid(storageEntity.getId()+"");
        deploymentInfoEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.STORAGE_ENTITY_NAME));
        ValidatorUtils.validateEntity(deploymentInfoEntity);
        List<Integer> ywzids = storageEntity.getYwzid();
        if(!ywzids.isEmpty()) {
            List<RmResourceDeptEntity> rmResourceDeptEntities = new ArrayList<>();
            for (Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDeptEntity = new RmResourceDeptEntity();
                rmResourceDeptEntity.setZyid(storageEntity.getId());
                rmResourceDeptEntity.setYwzid(ywzid);
                rmResourceDeptEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.STORAGE_ENTITY_NAME));
                rmResourceDeptEntities.add(rmResourceDeptEntity);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntities);
        }

        deploymentInfoService.save(deploymentInfoEntity);
    }

    @Override
    @Transactional
    public R updateStorage(StorageEntity storageEntity) {
//        if(storageEntity.getCcbh() != null ) {
//            if(storageEntity.getCcbh().length() != 5) {
//                return R.error("请输入五位数存储资源编码");
//            }else{
//                //设备编号等于"CC"+五位编码
//                storageEntity.setCcbh(RmParams.CODE_PREFIX.get(RmParams.STORAGE_ENTITY_NAME) + storageEntity.getCcbh());
//            }
//        }
        String tableName = RmParams.RM_TABLE_NAME.get(RmParams.STORAGE_ENTITY_NAME);
        DeploymentInfoEntity deploymentInfoEntity = new DeploymentInfoEntity();
        BeanUtils.copyProperties(storageEntity,deploymentInfoEntity);
        this.updateById(storageEntity);
        deploymentInfoEntity.setXgrid(storageEntity.getXgrid());
        deploymentInfoEntity.setXgsj(storageEntity.getXgsj());
        deploymentInfoService.update(deploymentInfoEntity,
                new QueryWrapper<DeploymentInfoEntity>().eq("zyid", storageEntity.getId())
                        .eq("zybm", tableName));

        List<Integer> ywzids = storageEntity.getYwzid();
        List<RmResourceDeptEntity> rmResourceDeptEntitieList = new ArrayList<>();
        rmResourceDeptService.removeResourceDepts(storageEntity.getId()+"", tableName);
        if(!ywzids.isEmpty()){
            for(Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDept = new RmResourceDeptEntity();
                rmResourceDept.setYwzid(ywzid);
                rmResourceDept.setZyid(storageEntity.getId());
                rmResourceDept.setZybm(tableName);
                rmResourceDeptEntitieList.add(rmResourceDept);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntitieList);
        }

        return R.ok();
    }

    @Override
    public StorageEntity getStorage(Integer id) {
        StorageEntity storage = storageDao.getStorage(id);
        if(storage == null) {
            return null;
        }
        List<Map<String, Object>> resourceDeptNames = rmResourceDeptService.getResourceDeptNames(storage.getId() + "", RmParams.RM_TABLE_NAME.get(RmParams.STORAGE_ENTITY_NAME));
        List<Integer> ywzids = new ArrayList<>();
        String ywzmc = "";
        for (Map<String, Object> map : resourceDeptNames) {
            ywzids.add(Integer.parseInt(map.get("DEPT_ID")+""));
            ywzmc = (StringUtil.isEmpty(ywzmc)?"":(ywzmc+",")) + (StringUtil.isEmpty(map.get("DEPT_NAME")+"")?"":map.get("DEPT_NAME"));
        }
        storage.setYwzid(ywzids);
        storage.setYwzmc(ywzmc);
        return storage;
    }

}