package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.ContainerDao;
import cn.com.bmsoft.modules.rm.entity.ContainerEntity;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;
import cn.com.bmsoft.modules.rm.service.ContainerService;
import cn.com.bmsoft.modules.rm.service.DeploymentInfoService;
import cn.com.bmsoft.modules.rm.service.RmResourceDeptService;
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


@Service("containerService")
public class ContainerServiceImpl extends ServiceImpl<ContainerDao, ContainerEntity> implements ContainerService {

    @Autowired
    private ContainerDao containerDao;

    @Autowired
    private DeploymentInfoService deploymentInfoService;

    @Autowired
    private RmResourceDeptService rmResourceDeptService;

    final static String[] SEARCH_FIELDS = {"rqmc","ywjz","ywryid","sfyx"};

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContainerEntity> page = new Query<ContainerEntity>().getPage(params);
        return new PageUtils(containerDao.getContainerList(page,params));
    }

    @Override
    public void removeToRecoveryByIds(List<Integer> ids) {
        containerDao.removeToRecoveryByIds(ids);
    }

    @Override
    @Transactional
    public void saveContainer(ContainerEntity containerEntity){
        DeploymentInfoEntity deploymentInfoEntity = new DeploymentInfoEntity();
        BeanUtils.copyProperties(containerEntity,deploymentInfoEntity);
        this.save(containerEntity);
        deploymentInfoEntity.setZyid(containerEntity.getId()+"");
        deploymentInfoEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.CONTAINER_ENTITY_NAME));
        ValidatorUtils.validateEntity(deploymentInfoEntity);
        deploymentInfoService.save(deploymentInfoEntity);

        List<Integer> ywzids = containerEntity.getYwzid();
        if(!ywzids.isEmpty()) {
            List<RmResourceDeptEntity> rmResourceDeptEntities = new ArrayList<>();
            for (Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDeptEntity = new RmResourceDeptEntity();
                rmResourceDeptEntity.setZyid(containerEntity.getId());
                rmResourceDeptEntity.setYwzid(ywzid);
                rmResourceDeptEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.CONTAINER_ENTITY_NAME));
                rmResourceDeptEntities.add(rmResourceDeptEntity);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntities);
        }
    }

    @Override
    @Transactional
    public R updateContainer(ContainerEntity containerEntity) {
//        if(containerEntity.getRqbm() != null ) {
//            if(containerEntity.getRqbm().length() != 5) {
//                return R.error("请输入五位数容器编码");
//            }else {
//                //容器编码等于"RQ"+五位编码
//                containerEntity.setRqbm(RmParams.CODE_PREFIX.get(RmParams.CONTAINER_ENTITY_NAME) + containerEntity.getRqbm());
//            }
//        }
        String tableName = RmParams.RM_TABLE_NAME.get(RmParams.CONTAINER_ENTITY_NAME);
        DeploymentInfoEntity deploymentInfoEntity = new DeploymentInfoEntity();
        BeanUtils.copyProperties(containerEntity,deploymentInfoEntity);
        this.updateById(containerEntity);
        deploymentInfoEntity.setXgrid(containerEntity.getXgrid());
        deploymentInfoEntity.setXgsj(containerEntity.getXgsj());
        ValidatorUtils.validateEntity(deploymentInfoEntity);
        deploymentInfoService.update(deploymentInfoEntity,
                new QueryWrapper<DeploymentInfoEntity>().eq("zyid",containerEntity.getId())
                .eq("zybm",tableName));

        List<Integer> ywzids = containerEntity.getYwzid();
        List<RmResourceDeptEntity> rmResourceDeptEntitieList = new ArrayList<>();
        rmResourceDeptService.removeResourceDepts(containerEntity.getId()+"", tableName);
        if(!ywzids.isEmpty()){
            for(Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDept = new RmResourceDeptEntity();
                rmResourceDept.setYwzid(ywzid);
                rmResourceDept.setZyid(containerEntity.getId());
                rmResourceDept.setZybm(tableName);
                rmResourceDeptEntitieList.add(rmResourceDept);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntitieList);
        }
        return R.ok();
    }

    @Override
    public ContainerEntity getContainer(Integer id) {
        ContainerEntity container = containerDao.getContainer(id);
        if(container == null) {
            return null;
        }
        List<Map<String, Object>> resourceDeptNames = rmResourceDeptService.getResourceDeptNames(container.getId() + "", RmParams.RM_TABLE_NAME.get(RmParams.CONTAINER_ENTITY_NAME));
        List<Integer> ywzids = new ArrayList<>();
        String ywzmc = "";
        for (Map<String, Object> map : resourceDeptNames) {
            ywzids.add(Integer.parseInt(map.get("DEPT_ID")+""));
            ywzmc = (StringUtil.isEmpty(ywzmc)?"":(ywzmc+",")) + (StringUtil.isEmpty(map.get("DEPT_NAME")+"")?"":map.get("DEPT_NAME"));
        }
        container.setYwzid(ywzids);
        container.setYwzmc(ywzmc);
        return container;
    }


}