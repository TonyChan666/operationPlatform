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

import cn.com.bmsoft.modules.am.entity.AppRunningEntity;
import cn.com.bmsoft.modules.am.service.AppRunningService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@RestController
@RequestMapping("am/apprunning")
@Api(" API")
public class AppRunningController {
    @Autowired
    private AppRunningService appRunningService;

    /**
     * 分页列表
     */
    @GetMapping("/page")
    @RequiresPermissions("am:apprunning:list")
    @ApiOperation(value = "获取apprunning分页列表", notes = "apprunning分页列表", response = R.class)
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = appRunningService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("am:apprunning:info")
    @ApiOperation(value = "获取apprunning对象详情", notes = "apprunning对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		AppRunningEntity appRunning = appRunningService.getById(id);
        return R.ok().put("appRunning", appRunning);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("am:apprunning:save")
    @ApiOperation(value = "保存apprunning对象", notes = "apprunning对象", response = R.class)
    public R save(@RequestBody AppRunningEntity appRunning){
		appRunningService.save(appRunning);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("am:apprunning:update")
    @ApiOperation(value = "修改apprunning对象", notes = "apprunning对象", response = R.class)
    public R update(@RequestBody AppRunningEntity appRunning){
		appRunningService.updateById(appRunning);
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @RequiresPermissions("am:apprunning:delete")
    @ApiOperation(value = "删除apprunning对象", notes = "apprunning对象", response = R.class)
        public R delete(@RequestParam String[] ids){
            appRunningService.removeByIds(Arrays.asList(ids));
            return R.ok();
    }

}
