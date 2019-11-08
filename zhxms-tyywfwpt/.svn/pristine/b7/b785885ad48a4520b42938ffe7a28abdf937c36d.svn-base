package cn.com.bmsoft.modules.bsm.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.com.bmsoft.modules.bsm.entity.UpgradeEntity;
import cn.com.bmsoft.modules.bsm.service.UpgradeService;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;
import cn.com.bmsoft.modules.rm.service.DeploymentInfoService;
import cn.com.bmsoft.modules.rm.service.RmResourceDeptService;
import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.bsm.service.ServiceService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 业务服务管理-应用服务表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("bsm/service")
@Api("业务服务管理-应用服务表 API")
public class ServiceController extends AbstractController {
    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UpgradeService upgradeService;
    @Autowired
    private DeploymentInfoService deploymentInfoService;
    @Autowired
    private RmResourceDeptService rmResourceDeptService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("bsm:service:list")
    @ApiOperation(value = "获取service分页列表", notes = "service分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = serviceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("bsm:service:info")
    @ApiOperation(value = "获取service对象详情", notes = "service对象详情", response = R.class)
    public R info(@PathVariable("id") int id){
        /*基础信息*/
		ServiceEntity service = serviceService.getServiceById(id);
        /*部署信息*/
        List<DeploymentInfoEntity> list = deploymentInfoService.serverList(id);
        /*升级信息*/
        QueryWrapper<UpgradeEntity> wrapper = new QueryWrapper<>();
        wrapper.select(
                "id",
                "fjid",
                "sjsj",
                "ywfwbm",
                "sjrid",
                "(select t.name  from sys_user t where t.user_id = bsm_upgrade.sjrid) sjrmc",
                "(SELECT t.original_file_name FROM c_attachment t WHERE t.id = bsm_upgrade.fjid ) fjmc "
        );
        wrapper.eq("ywid",id);
        wrapper.eq("ywbm","bsm_service");
        //wrapper.apply("")
        List<UpgradeEntity> upgrade = upgradeService.list(wrapper);
        Map<String, Object> data = new HashMap<>();
        ServiceEntity ywzmc = ywzmc(service);
        service.setYwzmc(ywzmc.getYwzmc());
        service.setYwzid(ywzmc.getYwzid());
        data.put("service",service);
        data.put("list",list);
        data.put("upgrade",upgrade);
        return R.ok().put("data", data);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("bsm:service:save")
    @ApiOperation(value = "保存service对象", notes = "service对象", response = R.class)
    @Transactional
    public R save(@RequestBody Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
        Long user = getUser().getUserId();
        boolean rf = false;
        /*基础信息*/
        Map<String, Object> params1 = (Map<String, Object>)params.get("service");
        ServiceEntity service = new ServiceEntity();
        BeanUtils.populate(service, params1);
        String ywfwbm = service.getYwfwbm();
        List<Integer>  ywzids = service.getYwzid();
        String ywfwbm1 = serviceService.ywfwbmYz(ywfwbm);//验证ywfwbm唯一性
        if(StringUtils.isNotBlank(ywfwbm1)) {
            return R.error("业务服务编码已存在");
        }

        Date date = new Date();
        service.setXgsj(date);
        service.setXgrid(user);
        serviceService.save(service);

        QueryWrapper<ServiceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("ywfwbm",ywfwbm);
        Long id =serviceService.getOne(wrapper).getId();
        //业务组信息
        ywzList(ywzids,id);
            /*升级信息*/
            Map<String, Object> upgrade = (Map<String, Object>) params.get("upgrade");
            String fjid = (String) upgrade.get("fjid");
            String fjmc = (String) upgrade.get("fjmc");
            Long sjr = Long.valueOf( upgrade.get("sjrid").toString()) ;
            String sjsj = (String) upgrade.get("sjsj");
            UpgradeEntity upgradeEntity = new UpgradeEntity();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            try {
                date1 = sdf.parse(sjsj);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            upgradeEntity.setFjid(fjid);
            upgradeEntity.setFjmc(fjmc);
            upgradeEntity.setYwfwbm(ywfwbm);
            upgradeEntity.setYwbm("bsm_service");
            upgradeEntity.setYwid(id);
            upgradeEntity.setSjrid(sjr);
            upgradeEntity.setSjsj(date1);

            /*服务器信息*/
            ArrayList<DeploymentInfoEntity> list = (ArrayList<DeploymentInfoEntity>) params.get("list");
            Collection<DeploymentInfoEntity> list1 = new ArrayList<DeploymentInfoEntity>();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> params2 = (Map<String, Object>) list.get(i);
                DeploymentInfoEntity deploymentInfoEntity = new DeploymentInfoEntity();
                //BeanUtils.copyProperties(installEntity,params2);
                BeanUtils.populate(deploymentInfoEntity, params2);
                deploymentInfoEntity.setZyid(id+"");
                deploymentInfoEntity.setZybm("bsm_service");
                deploymentInfoEntity.setCjrid(Math.toIntExact(user));
                deploymentInfoEntity.setCjsj(date);
                list1.add(deploymentInfoEntity);
            }

            deploymentInfoService.saveBatch(list1);
            upgradeService.save(upgradeEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("bsm:service:update")
    @ApiOperation(value = "修改service对象", notes = "service对象", response = R.class)
    @Transactional
    public R update(@RequestBody Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
        Long user = getUser().getUserId();
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        /*通过id 修改服务管理信息*/
        Map<String, Object> params1 = (Map<String, Object>)params.get("service");
        ArrayList<DeploymentInfoEntity>  list = ( ArrayList<DeploymentInfoEntity>)params.get("list");
        ServiceEntity service = new ServiceEntity();
        BeanUtils.copyProperties(service,params1);
        BeanUtils.populate(service, params1);
        String ywfwbm = service.getYwfwbm();
        List<Integer>  ywzids = service.getYwzid();
        Long id = service.getId();
        Date date = new Date();
        service.setXgsj(date);
        service.setXgrid(user);
        String ywfwbm2 = serviceService.getOne(new QueryWrapper<ServiceEntity>().eq("id",id)).getYwfwbm();
        if(!ywfwbm2.equals(ywfwbm)){
            String ywfwbm1 = serviceService.ywfwbmYz(ywfwbm);//验证ywfwbm唯一性
            if(StringUtils.isNotBlank(ywfwbm1)) {
                return R.error("业务服务编码已存在");
            }
        }
        /*通过id修改对应服务器信息*/

        Collection<DeploymentInfoEntity>  list1 = new  ArrayList<DeploymentInfoEntity>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> params2  = (Map<String, Object>) list.get(i);
            DeploymentInfoEntity installEntity = new DeploymentInfoEntity();
            BeanUtils.copyProperties(installEntity,params2);
            BeanUtils.populate(installEntity, params2);
            installEntity.setZyid(id+"");
            installEntity.setZybm("bsm_service");
            installEntity.setXgrid(Math.toIntExact(user));
            installEntity.setXgsj(date);
            list1.add(installEntity);
        }
        //业务组信息
        updateYwzList(service);
        serviceService.updateById(service);
        deploymentInfoService.saveOrUpdateBatch(list1);
        upgradeService.update(new UpdateWrapper<UpgradeEntity>().set("ywfwbm",ywfwbm).eq("ywid",id));
        return R.ok();
    }
    /**
     * 升级
     */
    @PostMapping("/upgrade")
    //@RequiresPermissions("bsm:service:update")
    @ApiOperation(value = "修改service对象", notes = "service对象", response = R.class)
    @Transactional
    public R upgrade(@RequestBody  Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
        Long user = getUser().getUserId();
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        Date date = new Date();
        /*基础信息*/
        Map<String, Object> params1 = (Map<String, Object>)params.get("service");
        ServiceEntity service = new ServiceEntity();
        BeanUtils.copyProperties(service,params1);
        BeanUtils.populate(service, params1);
        String ywfwbm = service.getYwfwbm();
        List<Integer>  ywzids = service.getYwzid();
        Long id = service.getId();
        String ywfwbm2 = serviceService.getOne(new QueryWrapper<ServiceEntity>().eq("id",id)).getYwfwbm();
        if(!ywfwbm2.equals(ywfwbm)){
            String ywfwbm1 = serviceService.ywfwbmYz(ywfwbm);//验证ywfwbm唯一性
            if(StringUtils.isNotBlank(ywfwbm1)) {
                return R.error("业务服务编码已存在");
            }
        }
        service.setXgsj(date);
        service.setXgrid(user);
        /*升级信息*/
        Map<String, Object> upgrade = (Map<String, Object>)params.get("upgrade");
        String fjid = (String)upgrade.get("fjid");
        String fjmc = (String)upgrade.get("fjmc");
        Long sjr = Long.valueOf( upgrade.get("sjrid").toString()) ;
        String sjsj = (String)upgrade.get("sjsj");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date1= null;
        try {
            date1 = sdf.parse(sjsj);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UpgradeEntity upgradeEntity = new UpgradeEntity();
        upgradeEntity.setFjid(fjid);
        upgradeEntity.setFjmc(fjmc);
        upgradeEntity.setSjrid(sjr);
        upgradeEntity.setSjsj(date1);
        upgradeEntity.setYwfwbm(ywfwbm);
        upgradeEntity.setYwbm("bsm_service");
        upgradeEntity.setYwid(id);
        /*服务器信息*/
        ArrayList<DeploymentInfoEntity>  list = ( ArrayList<DeploymentInfoEntity>)params.get("list");
        Collection<DeploymentInfoEntity>  list1 = new  ArrayList<DeploymentInfoEntity>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> params2  = (Map<String, Object>) list.get(i);
            DeploymentInfoEntity installEntity = new DeploymentInfoEntity();
            BeanUtils.copyProperties(installEntity,params2);
            BeanUtils.populate(installEntity, params2);
            installEntity.setZyid(id+"");
            installEntity.setZybm("bsm_service");
            installEntity.setXgrid(Math.toIntExact(user));
            installEntity.setXgsj(date);
            list1.add(installEntity);
        }
        //业务组信息
        updateYwzList(service);
        serviceService.updateById(service);
        deploymentInfoService.saveOrUpdateBatch(list1);
        upgradeService.save(upgradeEntity);
        return R.ok();
    }
    /**
     * 删除
     */
    @PostMapping("/delete")
   // @RequiresPermissions("bsm:service:delete")
    @ApiOperation(value = "删除service对象", notes = "service对象", response = R.class)
    public R delete(@RequestBody Long[] ids){
       /* Long user = getUser().getUserId();*/
		serviceService.updateBatchByIds(ids);
        return R.ok();
    }
    /**
     * 信息
     */
    @GetMapping("/ywfwlist/{id}")
    //@RequiresPermissions("bsm:service:info")
    @ApiOperation(value = "获取service对象详情", notes = "service对象详情", response = R.class)
    public R ywfwlist(@PathVariable("id") int id){
        /*基础信息*/
        List<ServiceEntity> list = serviceService.ywfwlist(id);
        return R.ok().put("data", list);
    }

    @PostMapping("/updateStatus")
    //@RequiresPermissions("bsm:service:update")
    @ApiOperation(value = "修改service对象", notes = "service对象", response = R.class)
    public R updateStatus(@RequestBody ServiceEntity serviceEntity) throws InvocationTargetException, IllegalAccessException {
        serviceService.updateById(serviceEntity);
        return R.ok();
    }

    /*业务组信息*/
    public void ywzList(List<Integer>  ywzids,Long id){
        if(!ywzids.isEmpty()) {
            List<RmResourceDeptEntity> rmResourceDeptEntities = new ArrayList<>();
            for (Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDeptEntity = new RmResourceDeptEntity();
                rmResourceDeptEntity.setZyid(Math.toIntExact(id));
                rmResourceDeptEntity.setYwzid(ywzid);
                rmResourceDeptEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.BSM_SERVICE_NAME));
                rmResourceDeptEntities.add(rmResourceDeptEntity);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntities);
        }
    }
    public void updateYwzList(ServiceEntity serviceEntity){
        String tableName = RmParams.RM_TABLE_NAME.get(RmParams.BSM_SERVICE_NAME);
        List<Integer> ywzids = serviceEntity.getYwzid();
        List<RmResourceDeptEntity> rmResourceDeptEntitieList = new ArrayList<>();
        rmResourceDeptService.removeResourceDepts(serviceEntity.getId()+"", tableName);
        if(!ywzids.isEmpty()){
            for(Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDept = new RmResourceDeptEntity();
                rmResourceDept.setYwzid(ywzid);
                Integer id = Math.toIntExact(serviceEntity.getId());
                rmResourceDept.setZyid(id);
                rmResourceDept.setZybm(tableName);
                rmResourceDeptEntitieList.add(rmResourceDept);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntitieList);
        }
    }

    public ServiceEntity ywzmc(ServiceEntity serviceEntity){
        List<Map<String, Object>> resourceDeptNames = rmResourceDeptService.getResourceDeptNames(serviceEntity.getId() + "", RmParams.RM_TABLE_NAME.get(RmParams.BSM_SERVICE_NAME));
        List<Integer> ywzids = new ArrayList<>();
        String ywzmc = "";
        for (Map<String, Object> map : resourceDeptNames) {
            ywzids.add(Integer.parseInt(map.get("DEPT_ID")+""));
            ywzmc = (StringUtil.isEmpty(ywzmc)?"":(ywzmc+",")) + (StringUtil.isEmpty(map.get("DEPT_NAME")+"")?"":map.get("DEPT_NAME"));
        }
        serviceEntity.setYwzid(ywzids);
        serviceEntity.setYwzmc(ywzmc);
        return serviceEntity;
    }
}
