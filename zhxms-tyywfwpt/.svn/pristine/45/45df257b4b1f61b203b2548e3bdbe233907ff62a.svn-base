package cn.com.bmsoft.modules.wom.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import cn.com.bmsoft.modules.wom.entity.BusinessBindingEntity;
import cn.com.bmsoft.modules.wom.service.BusinessBindingService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 工单管理-工单业务绑定表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-10-12
 */
@RestController
@RequestMapping("wom/businessBinding")
@Api("工单管理-工单业务绑定表 API")
public class BusinessBindingController {
    @Autowired
    private BusinessBindingService businessBindingService;

    /**
     * 分页列表
     */
    @GetMapping("/page")
    //@RequiresPermissions("wom:model:list")
    @ApiOperation(value = "获取model分页列表", notes = "model分页列表", response = R.class)
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = businessBindingService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("wom:model:info")
    @ApiOperation(value = "获取model对象详情", notes = "model对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
		BusinessBindingEntity model = businessBindingService.getById(id);
        return R.ok().put("model", model);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("wom:model:save")
    @ApiOperation(value = "保存model对象", notes = "model对象", response = R.class)
    public R save(@RequestBody BusinessBindingEntity model){
        businessBindingService.save(model);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("wom:model:update")
    @ApiOperation(value = "修改model对象", notes = "model对象", response = R.class)
    public R update(@RequestBody BusinessBindingEntity model){
        businessBindingService.updateById(model);
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    //@RequiresPermissions("wom:model:delete")
    @ApiOperation(value = "删除model对象", notes = "model对象", response = R.class)
        public R delete(@RequestParam Integer[] ids){
        businessBindingService.removeByIds(Arrays.asList(ids));
            return R.ok();
    }

}
