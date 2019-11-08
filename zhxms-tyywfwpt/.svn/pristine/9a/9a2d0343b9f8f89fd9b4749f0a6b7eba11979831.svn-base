package cn.com.bmsoft.modules.wom.controller;

import cn.com.bmsoft.modules.wom.entity.ProcessEntity;
import cn.com.bmsoft.modules.wom.service.ProcessService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.workflowInterface.RestTemplate;
import cn.com.bmsoft.workflowInterface.Result;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 工单管理-工单过程表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
@RestController
@RequestMapping("wom/workflowmode")
@Api("工单管理-工单过程表 API")
public class WorkFlowModelController {
    @Autowired
    private ProcessService processService;

    /**
     * 列表
     */
    @GetMapping("/list")
   // @RequiresPermissions("wom:workflowmode:list")
    @ApiOperation(value = "获取workflowmode分页列表", notes = "workflowmode分页列表", response = R.class)
    public Result list(@RequestParam Map<String, Object> params){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.flowchartTemplateList(params);
        return Result.ok(result);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
   // @RequiresPermissions("wom:workflowmode:info")
    @ApiOperation(value = "获取workflowmode对象详情", notes = "workflowmode对象详情", response = R.class)
    public R info(@PathVariable("id") int id){
        RestTemplate restTemplate = new RestTemplate();
        String urlModel = "http://10.194.186.222:9601/modeler.html?modelId=" +id;
        return R.ok().put("urlModel",urlModel);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("wom:workflowmode:save")
    @ApiOperation(value = "保存workflowmode对象", notes = "workflowmode对象", response = R.class)
    public Result save(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.addFlowchartTemplate();
        return Result.ok(result);
    }

    /**
     * 修改
     */
    @GetMapping("/update/{id}")
   // @RequiresPermissions("wom:workflowmode:update")
    @ApiOperation(value = "修改workflowmode对象", notes = "workflowmode对象", response = R.class)
    public R update(@PathVariable("id") String id){
        RestTemplate restTemplate = new RestTemplate();
        String urlModel = "http://10.194.186.222:9601/modeler.html?modelId=" +id;
        return R.ok().put("urlModel",urlModel);
    }

    /**
     * 删除
     */
    @GetMapping("/delete/{id}")
    //@RequiresPermissions("wom:workflowmode:delete")
    @ApiOperation(value = "删除pworkflowmode对象", notes = "workflowmode对象", response = R.class)
    public R delete(@PathVariable("id") int id){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.deleteFlowchartTemplate(id);
        return R.ok();
    }
    /**
     * 发布流程
     */
    @GetMapping("/publish/{id}")
   // @RequiresPermissions("wom:workflowmode:publish")
    @ApiOperation(value = "删除workflowmode对象", notes = "workflowmode对象", response = R.class)
    public Result publish(@PathVariable("id") int id){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> result = restTemplate.fabuFlowchartTemplate(id);
        return Result.ok(result);
    }
}
