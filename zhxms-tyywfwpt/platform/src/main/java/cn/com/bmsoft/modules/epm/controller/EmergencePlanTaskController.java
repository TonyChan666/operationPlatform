package cn.com.bmsoft.modules.epm.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanTaskEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanTaskService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 应急预案任务表
 *
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("epm/emergence_plan_task")
@Api("应急预案任务表 API")
public class EmergencePlanTaskController {
    @Autowired
    private EmergencePlanTaskService emergencePlanTaskService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("epm:emergenceplantask:list")
    @ApiOperation(value = "获取emergenceplantask分页列表", notes = "emergenceplantask分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = emergencePlanTaskService.queryPage(params);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("epm:emergenceplantask:info")
    @ApiOperation(value = "获取emergenceplantask对象详情", notes = "emergenceplantask对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		EmergencePlanTaskEntity emergencePlanTask = emergencePlanTaskService.getById(id);
        return R.ok().put("data", emergencePlanTask);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("epm:emergenceplantask:save")
    @ApiOperation(value = "保存emergenceplantask对象", notes = "emergenceplantask对象", response = R.class)
    public R save(@RequestBody EmergencePlanTaskEntity emergencePlanTask){
		emergencePlanTaskService.save(emergencePlanTask);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("epm:emergenceplantask:update")
    @ApiOperation(value = "修改emergenceplantask对象", notes = "emergenceplantask对象", response = R.class)
    public R update(@RequestBody EmergencePlanTaskEntity emergencePlanTask){
		emergencePlanTaskService.updateById(emergencePlanTask);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("epm:emergenceplantask:delete")
    @ApiOperation(value = "删除emergenceplantask对象", notes = "emergenceplantask对象", response = R.class)
    public R delete(@RequestBody String[] ids){
		emergencePlanTaskService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
