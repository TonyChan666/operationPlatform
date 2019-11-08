package cn.com.bmsoft.modules.epm.controller;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntityExtend;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanReviewEntity;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanTaskEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanReviewService;
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
@RequestMapping("epm/emergencePlanReview")
@Api("应急预案评审表 API")
public class EmergencePlanReviewController extends AbstractController {
    @Autowired
    private EmergencePlanReviewService emergencePlanReviewService;

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
    // @RequiresPermissions("epm:emergenceplanreview:list")
    @ApiOperation(value = "获取emergenceplanreview分页列表", notes = "emergenceplanreview分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = emergencePlanReviewService.queryPage(params);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("epm:emergenceplanreview:info")
    @ApiOperation(value = "获取emergenceplanreview对象详情", notes = "emergenceplanreview对象详情", response = R.class)
    public R info(@PathVariable("id") String id) {
        EmergencePlanReviewEntity emergencePlanReview = emergencePlanReviewService.getById(id);
        return R.ok().put("data", emergencePlanReview);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("epm:emergenceplanreview:save")
    @ApiOperation(value = "保存emergenceplanreview对象", notes = "emergenceplanreview对象", response = R.class)
    public R save(@RequestBody EmergencePlanReviewEntity emergencePlanReview) {
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal(); //获取当前登录的用户
        String taskId = "";
        String epid = emergencePlanReview.getEpid(); //对应派审任务id
        EmergencePlanTaskEntity emergencePlanTaskEntity = emergencePlanTaskService.getEmergencePlanTask(epid, String.valueOf(sysUserEntity.getUserId()), DictionaryConstant.EMERGENCE_PLAN_Task_Review);
        if (emergencePlanTaskEntity != null) {
            taskId = emergencePlanTaskEntity.getId();
        }
        emergencePlanReview.setRwid(taskId); //任务id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //日期格式
        String currentDate = sdf.format(new Date());
        R result=serialNumberService.generator("YAPS");
        String yapgbh= (String) result.get("serialNumber");
        emergencePlanReview.setYapgbh(yapgbh); //预案评审编号
        String nowTime = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        emergencePlanReview.setCreateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
        emergencePlanReview.setUpdateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
        emergencePlanReview.setStatus(DictionaryConstant.STATUS_VALID); //有效
        emergencePlanReview.setClzt(DictionaryConstant.EMERGENCE_PLAN_REVIEW_STATE_UNHANDLE); //处理状态为未处理
        emergencePlanReview.setYwryid(String.valueOf(sysUserEntity.getUserId()));  //运维人员id为当前登录用户的id
        emergencePlanReview.setCreateUser(String.valueOf(sysUserEntity.getUserId()));
        emergencePlanReview.setUpdateUser(String.valueOf(sysUserEntity.getUserId()));
        boolean flag=emergencePlanReviewService.save(emergencePlanReview);
       if(flag==true) {
           emergencePlanTaskEntity.setStatus(DictionaryConstant.STATUS_INVALID);
           emergencePlanTaskService.updateById(emergencePlanTaskEntity);  //更新对应的任务表的状态未0（从而标记该用户已经完成了评审工作）

           //判断一下该应急预案是否全部完成评审
           Map<String, Object> queryParam = new HashMap();
           queryParam.put("yjyaid", epid); //应急预案id
           queryParam.put("status", DictionaryConstant.STATUS_VALID);
           queryParam.put("rwmc", DictionaryConstant.EMERGENCE_PLAN_Task_Review);
           List<EmergencePlanTaskEntity> taskEntityList = (List<EmergencePlanTaskEntity>) emergencePlanTaskService.listByMap(queryParam);
           if (taskEntityList.size() == 0) {
               EmergencePlanEntityExtend emergencePlan1 = new EmergencePlanEntityExtend();
               emergencePlan1.setSfyps(DictionaryConstant.EMERGENCE_PLAN_STATE_EXAMINED); //是否已评审（已评审）
               emergencePlan1.setId(epid);
               emergencePlanService.updateById(emergencePlan1);   //改成已评审
           }
       }

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    // @RequiresPermissions("epm:emergenceplanreview:update")
    @ApiOperation(value = "修改emergenceplanreview对象", notes = "emergenceplanreview对象", response = R.class)
    public R update(@RequestBody EmergencePlanReviewEntity emergencePlanReview) {
        emergencePlanReviewService.updateById(emergencePlanReview);
        return R.ok();
    }

    /**
     * 删除应急预案评审表
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除EmergencePlanReview对象", notes = "删除应急预案评审表", response = R.class)
    public R deleteEmergencePlanReview(@RequestParam String id) throws Exception {
        int result = emergencePlanReviewService.deleteByIdLogical(id);
        return R.ok();
    }


    /**
     * 批量删除应急预案表(只是逻辑删除，不是物理删除)
     */
    @PostMapping("/deleteBatch")
    @ApiOperation(value = "删除EmergencePlan对象", notes = "删除应急预案表", response = R.class)
    public R deleteEmergencePlanReviewBatch(@RequestParam(value = "ids") String id) throws Exception {
        int result = emergencePlanReviewService.deleteByIdLogicalBatch(id);
        return R.ok();
    }


    /**
     * 应急预案评审分页列表
     */
    @GetMapping("/emergencePlanReviewQueryList")
    public R emergencePlanReviewQueryList(@RequestParam Map<String, Object> params) {
        params.put("status", DictionaryConstant.STATUS_VALID);//筛选有效的（已删除的不显示）
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal(); //获取当前登录的用户
//        params.put("createUser", sysUserEntity.getUserId());
        PageUtils result = emergencePlanReviewService.emergencePlanReviewQueryList(params);
        String epid= (String) params.get("epid");
        Map<String,Object> taskMapParams=new HashMap<>();
        taskMapParams.put("yjyaid",epid);
        taskMapParams.put("rwmc","review");
        EmergencePlanTaskEntity emergencePlanTaskEntity=null;
        List<EmergencePlanTaskEntity> emergencePlanTaskEntityList= emergencePlanTaskService.getEmergencePlanTaskList(taskMapParams);
        if (emergencePlanTaskEntityList.size()>0){
            emergencePlanTaskEntity=emergencePlanTaskEntityList.get(0);
        }
        return R.ok().put("page", result).put("emergencePlanTaskEntity",emergencePlanTaskEntity);

    }




    /**
     * 应急预案待评审分页列表
     */
    @GetMapping("/unReviewList")
    public R unReviewQueryList(@RequestParam Map<String, Object> params) {
        PageUtils result=null;
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal(); //获取当前登录的用户
        //获取用户所属的角色列（除了被派发评审的运维人员之外，系统管理员和运维组长可以查看应急该应急预案）
//        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(getUserId());
        Long roleId = getUser().getRoleId();
        params.put("sfyps", DictionaryConstant.EMERGENCE_PLAN_STATE_UNEXAMINE);//是否已评审（待评审)
        params.put("yazt", DictionaryConstant.EMERGENCE_PLAN_STATE_REVIEW); //预案状态（已派发)
        params.put("status", DictionaryConstant.STATUS_VALID);//筛选有效的（已删除的不显示）
        if( roleId !=Constant.ADMIN_ROLE  && roleId !=Constant.OPERATIONS_GROUP_LEADER_ROLE) {  //管理员
            params.put("taskStatus", DictionaryConstant.STATUS_VALID); //任务状态未评审1
            params.put("rwmc", DictionaryConstant.EMERGENCE_PLAN_Task_Review); //任务表是评审类型的
            params.put("updateTaskUser", String.valueOf(sysUserEntity.getUserId())); //只对被派发评审的运维人员显示
            result = emergencePlanService.unReviewQueryList(params);
            //判断一下运维组长和管理员是否是被派发人员，如果不是前端则没有则没有评审操作按钮
            List<EmergencePlanEntityExtend> list= (List<EmergencePlanEntityExtend>) result.getList();
            if(list.size()>0) {
                for (int i = 0, len = list.size(); i < len; i++) {
                    EmergencePlanEntityExtend emergencePlanEntityExtend = list.get(i);
                    String reviewUser=emergencePlanEntityExtend.getReviewUser();
                    if (reviewUser.contains(getUserId().toString())) {  //当前运维组长或者管理员是评审人员
                        emergencePlanEntityExtend.setOperate(true);  //具有评审按钮权限
                        emergencePlanEntityExtend.setHasOperate(false);  //还没评审
                    }
                }
            }
        }else{
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
                    queryParams.put("status",0);//已评审
                    queryParams.put("rwmc","review");
                    //判断是否完成了评审
                    List<EmergencePlanTaskEntity> emergencePlanTaskEntityList= (List<EmergencePlanTaskEntity>) emergencePlanTaskService.listByMap(queryParams);
                    if (emergencePlanTaskEntityList.size()!=0){ //代表已评审
                    emergencePlanEntityExtend.setHasOperate(true); //已完成评审
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
        queryParams.put("status","1");  //未评审
        queryParams.put("rwmc","review");
        List<EmergencePlanTaskEntity> emergencePlanTaskEntityList= (List<EmergencePlanTaskEntity>) emergencePlanTaskService.listByMap(queryParams);
        if (emergencePlanTaskEntityList.size()>0){
          for (int i=0,len=emergencePlanTaskEntityList.size();i<len;i++){
              String unReviewUserId=emergencePlanTaskEntityList.get(i).getUpdateUser();
              unReviewUserIds.add(unReviewUserId);
          }
        }
        return R.ok();
    }






    /**
     * 应急预案待发布分页列表
     */
    @GetMapping("/unPublishedList")
    public R unPublishedQueryList(@RequestParam Map<String, Object> params) {
        params.put("sfyps",DictionaryConstant.EMERGENCE_PLAN_STATE_EXAMINED);//是否已评审（已评审)
        //前端如果预案状态为02就显示发布按钮，否则就不显示发布按钮
        params.put("status",DictionaryConstant.STATUS_VALID);//筛选有效的（已删除的不显示）
        PageUtils result = emergencePlanService.emergencePlanQueryList(params);
        return R.ok().put("page", result);

    }

}
