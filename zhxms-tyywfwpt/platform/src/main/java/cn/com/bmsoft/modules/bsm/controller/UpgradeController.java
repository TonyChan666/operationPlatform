package cn.com.bmsoft.modules.bsm.controller;

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

import cn.com.bmsoft.modules.bsm.entity.UpgradeEntity;
import cn.com.bmsoft.modules.bsm.service.UpgradeService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-10-08
 */
@RestController
@RequestMapping("bsm/upgrade")
@Api(" API")
public class UpgradeController {
    @Autowired
    private UpgradeService upgradeService;

    /**
     * 分页列表
     */
    @GetMapping("/page")
    @RequiresPermissions("bsm:upgrade:list")
    @ApiOperation(value = "获取upgrade分页列表", notes = "upgrade分页列表", response = R.class)
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = upgradeService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("bsm:upgrade:info")
    @ApiOperation(value = "获取upgrade对象详情", notes = "upgrade对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
		UpgradeEntity upgrade = upgradeService.getById(id);
        return R.ok().put("upgrade", upgrade);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("bsm:upgrade:save")
    @ApiOperation(value = "保存upgrade对象", notes = "upgrade对象", response = R.class)
    public R save(@RequestBody UpgradeEntity upgrade){
		upgradeService.save(upgrade);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("bsm:upgrade:update")
    @ApiOperation(value = "修改upgrade对象", notes = "upgrade对象", response = R.class)
    public R update(@RequestBody UpgradeEntity upgrade){
		upgradeService.updateById(upgrade);
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @RequiresPermissions("bsm:upgrade:delete")
    @ApiOperation(value = "删除upgrade对象", notes = "upgrade对象", response = R.class)
        public R delete(@RequestParam Integer[] ids){
            upgradeService.removeByIds(Arrays.asList(ids));
            return R.ok();
    }

}
