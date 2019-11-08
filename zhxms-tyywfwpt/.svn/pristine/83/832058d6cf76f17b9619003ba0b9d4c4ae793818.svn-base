package cn.com.bmsoft.modules.wom.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.modules.wom.entity.HandleInformationEntity;
import cn.com.bmsoft.modules.wom.entity.ProcessEntity;
import cn.com.bmsoft.modules.wom.service.HandleInformationService;
import cn.com.bmsoft.modules.wom.service.BusinessBindingService;
import cn.com.bmsoft.modules.wom.service.ProcessService;
import cn.com.bmsoft.utils.Constant;
import cn.com.bmsoft.workflowInterface.RestTemplate;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import cn.com.bmsoft.modules.wom.entity.InformationEntity;
import cn.com.bmsoft.modules.wom.service.InformationService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;


/**
 * 工单管理-工单信息表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
@RestController
@RequestMapping("wom/information")
@Api("工单管理-工单信息表 API")
public class InformationController extends AbstractController {
    @Autowired
    private InformationService informationService;
    @Autowired
    private ProcessService processService;
    @Autowired
    private HandleInformationService handleInformationService;
    @Autowired
    private BusinessBindingService modelService;
    @Autowired
    private SerialNumberService serialNumberService;



    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("wom:information:list")
    @ApiOperation(value = "获取information分页列表", notes = "information分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        Long role = getUserId();
        Long roleId = getUser().getRoleId();
        params.put("roleId",roleId);
        params.put("role",role);
        PageUtils page = informationService.queryPage(params);
        return R.ok().put("data", page);
    }
    /*获取告警&故障工单列表*/
    @GetMapping("/warnlist")
    // @RequiresPermissions("wom:information:warnlist")
    @ApiOperation(value = "获取information分页列表", notes = "information分页列表", response = R.class)
    public R warnlist(@RequestParam Map<String, Object> params){
        PageUtils page = informationService.warnList(params);
        return R.ok().put("data", page);
    }

    /**
     * 创建信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("wom:information:info")
    @ApiOperation(value = "获取information对象详情", notes = "information对象详情", response = R.class)
    public R info(@PathVariable("id") int id){
		InformationEntity information = informationService.getInformationById(id);
        return R.ok().put("data", information);
    }


    /**
     * 处理过程信息
     */
    @GetMapping("/handleProcessInfo/{id}")
    //@RequiresPermissions("wom:information:info")
    @ApiOperation(value = "获取information对象详情", notes = "information对象详情", response = R.class)
    public R handleProcessInfo(@PathVariable("id") int id){
        ProcessEntity processEntity = processService.paifaInfo(id);
        List<ProcessEntity> list = processService.handleProcessInfo(id);
		Map<String,Object> data = new HashMap<>();
		data.put("processEntity",processEntity);
		data.put("list",list);
        return R.ok().put("data", data);
    }
    /**
     * 审核信息
     */
    @GetMapping("/auditRecordsInfo/{id}")
    //@RequiresPermissions("wom:information:info")
    @ApiOperation(value = "获取information对象详情", notes = "information对象详情", response = R.class)
    public R auditRecordsInfo(@PathVariable("id") int id){
        List<ProcessEntity> processEntity = processService.auditRecordsInfo(id);
        return R.ok().put("data", processEntity);
    }

    /**
     * 办结信息
     */
    @GetMapping("/transferredRecordsInfo/{id}")
    //@RequiresPermissions("wom:information:info")
    @ApiOperation(value = "获取information对象详情", notes = "information对象详情", response = R.class)
    public R transferredRecordsInfo(@PathVariable("id") int id){
        List<ProcessEntity> processEntity = processService.transferredRecordsInfo(id);
        return R.ok().put("data", processEntity);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("wom:information:update")
    @ApiOperation(value = "修改information对象", notes = "information对象", response = R.class)
    public R update(@RequestBody InformationEntity information){
        Long user = getUser().getUserId();
        RestTemplate restTemplate = new RestTemplate();
        int sftj = information.getSftj();
        information.setXgrid(user);
        information.setXgsj(new Date());
        int ywbdid = information.getYwid();// 业务绑定id
        String modelId = modelService.getById(ywbdid).getMbid();//获取模板id
        //InformationEntity informationEntity = new InformationEntity();
        Date day=new Date();
        String slid = "";//实例id
        String wjid = "";//环节id

        if(sftj==1){
            /*=========================生成工单后，启动工作流程===================================*/
            HashMap<String, Object> map = new HashMap<String, Object>();
            HashMap<String, String> variables = new HashMap<String, String>();
            map.put("modelId", modelId);
            /*生产工单时，流程启动工作流*/
            Map<String, Object> body = restTemplate.run(map);
            Double code = (Double)body.get("code");
            if (code==500){
                return R.error("提交工单失败!");
            }
            Map<String, Object> data = (Map<String, Object>)body.get("data");
            slid = (String) data.get("id");
            wjid = (String) data.get("taskId");
            /*工单过程表插入数据*/
            ProcessEntity processEntity = new ProcessEntity();
            processEntity.setGdbh(information.getGdbh());
            processEntity.setGdhjid(wjid);
            processEntity.setGdzt("1");//1待派发
            processEntity.setCjsj(day);
            processEntity.setCjrid(user);
            processService.save(processEntity);
            information.setSlid(slid);
            information.setGdzt("1");//待派发
            information.setSftj(1);
            information.setYwryid(null);
            informationService.updateById(information);
        }else{
            information.setYwryid(user);
            informationService.updateById(information);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("wom:information:delete")
    @ApiOperation(value = "删除information对象", notes = "information对象", response = R.class)
    public R delete(@RequestBody Long[] ids){
        informationService.deleteByIds(ids);
        return R.ok();
    }
  /*  *//*
     * 普通运维人员首页工单列表
     * *//*
    @GetMapping("/getDaiChuLiList")
    //@RequiresPermissions("wom:information:getDaiChuLiList")
    @ApiOperation(value = "获取InformationEntity分页列表", notes = "工单管理-工单信息表分页列表", response = R.class)
    public R getDaiChuLiList( @RequestParam Map<String, Object> params) {
        Long user = getUser().getUserId();
        RestTemplate restTemplate = new RestTemplate();
        Collection<String> list = (Collection<String>)restTemplate.daibanList(user,Integer.valueOf(params.get("size").toString()),Integer.valueOf(params.get("page").toString()));
        PageUtils page =  informationService.getDaiChuLiList(params,list);
        return R.ok().put("data", page);
    }*/
    /*
     * 运维组长人员首页工单列表
     * */
    @GetMapping("/getDaiBanLiList")
   // @RequiresPermissions("wom:information:getDaiChuLiList")
    @ApiOperation(value = "获取InformationEntity分页列表", notes = "工单管理-工单信息表分页列表", response = R.class)
    public R getDaiBanLiList( @RequestParam Map<String, Object> params) {
        Long user = getUser().getUserId();
        params.put("ywryid",null);
        PageUtils page = informationService.home(params);
        return R.ok().put("data", page);
    }
    /*运维组长人员：获取待派发工单列表查询*/
    @GetMapping("/getDaiPaiFaPageList")
    @ApiOperation(value = "获取InformationEntity对象详情", notes = "查看待派发工单信息表", response = R.class)
    public R getDaiPaiFaPageList( @RequestParam Map<String, Object> params) {
        Long user = getUser().getUserId();
       /* RestTemplate restTemplate = new RestTemplate();
        Collection<String> list = (Collection<String>)restTemplate.daibanList(null);*/
        PageUtils page =  informationService.getDaiPaiFaPageList(params);
        return R.ok().put("data",page);
    }
    /*运维组长人员：获取待审核工单列表查询*/
    @GetMapping("/getdaiShengHePageList")
    @ApiOperation(value = "获取InformationEntity对象详情", notes = "查看待派发工单信息表", response = R.class)
    public R getdaiShengHePageList( @RequestParam Map<String, Object> params) {
        Long user = getUserId();
        PageUtils page = null;
        String actionType= (String) params.get("actionType");  //首页标识
        if (StringUtils.isNotBlank(actionType) && "indexForTodo".equals(actionType)){
            params.put("ywryid",user);
           page = informationService.home(params);
        }else{
           /*获取待审核工单列表查询*/
            page =  informationService.getdaiShengHePageList(params);
        }

        return R.ok().put("data",page);
    }
    /*普通运维人员：获取待办结发工单列表查询*/
    @GetMapping("/getDaiBanJiePageList")
    @ApiOperation(value = "获取InformationEntity对象详情", notes = "查看待派发工单信息表", response = R.class)
    public R getDaiBanJiePageList( @RequestParam Map<String, Object> params) {
        Long user = getUser().getUserId();
        params.put("ywryid",user);
       // RestTemplate restTemplate = new RestTemplate();
        //Collection<String> list = (Collection<String>)restTemplate.daibanList(user,Integer.valueOf(params.get("size").toString()),Integer.valueOf(params.get("page").toString()));
        PageUtils page =  informationService.getDaiBanJiePageList(params);
        return R.ok().put("data",page);
    }
    /*运维人员获取我申请工单列表*/
    @GetMapping("/getShenQingPageList")
    @ApiOperation(value = "获取InformationEntity对象详情", notes = "查看待派发工单信息表", response = R.class)
    public R getShenQingPageList( @RequestParam Map<String, Object> params) {
        Long user = getUser().getUserId();
        PageUtils page = informationService.getShenQingPageList(params,user);
        return R.ok().put("data",page);
    }
    /**
     * 保存
     */
    @PostMapping("/save")
     @Transactional(rollbackFor=Exception.class)
    //@RequiresPermissions("wom:information:save")
    @ApiOperation(value = "保存information对象", notes = "information对象", response = R.class)
    public R save(@RequestBody Map<String, Object> params) throws InvocationTargetException, IllegalAccessException {
        RestTemplate restTemplate = new RestTemplate();
        Long user = getUser().getUserId();
        cn.com.bmsoft.utils.R result = serialNumberService.generator("GD");
        String gdbh = (String) result.get("serialNumber");
        Map<String, Object> param = (Map<String, Object>)params.get("information");
        int ywbdid = (int) params.get("ywid");// 业务绑定id
        String modelId = modelService.getById(ywbdid).getMbid();//获取模板id
        InformationEntity informationEntity = new InformationEntity();
        BeanUtils.populate(informationEntity, param);
        Date day=new Date();
        String slid = "";//实例id
        String wjid = "";//环节id

        boolean flag = false;
        informationEntity.setGdbh(gdbh);
        informationEntity.setCjsj(day);
        informationEntity.setCjrid(user);
        int sftj = informationEntity.getSftj();//判断工单是否提交
        if (sftj==1) {
            /*保存工单信息*/
            informationService.save(informationEntity);
            /*=========================生成工单后，启动工作流程===================================*/
            HashMap<String, Object> map = new HashMap<String, Object>();
            HashMap<String, String> variables = new HashMap<String, String>();
            map.put("modelId", modelId);
            /*生产工单时，流程启动工作流*/
            Map<String, Object> body = restTemplate.run(map);
            Double code = (Double)body.get("code");
            if (code==500){
                return R.error("新增工单失败!");
            }
            Map<String, Object> data = (Map<String, Object>)body.get("data");
            slid = (String) data.get("id");
            wjid = (String) data.get("taskId");
            /*工单过程表插入数据*/
            ProcessEntity processEntity = new ProcessEntity();
            processEntity.setGdbh(gdbh);
            processEntity.setGdhjid(wjid);
            processEntity.setGdzt("1");//1待派发
            processEntity.setCjsj(day);
            processEntity.setCjrid(user);
            processService.save(processEntity);
            informationEntity.setSlid(slid);
            informationEntity.setGdzt("1");//待派发
            informationEntity.setSftj(1);
            informationService.updateById(informationEntity);
        } else {
            /*工单待提交*/
            informationEntity.setYwryid(user);
            informationEntity.setSftj(0);
            flag = informationService.save(informationEntity);
        }
        return R.ok();
    }
    /*派发工单*/
    @PostMapping("/paifa")
    @Transactional(rollbackFor=Exception.class)
    @ApiOperation(value = "获取WorkorderInformation对象详情", notes = "派发工单", response = R.class)
    public R paifa( @RequestBody Map<String,Object> map) {
        RestTemplate restTemplate = new RestTemplate();
        Long user = getUser().getUserId();
        String slid = (String) map.get("slid");
        Long bpfr = Long.valueOf(map.get("bpfr").toString()) ;
        String gdsm = (String) map.get("gdsm");
        String tzqd = (String) map.get("tzqd");
        String gdbh = (String) map.get("gdbh");
        int blsx = Integer.valueOf(map.get("blsx").toString()) ;
        String taskId = restTemplate.daibanSlid(slid);//任务id
        String wjid =null;
        Date date = new Date();
        HashMap<String, Object> claim1 = new HashMap<>();
        claim1.put("action", "claim");//completeOrclaim – 任务完成，claim – 任务认领，delegate – 任务代理(委派),resolve – 代理（委派）任务处理
        claim1.put("assignee", user);//用户号（认领用户/代理（委派）用户）
        Map<String, Object>  claimBody = restTemplate.completeOrclaim(claim1, taskId);//认领任务
        Double codeClaim = (Double) claimBody.get("code");
        /*判断是否工单已被认领*/
        if(codeClaim==500){
            return R.error("工单已被认领");
        }
        HashMap<String, Object> completeOrclaim = new HashMap<>();
        completeOrclaim.put("action", "complete");
        completeOrclaim.put("assignee", user);
        Map<String, Object>  completeBody = restTemplate.completeOrclaim(completeOrclaim, taskId);//任务完成，工作流转到下一个节点
        Double completeClaim = (Double) claimBody.get("code");
        /*判断是否工单已被认领*/
        if(completeClaim==500){
            return R.error("工单派发失败");
        }
        /*获取下一个节点的环节id*/
        wjid = restTemplate.daibanSlid(slid);
        HashMap<String, Object> claim = new HashMap<>();
        claim.put("action", "claim");
        claim.put("assignee", bpfr);
        Map<String,Object> next = restTemplate.completeOrclaim(claim, wjid);//任务完成，工作流转到下一个节点
        try{
            /*更新工单信息表,工单状态：待处理*/
            updateInformationEntity(gdbh,"2",bpfr,user,date);
            /*更新工单过程表工单状态为：已派发*/
            UpdateWrapper<ProcessEntity> wrapper = new UpdateWrapper<>();
            wrapper.set("gdzt","1");//已派发
            wrapper.eq("gdhjid",taskId);
            wrapper.set("gdjsrid",bpfr);
            wrapper.set("tzqd",tzqd);
            wrapper.set("gdsm",gdsm);
            wrapper.set("blsx",blsx);
            wrapper.set("xgrid",user);
            wrapper.set("xgsj",new Date());
            //wrapper.set("xgsjww",new Date());
            boolean upProcess = processService.update(wrapper); //更新工单过程表
            /*工单过程表插入派发记录*/
            ProcessEntity processEntity = new ProcessEntity();
            processEntity.setGdbh(gdbh);
            processEntity.setTzqd(tzqd);
            processEntity.setGdsm(gdsm);
            processEntity.setGdhjid(wjid);
            processEntity.setBlsx(blsx);
            processEntity.setGdzt("2");//工单状态：待处理;
            processEntity.setCjrid(user);
            processEntity.setCjsj(new Date());
            boolean rf= processService.save(processEntity);
        }catch (Exception e){
            restTemplate.revoke(wjid);//回撤任务
            String taskId1 = restTemplate.daibanSlid(slid);//任务id
            restTemplate.cancelClaim(taskId1);//取消认领
            /*更新工单过程表工单环节id*/
            UpdateWrapper<ProcessEntity> wrapper = new UpdateWrapper<>();
            wrapper.eq("gdhjid",taskId);
            wrapper.set("gdhjid",taskId1);
            boolean upProcess = processService.update(wrapper); //更新工单过程表
        }

        return R.ok();
    }

    /*处理完结工单*/
    @PostMapping("/chuli")
    @Transactional(rollbackFor=Exception.class)
    @ApiOperation(value = "获取WorkorderInformation对象详情", notes = "派发工单", response = R.class)
    public R chuli( @RequestBody Map<String,Object> map) {
        RestTemplate restTemplate = new RestTemplate();
        Long user = getUser().getUserId();
        String gdclzt = (String) map.get("gdclzt");
        String wtfx = (String) map.get("wtfx");
        String jjfa = (String) map.get("jjfa");
        String gdbh = (String) map.get("gdbh");
        String slid = (String) map.get("slid");//任务id
        String taskId = restTemplate.daibanSlid(slid);//任务id
        HashMap<String, Object> completeOrclaim = new HashMap<>();
        completeOrclaim.put("action", "complete");
        completeOrclaim.put("assignee", user);
        Map<String,Object> body = restTemplate.completeOrclaim(completeOrclaim, taskId);
        /*获取下一个节点的环节id*/
        String wjid = restTemplate.daibanSlid(slid);
        Date date = new Date();
        try{
            /*更新工单信息表,工单状态：待审核*/
            updateInformationEntity(gdbh,"3",null,user,date);
            /*更新过程工单状态:已处理*/
            updateProcessEntity("3",taskId,user,date);
            /*保存新过程记录,待审核*/
            saveProcessEntity(gdbh,"4",wjid,user,date);
            /*保存处理记录*/
            updateHandleInformationEntity(taskId,null,jjfa,wtfx, gdclzt,user,date);
        }catch (Exception e){
            callBack(wjid,slid,taskId);
        }


        return R.ok(body);
    }

    /*处理部分工单、转派工单*/
    @PostMapping("/transfer")
    @Transactional(rollbackFor=Exception.class)
    @ApiOperation(value = "获取WorkorderInformation对象详情", notes = "派发工单", response = R.class)
    public R transfer( @RequestBody Map<String,Object> map) {
        Long user = getUser().getUserId();
        RestTemplate restTemplate = new RestTemplate();
        String gdclzt = (String) map.get("gdclzt");
        Long zpr = Long.valueOf(map.get("zpr").toString()) ;
        String wtfx = (String) map.get("wtfx");
        String jjfa = (String) map.get("jjfa");
        String gdbh = (String) map.get("gdbh");
        String slid = (String) map.get("slid");//任务id
        String taskId = restTemplate.daibanSlid(slid);//任务id

        HashMap<String, Object> transfer = new HashMap<>();
        /*转派*/
        transfer.put( "assignee",zpr);
        restTemplate.transfer(transfer,taskId);
        Date date = new Date();
        try{
            //跟新基础表,工单状态：待处理
            updateInformationEntity(gdbh,"2",zpr,user,date);
            /*更新过程工单状态:已处理*/
            updateProcessEntity("3",taskId,user,date);
            /*保存新过程记录,待处理*/
            saveProcessEntity(gdbh,"2",taskId,user,date);
            /*保存处理记录*/
            updateHandleInformationEntity(taskId,null,jjfa,wtfx, gdclzt,user,date);
        }catch (Exception e){
            HashMap<String, Object> claim = new HashMap<>();
            claim.put("action", "claim");
            claim.put("assignee", user);
            restTemplate.completeOrclaim(claim, taskId);
        }

        return R.ok();
    }
    /*审核工单*/
    @PostMapping("/shenhe")
     @Transactional(rollbackFor=Exception.class)
    @ApiOperation(value = "获取WorkorderInformation对象详情", notes = "派发工单", response = R.class)
    public R shenhe( @RequestBody Map<String,Object> map) {
        Long user = getUser().getUserId();
        RestTemplate restTemplate = new RestTemplate();
        Long cjr ; //工单创建人
        Long clr ;//提交审核人

        String gdclzt = (String) map.get("gdclzt");
        String clsm = (String) map.get("clsm");
        String gdbh = (String) map.get("gdbh");
        String slid = (String) map.get("slid");//任务id
        String taskId = restTemplate.daibanSlid(slid);//任务id

        HashMap<String, Object> claim1 = new HashMap<>();
        claim1.put("action", "claim");
        claim1.put("assignee", user);
        Map<String, Object>  claimBody = restTemplate.completeOrclaim(claim1, taskId);//认领任务
        Double codeClaim = (Double) claimBody.get("code");
        String text = (String) claimBody.get("text");
        //判断是否工单已被认领
        if(codeClaim==500){
            return  R.error(text);
        }
        HashMap<String, Object> variables = new HashMap<>();
        List<Object> list = new ArrayList<>();

        HashMap<String, Object> completeOrclaim = new HashMap<>();
        HashMap<String, Object> claim = new HashMap<>();
        variables.put("name", "case");
        variables.put("type", "string");
        variables.put("variableScope", "LOCAL");
       //审核结果：no 审核未通过，yes 审核通过
        if (gdclzt.equals("yes")) {
            variables.put("value", "1");
            list.add(variables);
            //审核人员确认完结
            completeOrclaim.put("action", "complete");
            completeOrclaim.put("assignee", user);
            completeOrclaim.put("variables", list);
            Map<String,Object> body = restTemplate.completeOrclaim(completeOrclaim, taskId);//任务完成，工作流转到下一个节点
            /* 获取下一个节点的环节id*/
            String wjid = restTemplate.daibanSlid(slid);
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("slid",slid);
            cjr = informationService.getOne(wrapper).getCjrid();
            // 办结人员认领任务
            claim.put("action", "claim");
            claim.put("assignee", cjr); //办结人员认领
            Map<String,Object> next = restTemplate.completeOrclaim(claim, wjid);//任务完成，工作流转到下一个节点
            Double nextCode = (Double) next.get("code");
            String text1 = (String) claimBody.get("text");
            //判断是否工单已被认领,已认领则取消认领
            if(nextCode==500){
                callBack(wjid,slid,taskId);
                return  R.error(text);
            }
            Date date = new Date();
            try{
                /*更新工单状态:待办结*/
                updateInformationEntity(gdbh,"4",cjr,user,date);
                /*更新过程工单状态:已审核*/
                updateProcessEntity("5",taskId,user,date);
                /*保存新过程记录,待办结*/
                saveProcessEntity(gdbh,"6",wjid,user,date);
                /*保存审核结果*/
                updateHandleInformationEntity(taskId,clsm,null,null, gdclzt,user,date);
            }catch (Exception e){
                callBack(wjid,slid,taskId);
            }

        } else {
            variables.put("value", "0");
            list.add(variables);
            //审核人员确认完结
            completeOrclaim.put("action", "complete");
            completeOrclaim.put("assignee", "审核人员");
            completeOrclaim.put("variables", list);
            Map<String,Object> body = restTemplate.completeOrclaim(completeOrclaim, taskId);//任务完成，工作流转到下一个节点
            /*获取下一个节点的环节id*/
            String wjid = restTemplate.daibanSlid(slid);
            Date date = new Date();
            //获取上一个处理人
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("slid",slid);
            clr = informationService.getOne(wrapper).getXgrid();
            //处理人员认领任务
            claim.put("action", "claim");
            claim.put("assignee", clr); //上一个环节处理人
            Map<String,Object> chuli = restTemplate.completeOrclaim(claim, wjid);//任务完成，工作流转到下一个节点
            Double codeClaim1 = (Double) chuli.get("code");
            String text1 = (String) chuli.get("text");
            //判断是否工单已被认领
            if(codeClaim1==500){
                callBack(wjid,slid,taskId);
                return  R.error(text1);
            }

            try{
                /*更新工单状态:待处理*/
                updateInformationEntity(gdbh,"2",clr,user,date);
                /*更新过程工单状态:已审核*/
                updateProcessEntity("5",taskId,user,date);
                /*保存新过程记录,待处理*/
                saveProcessEntity(gdbh,"2",wjid,user,date);
                /*保存审核结果*/
                updateHandleInformationEntity(taskId,clsm,null,null, gdclzt,user,date);
            }catch (Exception e){
                callBack(wjid,slid,taskId);
            }

        }
        return R.ok();
    }

    /*办结工单*/
    @PostMapping("/banJie")
    @Transactional(rollbackFor=Exception.class)
    @ApiOperation(value = "获取WorkorderInformation对象详情", notes = "派发工单", response = R.class)
    public R banJie( @RequestBody Map<String,Object> map) {
        Long user = getUser().getUserId();
        RestTemplate restTemplate = new RestTemplate();
        Long cjr ; //工单创建人
        Long clr ;//提交审核人

        String gdclzt = (String) map.get("gdclzt");
        String clsm = (String) map.get("clsm");
        String gdbh = (String) map.get("gdbh");
        String slid = (String) map.get("slid");//任务id
        String taskId = restTemplate.daibanSlid(slid);//任务id

        HashMap<String, Object> variables = new HashMap<>();
        HashMap<String, Object> completeOrclaim = new HashMap<>();
        HashMap<String, Object> claim = new HashMap<>();
        List<Object> list = new ArrayList<>();
        variables.put("name", "case");
        variables.put("type", "string");
        variables.put("variableScope", "LOCAL");
       //办结结果：qrwbj 办结未通过，qrybj 办结已通过
        if (gdclzt.equals("qrybj")) {
            variables.put("value", "1");
            list.add(variables);
            Date date = new Date();
            /*更新工单状态：已办结*/
            updateInformationEntity(gdbh,"5",null,user,date);
            /*更新过程工单状态:已办结*/
            updateProcessEntity("7",taskId,user,date);
            /*保存审核结果*/
            updateHandleInformationEntity(taskId,clsm,null,null, gdclzt,user,date);
            /*b办结人员确认完结*/
            completeOrclaim.put("action", "complete");
            completeOrclaim.put("assignee", user);
            completeOrclaim.put("variables", list);
            Map<String,Object> hh= restTemplate.completeOrclaim(completeOrclaim, taskId);//任务完成，工作流转到下一个节点
            Double codeClaim = (Double) hh.get("code");
            String text = (String) hh.get("text");
            //判断是否工单已被认领
            if(codeClaim==500){
                return  R.error(text);
            }
        } else {
            variables.put("value", "0");
            list.add(variables);
            //审核人员确认完结
            completeOrclaim.put("action", "complete");
            completeOrclaim.put("assignee", user);
            completeOrclaim.put("variables", list);
            Map<String,Object> body = restTemplate.completeOrclaim(completeOrclaim, taskId);//任务完成，工作流转到下一个节点
            /*获取下一个节点的环节id*/
            String wjid = restTemplate.daibanSlid(slid);
            Date date = new Date();
            //获取上一个处理人
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("slid",slid);
            clr = informationService.getOne(wrapper).getXgrid();
            //处理人员认领任务
            claim.put("action", "claim");
            claim.put("assignee", clr); //上一个环节处理人
            Map<String,Object> chuli = restTemplate.completeOrclaim(claim, wjid);//任务完成，工作流转到下一个节点
            Double nextCode = (Double) chuli.get("code");
            String text = (String) chuli.get("text");
            //判断是否工单已被认领
            if(nextCode==500){
                callBack(wjid,slid,taskId);
                return  R.error(text);
            }
            try{
                /*更新基础工单状态：待处理*/
                updateInformationEntity(gdbh,"2",clr,user,date);
                /*更新过程工单状态:已办结*/
                updateProcessEntity("7",taskId,user,date);
                /*保存新过程记录,待处理*/
                saveProcessEntity(gdbh,"2",wjid,user,date);
                /*保存审核结果*/
                updateHandleInformationEntity(taskId,clsm,null,null, gdclzt,user,date);
            }catch (Exception e){
                callBack(wjid,slid,taskId);
            }
        }
        return R.ok();
    }
    /*更新工单信息表*/
    public boolean updateInformationEntity(String gdbh,String gdzt,Long ywryid,Long user,Date date){
        UpdateWrapper<InformationEntity> informationEntity = new UpdateWrapper<>();
        informationEntity.eq("gdbh",gdbh);
        informationEntity.set("gdzt",gdzt);
        if(ywryid!=null){
            informationEntity.set("ywryid",ywryid);
        }
        informationEntity.set("xgrid",user);
        informationEntity.set("xgsj",new Date());
        boolean rf = informationService.update(informationEntity); //更新工单信息表
        return rf;
    }
    /*更新过程工单状态*/
    public boolean updateProcessEntity(String gdzt,String taskId,Long user,Date date){
        UpdateWrapper<ProcessEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("gdzt",gdzt);
        updateWrapper.eq("gdhjid",taskId);
        updateWrapper.set("xgsj",new Date());
        updateWrapper.set("xgrid",user);
        processService.update(updateWrapper);
        boolean rf = processService.update(updateWrapper);;
        return rf;
    }
    /*新增过程工单记录*/
    public boolean saveProcessEntity(String gdbh,String gdzt,String wjid,Long user,Date date){
        ProcessEntity processEntity = new ProcessEntity();
        processEntity.setGdbh(gdbh);
        processEntity.setGdhjid(wjid);
        processEntity.setCjsj(date);
        processEntity.setGdzt(gdzt);
        processEntity.setCjrid(user);
        boolean rf = processService.save(processEntity);//新增过程记录
        return rf;
    }
    /*保存处理详情*/
    public boolean updateHandleInformationEntity(String taskId,String clsm,String jjfa,String wtfx, String gdclzt,Long user,Date date){
        HandleInformationEntity handleInformationEntity = new HandleInformationEntity();
        handleInformationEntity.setGdhjid(taskId);
        handleInformationEntity.setClsm(clsm);
        handleInformationEntity.setJjfa(jjfa);
        handleInformationEntity.setWtfx(wtfx);
        handleInformationEntity.setCjrid(user);
        handleInformationEntity.setGdclzt(gdclzt);
        handleInformationEntity.setCjsj(new Date());
        boolean rf = handleInformationService.save(handleInformationEntity);
        return rf;
    }
    /*撤回信息*/
    public void callBack(String wjid,String slid,String taskId){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.revoke(wjid);//回撤任务
        String taskId1 = restTemplate.daibanSlid(slid);//任务id
        /*更新工单过程表工单环节id*/
        UpdateWrapper<ProcessEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("gdhjid",taskId);
        wrapper.set("gdhjid",taskId1);
        boolean upProcess = processService.update(wrapper); //更新工单过程表
    }


}
