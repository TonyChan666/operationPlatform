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

import cn.com.bmsoft.modules.wom.entity.HandleInformationEntity;
import cn.com.bmsoft.modules.wom.service.HandleInformationService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 工单管理-工单处理详情表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
@RestController
@RequestMapping("wom/handleinformation")
@Api("工单管理-工单处理详情表 API")
public class HandleInformationController {
    @Autowired
    private HandleInformationService handleInformationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("wom:handleinformation:list")
    @ApiOperation(value = "获取handleinformation分页列表", notes = "handleinformation分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = handleInformationService.queryPage(params);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("wom:handleinformation:info")
    @ApiOperation(value = "获取handleinformation对象详情", notes = "handleinformation对象详情", response = R.class)
    public R info(@PathVariable("gdxqId") int id){
		HandleInformationEntity handleInformation = handleInformationService.getById(id);
        return R.ok().put("data", handleInformation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("wom:handleinformation:save")
    @ApiOperation(value = "保存handleinformation对象", notes = "handleinformation对象", response = R.class)
    public R save(@RequestBody HandleInformationEntity handleInformation){
		handleInformationService.save(handleInformation);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("wom:handleinformation:update")
    @ApiOperation(value = "修改handleinformation对象", notes = "handleinformation对象", response = R.class)
    public R update(@RequestBody HandleInformationEntity handleInformation){
		handleInformationService.updateById(handleInformation);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("wom:handleinformation:delete")
    @ApiOperation(value = "删除handleinformation对象", notes = "handleinformation对象", response = R.class)
    public R delete(@RequestBody int[] ids){
		handleInformationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
