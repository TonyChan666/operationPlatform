package cn.com.bmsoft.modules.epm.controller;
import java.util.*;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntityExtend;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanTaskEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanTaskService;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.DateUtils;

import cn.com.bmsoft.utils.DictionaryConstant;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 应急预案表
 *
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("epm/emergencePlan")
@Api("应急预案表 API")
public class EmergencePlanController extends AbstractController {
    @Autowired
    private EmergencePlanService emergencePlanService;
    @Autowired
    private EmergencePlanTaskService emergencePlanTaskService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SerialNumberService serialNumberService;

    /**
     * 应急预案分页列表
     */
//    @GetMapping("/list")
//    public R emergencePlanQueryList(@RequestParam Map<String, Object> params){
//        PageUtils result = emergencePlanService.emergencePlanQueryList(params);
//        return R.ok().put("page", result);
//
//    }


    /**
     * 应急预案分页列表
     */
    @GetMapping("/list")
    public R emergencePlanQueryList(@RequestParam Map<String, Object> params){
        PageUtils result=null;
        String actionType= (String) params.get("actionType");  //首页标识
        if (StringUtils.isNotBlank(actionType) && "indexForTodo".equals(actionType)){
            params.put("updateTaskUser",getUserId());
            result = emergencePlanService.unHandleList(params); //待办
        }else {
            result = emergencePlanService.emergencePlanQueryList(params);
        }
        return R.ok().put("page", result);

    }


    public static int[] stringArrayToIntArray(String[] array){
        int[] intArray = new int[array.length];
        for(int i = 0; i < array.length; i++){
            intArray[i] = Integer.parseInt(array[i]);
        }
        return intArray;
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("epm:emergenceplan:info")
    @ApiOperation(value = "获取emergenceplan对象详情", notes = "emergenceplan对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
        EmergencePlanEntityExtend emergencePlan=emergencePlanService.findEmergencePlanById(id);
        //获取运维人员的名字集合
        String reviewUser=emergencePlan.getReviewUser();
        String[] reviewUserArray=reviewUser.split(",");
        int [] reviewUserIds=stringArrayToIntArray(reviewUserArray); //
        List<String> reviewUserList=new ArrayList<>();
        for (int reviewUserId :reviewUserIds){
            SysUserEntity sysUserEntity= sysUserService.getById(reviewUserId);
            if (sysUserEntity!=null){
                int userid=sysUserEntity.getUserId().intValue();
                String name=sysUserEntity.getName();
                reviewUserList.add(name);
            }
        }
        String[] strings = new String[reviewUserList.size()];
        String []reviewsUserNamesArray=reviewUserList.toArray(strings);
        String reviewsUserNames=StringUtils.join(reviewsUserNamesArray,",");  //运维人员逗号分隔符字符串
        emergencePlan.setReviewUserId(reviewsUserNames);
        return R.ok().put("data", emergencePlan);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("epm:emergenceplan:save")
    @ApiOperation(value = "保存emergenceplan对象", notes = "emergenceplan对象", response = R.class)
    public R save(@RequestBody EmergencePlanEntityExtend emergencePlan){
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal(); //获取当前登录的用户
        R result=serialNumberService.generator("YAZD");
        String yjyabh= (String) result.get("serialNumber");
        emergencePlan.setYjyabh(yjyabh);
        emergencePlan.setYazt(DictionaryConstant.EMERGENCE_PLAN_STATE_UNREVIEW); //刚创建进入待派发状态
        emergencePlan.setSfypg(DictionaryConstant.EMERGENCE_PLAN_STATE_UNASSESS);  //未评估
        emergencePlan.setSfyps(DictionaryConstant.EMERGENCE_PLAN_STATE_UNEXAMINE);  //未评审
        String nowTime = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        emergencePlan.setCreateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
        emergencePlan.setUpdateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
        emergencePlan.setCreateUser(String.valueOf(sysUserEntity.getUserId()));
        emergencePlan.setUpdateUser(String.valueOf(sysUserEntity.getUserId()));
        emergencePlan.setStatus(DictionaryConstant.STATUS_VALID); //有效
		emergencePlanService.save(emergencePlan);
        return R.ok();
    }




    /**
     * 修改
     */
    @PostMapping("/update")
    // @RequiresPermissions("epm:emergenceplan:update")
    @ApiOperation(value = "修改emergenceplan对象", notes = "emergenceplan对象", response = R.class)
    public R update(@RequestBody EmergencePlanEntity emergencePlan){
        SysUserEntity sysUserEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal(); //获取当前登录的用户
        emergencePlan.setUpdateUser(String.valueOf(sysUserEntity.getUserId()));
        String nowTime = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        emergencePlan.setUpdateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
        emergencePlanService.updateById1(emergencePlan);
        return R.ok();
    }

    /**
     * 删除
     */
//    @DeleteMapping("/delete")
//    // @RequiresPermissions("epm:emergenceplan:delete")
//    @ApiOperation(value = "删除emergenceplan对象", notes = "emergenceplan对象", response = R.class)
//    public R delete(@RequestBody String[] ids){
//		emergencePlanService.removeByIds(Arrays.asList(ids));
//        return R.ok();
//    }


    /**
     * 删除应急预案表(只是逻辑删除，不是物理删除)
     */

    @PostMapping("/delete")
    @ApiOperation(value = "删除EmergencePlan对象", notes = "删除应急预案表", response = R.class)
    public R deleteEmergencePlan(@RequestParam String id) throws Exception {
        int result = emergencePlanService.deleteByIdLogical(id);
        return R.ok();
    }


    /**
     * 无效
     */
    @GetMapping("/changeStatus/{id}")
    // @RequiresPermissions("epm:emergenceplanassess:info")
    @ApiOperation(value = "修改EmergencePlan对象详情", notes = "EmergencePlan对象详情", response = R.class)
    public R changeStatus(@PathVariable("id") String id) throws Exception {
        int result = emergencePlanService.deleteByIdLogical(id);
        return R.ok();
    }


    /**
     * 批量删除应急预案表(只是逻辑删除，不是物理删除)
     */
    @PostMapping("/deleteBatch")
    @ApiOperation(value = "删除EmergencePlan对象", notes = "删除应急预案表", response = R.class)
    public R deleteEmergencePlanBatch(@RequestBody String[] ids) throws Exception {
        int result = emergencePlanService.deleteByIdLogicalBatch(ids);
        return R.ok();
    }



    /**
     * 派发评审
     */
    @PostMapping("/distributionReview")
    @ApiOperation(value = "修改EmergencePlan对象", notes = "派发评审", response = R.class)
    public R distributionReview(@RequestBody EmergencePlanEntityExtend emergencePlan) throws Exception {
        EmergencePlanTaskEntity taskEntity=new EmergencePlanTaskEntity();
        emergencePlan.setYazt(DictionaryConstant.EMERGENCE_PLAN_STATE_REVIEW); //修改预案状态为已派发
        String nowTime = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        emergencePlan.setUpdateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN)); //用于预案派发时间帅选
        boolean flag= emergencePlanService.updateById(emergencePlan);
        //派发评审的时候需要给评审人员派发任务
        if(flag==true) {
            String emergencePlanId = emergencePlan.getId();
            EmergencePlanEntityExtend emergencePlanEntityExtend = emergencePlanService.findEmergencePlanById(emergencePlanId);
            String reviewUser = emergencePlanEntityExtend.getReviewUser();//派发评审人员
            String reviewUserArray[] = reviewUser.split(",");
            taskEntity.setYjyaid(emergencePlanId);
            taskEntity.setRwmc(DictionaryConstant.EMERGENCE_PLAN_Task_Review);
            taskEntity.setCreateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
            taskEntity.setUpdateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
            taskEntity.setStatus(DictionaryConstant.STATUS_VALID);
            taskEntity.setCreateUser(String.valueOf(getUserId()));   //当前用户（评审分派者）
            for (int i = 0, len = reviewUserArray.length; i < len; i++) {
                taskEntity.setUpdateUser(reviewUserArray[i]);  //分派给具体的运维人员
                boolean flag1=emergencePlanTaskService.save(taskEntity);
                if (flag1){
                    taskEntity.setId("");
                }
            }
        }
        return R.ok();
    }


    /**
     * 发布
     */
    @PostMapping("/publish")
    @ApiOperation(value = "修改EmergencePlan对象", notes = "修改应急预案表",  response = R.class)
    public R publishEmergencePlan(@Valid @RequestBody EmergencePlanEntityExtend emergencePlan) throws Exception {
        //前端需要把选择的评估人员名单传过来assessUser("1,2,3")
        emergencePlan.setYazt(DictionaryConstant.EMERGENCE_PLAN_STATE_ESTIMATE);  //预案状态改为已发布
        String nowTime = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        emergencePlan.setUpdateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN)); //用于预案派发时间帅选
        String emergencePlanId=emergencePlan.getId(); //评审id
        String assessUser=emergencePlan.getAssessUser();
        boolean flag=emergencePlanService.updateById(emergencePlan);
        if(flag==true) {
            EmergencePlanTaskEntity taskEntity = new EmergencePlanTaskEntity();
            taskEntity.setYjyaid(emergencePlanId);
            taskEntity.setRwmc(DictionaryConstant.EMERGENCE_PLAN_Task_Assess);
            taskEntity.setCreateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
            taskEntity.setUpdateTime(DateUtils.stringToDate(nowTime, DateUtils.DATE_TIME_PATTERN));
            taskEntity.setStatus(DictionaryConstant.STATUS_VALID);
            taskEntity.setCreateUser(String.valueOf(getUserId()));   //当前用户（评估任务分派者）
            String assessUserArray[] = assessUser.split(",");
            for (int i = 0, len = assessUserArray.length; i < len; i++) {
                taskEntity.setUpdateUser(assessUserArray[i]);  //指定的具体评估运维人员
               boolean flag1=emergencePlanTaskService.save(taskEntity);
                if (flag1){
                    taskEntity.setId("");
                }
            }
        }

        return R.ok();
    }


    /**
     * 完结
     */
    @PostMapping("/end")
    @ApiOperation(value = "修改EmergencePlan对象", notes = "修改应急预案表", response = R.class)
    public R endEmergencePlan(@Valid @RequestBody EmergencePlanEntityExtend emergencePlan) throws Exception {
        emergencePlan.setYazt(DictionaryConstant.EMERGENCE_PLAN_STATE_END);  //预案状态改为已完结
        emergencePlanService.updateById(emergencePlan);
        return R.ok();
    }


}
