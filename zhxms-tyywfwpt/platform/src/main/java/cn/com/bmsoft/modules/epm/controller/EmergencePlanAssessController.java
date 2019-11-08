package cn.com.bmsoft.modules.epm.controller;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanAssessEntity;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntityExtend;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanTaskEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanAssessService;
import cn.com.bmsoft.modules.epm.service.EmergencePlanService;
import cn.com.bmsoft.modules.epm.service.EmergencePlanTaskService;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 应急预案评审表
 *
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("epm/emergencePlanAssess")
@Api("应急预案评审表 API")
public class EmergencePlanAssessController extends AbstractController {
    @Autowired
    private EmergencePlanAssessService emergencePlanAssessService;

    @Autowired
    private EmergencePlanService emergencePlanService;

    @Autowired
    private EmergencePlanTaskService emergencePlanTaskService;

    @Autowired
    private SerialNumberService serialNumberService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("epm:emergenceplanassess:list")
    @ApiOperation(value = "获取emergenceplanassess分页列表", notes = "emergenceplanassess分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = emergencePlanAssessService.queryPage(params);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("epm:emergenceplanassess:info")
    @ApiOperation(value = "获取emergenceplanassess对象详情", notes = "emergenceplanassess对象详情", response = R.class)
    public R info(@PathVariable("id") String id) {
        EmergencePlanAssessEntity emergencePlanAssess = emergencePlanAssessService.getById(id);
        return R.ok().put("data", emergencePlanAssess);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("epm:emergenceplanassess:save")
    @ApiOperation(value = "保存emergenceplanassess对象", notes = "emergenceplanassess对象", response = R.class)
    public R save(@RequestBody EmergencePlanAssessEntity emergencePlanAssess) {
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal(); //获取当前登录的用户
        String taskId = "";
        String epid = emergencePlanAssess.getEpid(); //对应派审任务id
        EmergencePlanTaskEntity emergencePlanTaskEntity = emergencePlanTaskService.getEmergencePlanTask(epid, String.valueOf(sysUserEntity.getUserId()), DictionaryConstant.EMERGENCE_PLAN_Task_Assess);
        if (emergencePlanTaskEntity != null) {
            taskId = emergencePlanTaskEntity.getId();
        }
        emergencePlanAssess.setRwid(taskId); //任务id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //日期格式
        String currentDate = sdf.format(new Date());
        R result=serialNumberService.generator("YAPG");
        String yapgbh= (String) result.get("serialNumber");
        emergencePlanAssess.setYapgbh(yapgbh); //预案评估编号
        String nowTime = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        emergencePlanAssess.setCreateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
        emergencePlanAssess.setUpdateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
        emergencePlanAssess.setStatus(DictionaryConstant.STATUS_VALID); //有效
        emergencePlanAssess.setClzt(DictionaryConstant.EMERGENCE_PLAN_REVIEW_STATE_UNHANDLE); //处理状态为未处理
        emergencePlanAssess.setYwryid(String.valueOf(sysUserEntity.getUserId()));  //运维人员id为当前登录用户的id
        emergencePlanAssess.setCreateUser(String.valueOf(sysUserEntity.getUserId()));
        emergencePlanAssess.setUpdateUser(String.valueOf(sysUserEntity.getUserId()));
        boolean flag = emergencePlanAssessService.save(emergencePlanAssess);
        if (flag == true) {
            emergencePlanTaskEntity.setStatus(DictionaryConstant.STATUS_INVALID);
            emergencePlanTaskService.updateById(emergencePlanTaskEntity);  //更新对应的任务表的状态未0（从而标记该用户已经完成了评估工作）

            //判断一下该应急预案是否全部完成评估
            Map<String, Object> queryParam = new HashMap();
            queryParam.put("yjyaid", epid); //应急预案id
            queryParam.put("status", DictionaryConstant.STATUS_VALID);
            queryParam.put("rwmc", DictionaryConstant.EMERGENCE_PLAN_Task_Assess);
            List<EmergencePlanTaskEntity> taskEntityList = (List<EmergencePlanTaskEntity>) emergencePlanTaskService.listByMap(queryParam);
            if (taskEntityList.size() == 0) {
                EmergencePlanEntityExtend emergencePlan1 = new EmergencePlanEntityExtend();
                emergencePlan1.setSfypg(DictionaryConstant.EMERGENCE_PLAN_STATE_ASSESS); //是否已评估（已评估）
                emergencePlan1.setId(epid);
                emergencePlanService.updateById(emergencePlan1);   //改成已评估
            }
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    // @RequiresPermissions("epm:emergenceplanassess:update")
    @ApiOperation(value = "修改emergenceplanassess对象", notes = "emergenceplanassess对象", response = R.class)
    public R update(@RequestBody EmergencePlanAssessEntity emergencePlanAssess) {
        emergencePlanAssessService.updateById(emergencePlanAssess);
        return R.ok();
    }


    /**
     *
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除EmergencePlanReview对象", notes = "删除应急预案评审表", response = R.class)
    public R deleteEmergencePlanReview(@RequestParam String id) throws Exception {
        int result = emergencePlanAssessService.deleteByIdLogical(id);
        return R.ok();
    }


    /**
     *
     */
    @PostMapping("/deleteBatch")
    @ApiOperation(value = "删除EmergencePlan对象", notes = "删除应急预案表", response = R.class)
    public R deleteEmergencePlanReviewBatch(@RequestParam(value = "ids") String id) throws Exception {
        int result = emergencePlanAssessService.deleteByIdLogicalBatch(id);
        return R.ok();
    }




    /**
     * 应急预案查看评估记录分页列表
     */
    @GetMapping("/emergencePlanAssessQueryList")
    public R emergencePlanAssessQueryList(@RequestParam Map<String, Object> params) {
        params.put("status", DictionaryConstant.STATUS_VALID);//筛选有效的（已删除的不显示）
//        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal(); //获取当前登录的用户
//        params.put("ywryid", sysUserEntity.getUserId());
        PageUtils result = emergencePlanAssessService.emergencePlanAssessQueryList(params);
        String epid= (String) params.get("epid");
        Map<String,Object> taskMapParams=new HashMap<>();
        taskMapParams.put("yjyaid",epid);
        taskMapParams.put("rwmc","assess");
        EmergencePlanTaskEntity emergencePlanTaskEntity=null;
        List<EmergencePlanTaskEntity> emergencePlanTaskEntityList= emergencePlanTaskService.getEmergencePlanTaskList(taskMapParams);
        if (emergencePlanTaskEntityList.size()>0){
            emergencePlanTaskEntity=emergencePlanTaskEntityList.get(0);
        }

        return R.ok().put("page", result).put("emergencePlanAssessEntity",emergencePlanTaskEntity);

    }




    /**
     * 应急预案待评估分页列表
     */
    @GetMapping("/unAssessList")
    public R unAssessQueryList(@RequestParam Map<String, Object> params) {
        PageUtils result = null;
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal(); //获取当前登录的用户
        //获取用户所属的角色列（除了被派发评审的运维人员之外，系统管理员和运维组长可以查看应急该应急预案）
//        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(getUserId());
        Long roleId = getUser().getRoleId();
        params.put("yazt", DictionaryConstant.EMERGENCE_PLAN_STATE_ESTIMATE); //预案状态（已发布)
        params.put("status", DictionaryConstant.STATUS_VALID);//筛选有效的（已删除的不显示）
        if ( Constant.ADMIN_ROLE !=roleId && Constant.OPERATIONS_GROUP_LEADER_ROLE !=roleId) {  //管理员和运维组长
            params.put("taskStatus", DictionaryConstant.STATUS_VALID); //任务状态未评估1
            params.put("rwmc", DictionaryConstant.EMERGENCE_PLAN_Task_Assess); //任务表是评审类型的
            params.put("updateTaskUser", String.valueOf(sysUserEntity.getUserId())); //只对被派发评估的运维人员显示
            params.put("sfypg", DictionaryConstant.EMERGENCE_PLAN_STATE_UNASSESS);//是否已评估（待评估)
            result = emergencePlanService.unReviewQueryList(params);
            //判断一下运维组长和管理员是否是被派发人员，如果不是前端则没有则没有评审操作按钮
            List<EmergencePlanEntityExtend> list= (List<EmergencePlanEntityExtend>) result.getList();
            if(list.size()>0) {
                for (int i = 0, len = list.size(); i < len; i++) {
                    EmergencePlanEntityExtend emergencePlanEntityExtend = list.get(i);
                        emergencePlanEntityExtend.setOperate(true);
                        emergencePlanEntityExtend.setHasOperate(false);

                }
            }

        } else {
            result = emergencePlanService.emergencePlanQueryList(params);
            //判断一下运维组长和管理员是否是被派发人员，如果不是前端则没有评审操作按钮
            List<EmergencePlanEntityExtend> list= (List<EmergencePlanEntityExtend>) result.getList();
            if(list.size()>0) {
                for (int i = 0, len = list.size(); i < len; i++) {
                    EmergencePlanEntityExtend emergencePlanEntityExtend = list.get(i);
                    String yjyaid=emergencePlanEntityExtend.getId(); //应急预案id
                    Map<String,Object> queryParams=new HashMap<>();
                    queryParams.put("yjyaid",yjyaid);
                    queryParams.put("update_user",getUserId());
//                    queryParams.put("status",0);//已评估
                    queryParams.put("rwmc","assess");
                    //判断是否完成了评审
                    List<EmergencePlanTaskEntity> emergencePlanTaskEntityList= (List<EmergencePlanTaskEntity>) emergencePlanTaskService.listByMap(queryParams);
                    if (emergencePlanTaskEntityList.size()>0){ //当前管理员或者运维组长是需要评估的用户
                        emergencePlanEntityExtend.setOperate(true); //具有评估操作
                        queryParams.put("status",0);  //已评估
                        List<EmergencePlanTaskEntity> emergencePlanTaskEntityList1= (List<EmergencePlanTaskEntity>) emergencePlanTaskService.listByMap(queryParams);
                        if (emergencePlanTaskEntityList1.size()>0){
                            emergencePlanEntityExtend.setHasOperate(true); //已完成评估
                        }
                    }
                    String reviewUser=emergencePlanEntityExtend.getReviewUser();
                    if (reviewUser.contains(getUserId().toString())) {  //当前运维组长或者管理员是评审人员
                        emergencePlanEntityExtend.setOperate(true);
                    }
                }
            }

        }
        return R.ok().put("page", result);

    }


    /**
     * 应急预案评审催办
     */
    @PostMapping("/urgeHandle")
    @ApiOperation(value = "修改EmergencePlan对象", notes = "催办", response = R.class)
    public R urgeHandle(@RequestBody EmergencePlanEntityExtend emergencePlan) throws Exception {
        List<String> unReviewUserIds=new ArrayList<>();  //未评审的人员id集合
        String id=emergencePlan.getId(); //应急预案id
        Map<String,Object> queryParams=new HashMap<>();
        queryParams.put("yjyaid",id);
        queryParams.put("status","1");  //未评估
        queryParams.put("rwmc","assess");
        List<EmergencePlanTaskEntity> emergencePlanTaskEntityList= (List<EmergencePlanTaskEntity>) emergencePlanTaskService.listByMap(queryParams);
        if (emergencePlanTaskEntityList.size()>0){
            for (int i=0,len=emergencePlanTaskEntityList.size();i<len;i++){
                String unReviewUserId=emergencePlanTaskEntityList.get(i).getUpdateUser();
                unReviewUserIds.add(unReviewUserId);
            }
        }
        return R.ok();
    }

}
