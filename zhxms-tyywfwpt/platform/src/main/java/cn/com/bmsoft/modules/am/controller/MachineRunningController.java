package cn.com.bmsoft.modules.am.controller;

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

import cn.com.bmsoft.modules.am.entity.MachineRunningEntity;
import cn.com.bmsoft.modules.am.service.MachineRunningService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@RestController
@RequestMapping("am/machinerunning")
@Api(" API")
public class MachineRunningController {
    @Autowired
    private MachineRunningService machineRunningService;

    /**
     * 分页列表
     */
    @GetMapping("/page")
    @RequiresPermissions("am:machinerunning:list")
    @ApiOperation(value = "获取machinerunning分页列表", notes = "machinerunning分页列表", response = R.class)
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = machineRunningService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("am:machinerunning:info")
    @ApiOperation(value = "获取machinerunning对象详情", notes = "machinerunning对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		MachineRunningEntity machineRunning = machineRunningService.getById(id);
        return R.ok().put("machineRunning", machineRunning);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("am:machinerunning:save")
    @ApiOperation(value = "保存machinerunning对象", notes = "machinerunning对象", response = R.class)
    public R save(@RequestBody MachineRunningEntity machineRunning){
		machineRunningService.save(machineRunning);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("am:machinerunning:update")
    @ApiOperation(value = "修改machinerunning对象", notes = "machinerunning对象", response = R.class)
    public R update(@RequestBody MachineRunningEntity machineRunning){
		machineRunningService.updateById(machineRunning);
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @RequiresPermissions("am:machinerunning:delete")
    @ApiOperation(value = "删除machinerunning对象", notes = "machinerunning对象", response = R.class)
        public R delete(@RequestParam String[] ids){
            machineRunningService.removeByIds(Arrays.asList(ids));
            return R.ok();
    }

}
