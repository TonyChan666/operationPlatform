package cn.com.bmsoft.modules.rm.controller;

import cn.com.bmsoft.modules.rm.entity.LinkDeviceEntity;
import cn.com.bmsoft.modules.rm.service.LinkDeviceService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 资源管理-链路设备表
 *
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
@RestController
@RequestMapping("rm/linkdevice")
@Api("资源管理-链路设备表 API")
public class LinkDeviceController {
    @Autowired
    private LinkDeviceService linkDeviceService;

    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("rm:linkdevice:list")
    @ApiOperation(value = "获取linkdevice分页列表", notes = "linkdevice分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = linkDeviceService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("rm:linkdevice:info")
    @ApiOperation(value = "获取linkdevice对象详情", notes = "linkdevice对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
		LinkDeviceEntity linkDevice = linkDeviceService.getById(id);
        return R.ok().put("linkDevice", linkDevice);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("rm:linkdevice:save")
    @ApiOperation(value = "保存linkdevice对象", notes = "linkdevice对象", response = R.class)
    public R save(@RequestBody LinkDeviceEntity linkDevice){
		linkDeviceService.save(linkDevice);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("rm:linkdevice:update")
    @ApiOperation(value = "修改linkdevice对象", notes = "linkdevice对象", response = R.class)
    public R update(@RequestBody LinkDeviceEntity linkDevice){
		linkDeviceService.updateById(linkDevice);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("rm:linkdevice:delete")
    @ApiOperation(value = "删除linkdevice对象", notes = "linkdevice对象", response = R.class)
    public R delete(@RequestBody Integer[] ids){
		linkDeviceService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
