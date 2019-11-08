package cn.com.bmsoft.modules.wom.controller;

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

import cn.com.bmsoft.modules.wom.entity.ProcessEntity;
import cn.com.bmsoft.modules.wom.service.ProcessService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 工单管理-工单过程表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
@RestController
@RequestMapping("wom/process")
@Api("工单管理-工单过程表 API")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("wom:process:list")
    @ApiOperation(value = "获取process分页列表", notes = "process分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = processService.queryPage(params);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("wom:process:info")
    @ApiOperation(value = "获取process对象详情", notes = "process对象详情", response = R.class)
    public R info(@PathVariable("id") int id){
		ProcessEntity process = processService.getById(id);
        return R.ok().put("data", process);
    }
    /**
     * 获取派发时间，办理时限
     */
    @GetMapping("/infoBygdbh/{gdbh}")
    //@RequiresPermissions("wom:processEntity:info")
    @ApiOperation(value = "获取processEntity对象详情", notes = "processEntity", response = R.class)
    public R infoBygdbh(@PathVariable("gdbh") String gdbh){
        ProcessEntity processEntity = processService.infoBygdbh(gdbh);
        return R.ok().put("data", processEntity);
    }
    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("wom:process:save")
    @ApiOperation(value = "保存process对象", notes = "process对象", response = R.class)
    public R save(@RequestBody ProcessEntity process){
		processService.save(process);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("wom:process:update")
    @ApiOperation(value = "修改process对象", notes = "process对象", response = R.class)
    public R update(@RequestBody ProcessEntity process){
		processService.updateById(process);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("wom:process:delete")
    @ApiOperation(value = "删除process对象", notes = "process对象", response = R.class)
    public R delete(@RequestBody String[] gdhjIds){
		processService.removeByIds(Arrays.asList(gdhjIds));
        return R.ok();
    }

}
