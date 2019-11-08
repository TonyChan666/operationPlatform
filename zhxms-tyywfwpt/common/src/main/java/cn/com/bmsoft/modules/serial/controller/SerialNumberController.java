package cn.com.bmsoft.modules.serial.controller;

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

import cn.com.bmsoft.modules.serial.entity.SerialNumberEntity;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 公共流水号表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-10-10
 */
@RestController
@RequestMapping("serial/number")
@Api("公共流水号表 API")
public class SerialNumberController {
    @Autowired
    private SerialNumberService serialNumberService;

    @GetMapping("/generator/{code}")
    public R generator(@PathVariable("code") String code){
        return serialNumberService.generator(code);
    }

    /**
     * 分页列表
     */
    @GetMapping("/page")
    @RequiresPermissions("serial:number:list")
    @ApiOperation(value = "获取serialNumber分页列表", notes = "serialNumber分页列表", response = R.class)
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = serialNumberService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("serial:number:info")
    @ApiOperation(value = "获取serialNumber对象详情", notes = "serialNumber对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		SerialNumberEntity serialNumber = serialNumberService.getById(id);
        return R.ok().put("serialNumber", serialNumber);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("serial:number:save")
    @ApiOperation(value = "保存serialNumber对象", notes = "serialNumber对象", response = R.class)
    public R save(@RequestBody SerialNumberEntity serialNumber){
		serialNumberService.save(serialNumber);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("serial:number:update")
    @ApiOperation(value = "修改serialNumber对象", notes = "serialNumber对象", response = R.class)
    public R update(@RequestBody SerialNumberEntity serialNumber){
		serialNumberService.updateById(serialNumber);
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @RequiresPermissions("serial:number:delete")
    @ApiOperation(value = "删除serialNumber对象", notes = "serialNumber对象", response = R.class)
        public R delete(@RequestParam String[] ids){
            serialNumberService.removeByIds(Arrays.asList(ids));
            return R.ok();
    }

}
