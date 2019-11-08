package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.ComponentDao;
import cn.com.bmsoft.modules.rm.entity.ComponentEntity;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;
import cn.com.bmsoft.modules.rm.service.ComponentService;
import cn.com.bmsoft.modules.rm.service.DeploymentInfoService;
import cn.com.bmsoft.modules.rm.service.RmResourceDeptService;
import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.utils.StringUtil;
import cn.com.bmsoft.validator.ValidatorUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("componentService")
public class ComponentServiceImpl extends ServiceImpl<ComponentDao, ComponentEntity> implements ComponentService {

    @Autowired
    private DeploymentInfoService deploymentInfoService;

    @Autowired
    private ComponentDao componentDao;

    @Autowired
    private RmResourceDeptService rmResourceDeptService;

    final static String[] SEARCH_FIELDS = {"zjmc","ywjz","ywryid","sfyx"};

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ComponentEntity> page = new Query<ComponentEntity>().getPage(params);
        PageUtils pageUtils = new PageUtils(componentDao.getComponentList(page,params));
        List<ComponentEntity> records = (List<ComponentEntity>)pageUtils.getList();
        if(records.isEmpty()){
            return pageUtils;
        }
        records = records2recordsWithDeploymentInfo(records);
        page.setRecords(records);
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public R saveComponent(ComponentEntity component, SysUserEntity user) {
        List<DeploymentInfoEntity> deploymentInfoEntityList = component.getDeploymentInfoEntityList();
        if (!deploymentInfoEntityList.isEmpty()) {
            this.save(component);
            for(DeploymentInfoEntity depInfo : deploymentInfoEntityList) {
                depInfo.setZyid(component.getId()+"");
                depInfo.setCjrid(user.getUserId().intValue());
                depInfo.setCjsj(new Date());
                depInfo.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.COMPONENT_ENTITY_NAME));
                ValidatorUtils.validateEntity(depInfo);
            }
            deploymentInfoService.saveDeploymentInfos(deploymentInfoEntityList);
        }else{
            return R.error("请填写部署信息");
        }
        List<Integer> ywzids = component.getYwzid();
        if(!ywzids.isEmpty()) {
            List<RmResourceDeptEntity> rmResourceDeptEntities = new ArrayList<>();
            for (Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDeptEntity = new RmResourceDeptEntity();
                rmResourceDeptEntity.setZyid(component.getId());
                rmResourceDeptEntity.setYwzid(ywzid);
                rmResourceDeptEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.COMPONENT_ENTITY_NAME));
                rmResourceDeptEntities.add(rmResourceDeptEntity);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntities);
        }
        return R.ok();
    }

    @Override
    public void removeToRecoveryByIds(List<Integer> ids) {
        componentDao.removeToRecoveryByIds(ids);
    }

    @Override
    public ComponentEntity getComponentById(Integer id){
        ComponentEntity componentEntity = componentDao.getComponent(id);
        if(componentEntity == null){
            return null;
        }
        List<Integer> componentIds = new ArrayList<Integer>();
        componentIds.add(componentEntity.getId());
        List<DeploymentInfoEntity> deploymentInfos = componentDao.getDeploymentInfos(componentIds);
        componentEntity.setDeploymentInfoEntityList(deploymentInfos);

        List<Map<String, Object>> resourceDeptNames = rmResourceDeptService.getResourceDeptNames(componentEntity.getId() + "", RmParams.RM_TABLE_NAME.get(RmParams.COMPONENT_ENTITY_NAME));
        List<Integer> ywzids = new ArrayList<>();
        String ywzmc = "";
        for (Map<String, Object> map : resourceDeptNames) {
            ywzids.add(Integer.parseInt(map.get("DEPT_ID")+""));
            ywzmc = (StringUtil.isEmpty(ywzmc)?"":(ywzmc+",")) + (StringUtil.isEmpty(map.get("DEPT_NAME")+"")?"":map.get("DEPT_NAME"));
        }
        componentEntity.setYwzid(ywzids);
        componentEntity.setYwzmc(ywzmc);
        return componentEntity;
    }

    @Override
    @Transactional
    public R updateComponent(ComponentEntity component) {
//        if(component.getZjbh() != null ) {
//            if(component.getZjbh().length() != 5) {
//                return R.error("请输入五位数容器编码");
//            }else {
//                //容器编码等于"ZJ"+五位编码
//                component.setZjbh(RmParams.CODE_PREFIX.get(RmParams.COMPONENT_ENTITY_NAME) + component.getZjbh());
//            }
//        }

        List<DeploymentInfoEntity> deploymentInfoEntityList = component.getDeploymentInfoEntityList();
        if (!deploymentInfoEntityList.isEmpty()) {
            deploymentInfoService.removeDeploymentInfos(component.getId()+"",RmParams.RM_TABLE_NAME.get(RmParams.COMPONENT_ENTITY_NAME));
            for(DeploymentInfoEntity de : deploymentInfoEntityList){
                de.setZyid(component.getId()+"");
                de.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.COMPONENT_ENTITY_NAME));
                ValidatorUtils.validateEntity(de);
            }
            deploymentInfoService.saveDeploymentInfos(deploymentInfoEntityList);
        }else{
            return R.error("请填写部署信息");
        }
        String tableName = RmParams.RM_TABLE_NAME.get(RmParams.COMPONENT_ENTITY_NAME);
        List<Integer> ywzids = component.getYwzid();
        List<RmResourceDeptEntity> rmResourceDeptEntitieList = new ArrayList<>();
        rmResourceDeptService.removeResourceDepts(component.getId()+"", tableName);
        if(!ywzids.isEmpty()){
            for(Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDept = new RmResourceDeptEntity();
                rmResourceDept.setYwzid(ywzid);
                rmResourceDept.setZyid(component.getId());
                rmResourceDept.setZybm(tableName);
                rmResourceDeptEntitieList.add(rmResourceDept);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntitieList);
        }
        this.updateById(component);
        return R.ok();
    }

    /**
     * 传入不带部署信息的component列表信息，返回带部署信息的列表信息
     * @param records 不带部署信息的component列表信息
     * @return 带部署信息的列表信息
     */
    private List<ComponentEntity> records2recordsWithDeploymentInfo(List<ComponentEntity> records) {
        List<Integer> componentIds = new ArrayList<Integer>();
        Map<Integer, ComponentEntity> componentWithDeploymentInfo = new HashMap<Integer, ComponentEntity>();
        for (ComponentEntity co : records) {
            componentIds.add(co.getId());
            componentWithDeploymentInfo.put(co.getId(), co);
        }
        records = new ArrayList<ComponentEntity>();
        List<DeploymentInfoEntity> deploymentInfos = componentDao.getDeploymentInfos(componentIds);
        for(DeploymentInfoEntity de : deploymentInfos) {
            ComponentEntity componentEntity = componentWithDeploymentInfo.get(Integer.parseInt(de.getZyid()));
            componentEntity.setAzfwq(StringUtil.isEmpty(componentEntity.getAzfwq())?de.getAzfwq():componentEntity.getAzfwq()+","+de.getAzfwq());
            componentEntity.setAzwz(StringUtil.isEmpty(componentEntity.getAzwz())?de.getAzwz():componentEntity.getAzwz()+","+de.getAzwz());
            componentEntity.setBbh(StringUtil.isEmpty(componentEntity.getBbh())?de.getBbh():componentEntity.getBbh()+","+de.getBbh());
        }
        for(Integer id : componentWithDeploymentInfo.keySet()) {
            records.add(componentWithDeploymentInfo.get(id));
        }
        return records;
    }
}