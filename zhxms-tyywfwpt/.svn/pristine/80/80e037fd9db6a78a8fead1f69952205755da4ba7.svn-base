package cn.com.bmsoft.modules.bsm.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.modules.bsm.entity.SystemEntity;
import cn.com.bmsoft.modules.bsm.entity.UpgradeEntity;
import cn.com.bmsoft.modules.bsm.service.UpgradeService;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import cn.com.bmsoft.modules.bsm.service.SystemService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 业务服务管理-应用系统表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("bsm/system")
@Api("业务服务管理-应用系统表 API")
public class SystemController extends AbstractController {

    @Autowired
    private SystemService systemService;
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
   // @RequiresPermissions("bsm:system:list")
    @ApiOperation(value = "获取system分页列表", notes = "system分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = systemService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/select")
    // @RequiresPermissions("bsm:system:list")
    @ApiOperation(value = "获取system分页列表", notes = "system分页列表", response = R.class)
    public R select(){
        QueryWrapper<SystemEntity> wrapper = new QueryWrapper<>();
        wrapper.select("id","ywfwmc");
        wrapper.isNull("delete_flag");
        List<SystemEntity> data = systemService.list(wrapper);
        return R.ok().put("data", data);
    }
    /**
     * 信息
     */
    @GetMapping("/info/{id}")
   // @RequiresPermissions("bsm:system:info")
    @ApiOperation(value = "获取system对象详情", notes = "system对象详情", response = R.class)
    public R info(@PathVariable("id") int id){
        SystemEntity systemEntity = systemService.getSystemById(id);
        /*部署信息*/
        List<DeploymentInfoEntity> list = deploymentInfoService.systemlList(id);
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
        wrapper.eq("ywbm","bsm_system");
        List<UpgradeEntity> upgrade = upgradeService.list(wrapper);
        Map<String, Object> data = new HashMap<>();
        SystemEntity ywzmc = ywzmc(systemEntity);
        systemEntity.setYwzmc(ywzmc.getYwzmc());
        systemEntity.setYwzid(ywzmc.getYwzid());
        data.put("systemEntity",systemEntity);
        data.put("list",list);
        data.put("upgrade",upgrade);
        return R.ok().put("data", data);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
   // @RequiresPermissions("bsm:system:save")
    @ApiOperation(value = "保存system对象", notes = "system对象", response = R.class)
    @Transactional
    public R save(@RequestBody Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
         Long user = getUser().getUserId();
        /*基础信息*/
        Map<String, Object> params1 = (Map<String, Object>)params.get("system");
        SystemEntity system = new SystemEntity();
        BeanUtils.populate(system, params1);
        String ywfwbm = system.getYwfwbm();


        String ywfwbm1 = systemService.ywfwbmYz(ywfwbm);//验证ywfwbm唯一性
        if(StringUtils.isNotBlank(ywfwbm1)) {
            return R.error("业务服务编码已存在");
        }
        Date date = new Date();
        system.setXgsj(date);
        system.setXgrid(user);
        system.setCjrid(user);
        system.setCjsj(date);
        systemService.save(system);
        QueryWrapper<SystemEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("ywfwbm",ywfwbm);
        int id = systemService.getOne(wrapper).getId();
        /*服务器信息*/
        ArrayList<DeploymentInfoEntity>  list = ( ArrayList<DeploymentInfoEntity>)params.get("list");
        Collection<DeploymentInfoEntity>  list1 = new  ArrayList<DeploymentInfoEntity>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> params2  = (Map<String, Object>) list.get(i);
            DeploymentInfoEntity installEntity = new DeploymentInfoEntity();
            BeanUtils.populate(installEntity, params2);
            installEntity.setZyid(id+"");
            installEntity.setZybm("bsm_system");
            installEntity.setCjrid(Math.toIntExact(user));
            installEntity.setCjsj(date);
            list1.add(installEntity);
        }
        /*升级信息*/
        Map<String, Object> upgrade = (Map<String, Object>)params.get("upgrade");
        UpgradeEntity upgradeEntity = new UpgradeEntity();
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
        upgradeEntity.setYwfwbm(ywfwbm);
        upgradeEntity.setFjid(fjid);
        upgradeEntity.setFjmc(fjmc);
        upgradeEntity.setSjrid(sjr);
        upgradeEntity.setSjsj(date1);
        upgradeEntity.setYwbm("bsm_system");
        upgradeEntity.setYwid(Long.valueOf(id));
        /*业务组信息*/
        List<Integer> ywzids = system.getYwzid();
        ywzList( ywzids,id);
        deploymentInfoService.saveBatch(list1);
        upgradeService.save(upgradeEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
   // @RequiresPermissions("bsm:system:update")
    @ApiOperation(value = "修改system对象", notes = "system对象", response = R.class)
    @Transactional
    public R update(@RequestBody Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
         Long user = getUser().getUserId();
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        Map<String, Object> params1 = (Map<String, Object>)params.get("system");
        SystemEntity system = new SystemEntity();
        BeanUtils.copyProperties(system,params1);
        BeanUtils.populate(system, params1);
        String ywfwbm = system.getYwfwbm();
        int id = system.getId();
        String ywfwbm2 = systemService.getOne(new QueryWrapper<SystemEntity>().eq("id",id)).getYwfwbm();
        if(!ywfwbm2.equals(ywfwbm)){
            String ywfwbm1 = systemService.ywfwbmYz(ywfwbm);//验证ywfwbm唯一性
            if(StringUtils.isNotBlank(ywfwbm1)) {
                return R.error("业务服务编码已存在");
            }
        }
        Date date = new Date();
        system.setXgsj(date);
        system.setXgrid(user);
        systemService.updateById(system);
        ArrayList<DeploymentInfoEntity>  list = ( ArrayList<DeploymentInfoEntity>)params.get("list");
        Collection<DeploymentInfoEntity>  list1 = new  ArrayList<DeploymentInfoEntity>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> params2  = (Map<String, Object>) list.get(i);
            DeploymentInfoEntity installEntity = new DeploymentInfoEntity();
            BeanUtils.copyProperties(installEntity,params2);
            BeanUtils.populate(installEntity, params2);
            installEntity.setZyid(id+"");
            installEntity.setZybm("bsm_system");
            installEntity.setCjrid(Math.toIntExact(user));
            installEntity.setCjsj(date);
            installEntity.setXgrid(Math.toIntExact(user));
            installEntity.setXgsj(date);
            list1.add(installEntity);
        }
        //业务组信息
        updateYwzList(system);
        deploymentInfoService.saveOrUpdateBatch(list1);
        upgradeService.update(new UpdateWrapper<UpgradeEntity>().set("ywfwbm",ywfwbm).eq("ywid",id));
        return R.ok();
    }
    /**
     * 升级
     */
    @PostMapping("/upgrade")
    //@RequiresPermissions("bsm:system:update")
    @ApiOperation(value = "修改system对象", notes = "system对象", response = R.class)
    @Transactional
    public R upgrade(@RequestBody  Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
         Long user = getUser().getUserId();
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
       /*基础信息*/
        Map<String, Object> params1 = (Map<String, Object>)params.get("system");
        SystemEntity system = new SystemEntity();
        BeanUtils.copyProperties(system,params1);
        BeanUtils.populate(system, params1);
        String ywfwbm = system.getYwfwbm();
        int id = system.getId();
        String ywfwbm2 = systemService.getOne(new QueryWrapper<SystemEntity>().eq("id",id)).getYwfwbm();
        if(!ywfwbm2.equals(ywfwbm)){
            String ywfwbm1 = systemService.ywfwbmYz(ywfwbm);//验证ywfwbm唯一性
            if(StringUtils.isNotBlank(ywfwbm1)) {
                return R.error("业务服务编码已存在");
            }
        }
        Date date = new Date();
        system.setXgsj(date);
        system.setXgrid(user);
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
        upgradeEntity.setYwfwbm(ywfwbm);
        upgradeEntity.setSjrid(sjr);
        upgradeEntity.setSjsj(date1);
        upgradeEntity.setYwfwbm(ywfwbm);
        upgradeEntity.setYwbm("bsm_system");
        upgradeEntity.setYwid(Long.valueOf(id));
        /*服务器信息*/
        ArrayList<DeploymentInfoEntity>  list = ( ArrayList<DeploymentInfoEntity>)params.get("list");
        Collection<DeploymentInfoEntity>  list1 = new  ArrayList<DeploymentInfoEntity>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> params2  = (Map<String, Object>) list.get(i);
            DeploymentInfoEntity installEntity = new DeploymentInfoEntity();
            BeanUtils.copyProperties(installEntity,params2);
            BeanUtils.populate(installEntity, params2);
            installEntity.setZyid(id+"");
            installEntity.setZybm("bsm_system");
            installEntity.setXgrid(Math.toIntExact(user));
            installEntity.setXgsj(date);
            list1.add(installEntity);
        }
        //业务组信息
        updateYwzList(system);
        systemService.updateById(system);
        deploymentInfoService.saveOrUpdateBatch(list1);
        upgradeService.save(upgradeEntity);
        return R.ok();
    }
    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("bsm:system:delete")
    @ApiOperation(value = "删除system对象", notes = "system对象", response = R.class)
    public R delete(@RequestBody Long[] ids){
		systemService.updateBatchByIds(ids);
        return R.ok();
    }
    @PostMapping("/updateStatus")
    //@RequiresPermissions("bsm:system:update")
    @ApiOperation(value = "修改system对象", notes = "system对象", response = R.class)
    public R updateStatus(@RequestBody SystemEntity systemEntity)  {
        systemService.updateById(systemEntity);
        return R.ok();
    }
    /*业务组信息*/
    public void ywzList(List<Integer>  ywzids,int id){
        if(!ywzids.isEmpty()) {
            List<RmResourceDeptEntity> rmResourceDeptEntities = new ArrayList<>();
            for (Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDeptEntity = new RmResourceDeptEntity();
                rmResourceDeptEntity.setZyid(id);
                rmResourceDeptEntity.setYwzid(ywzid);
                rmResourceDeptEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.BSM_SYSTEM_NAME));
                rmResourceDeptEntities.add(rmResourceDeptEntity);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntities);
        }
    }
    public void updateYwzList(SystemEntity systemEntity){
        String tableName = RmParams.RM_TABLE_NAME.get(RmParams.BSM_SYSTEM_NAME);
        List<Integer> ywzids = systemEntity.getYwzid();
        List<RmResourceDeptEntity> rmResourceDeptEntitieList = new ArrayList<>();
        rmResourceDeptService.removeResourceDepts(systemEntity.getId()+"", tableName);
        if(!ywzids.isEmpty()){
            for(Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDept = new RmResourceDeptEntity();
                rmResourceDept.setYwzid(ywzid);
                rmResourceDept.setZyid(systemEntity.getId());
                rmResourceDept.setZybm(tableName);
                rmResourceDeptEntitieList.add(rmResourceDept);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntitieList);
        }
    }

    public SystemEntity ywzmc(SystemEntity serviceEntity){
        List<Map<String, Object>> resourceDeptNames = rmResourceDeptService.getResourceDeptNames(serviceEntity.getId() + "", RmParams.RM_TABLE_NAME.get(RmParams.BSM_SYSTEM_NAME));
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
