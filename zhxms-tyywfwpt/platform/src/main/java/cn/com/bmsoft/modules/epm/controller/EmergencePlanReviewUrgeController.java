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

import cn.com.bmsoft.modules.epm.entity.EmergencePlanReviewUrgeEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanReviewUrgeService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 应急预案评审催办表
 *
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("epm/emergence_plan_revie_wurge")
@Api("应急预案评审催办表 API")
public class EmergencePlanReviewUrgeController {
    @Autowired
    private EmergencePlanReviewUrgeService emergencePlanReviewUrgeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("epm:emergenceplanreviewurge:list")
    @ApiOperation(value = "获取emergenceplanreviewurge分页列表", notes = "emergenceplanreviewurge分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = emergencePlanReviewUrgeService.queryPage(params);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("epm:emergenceplanreviewurge:info")
    @ApiOperation(value = "获取emergenceplanreviewurge对象详情", notes = "emergenceplanreviewurge对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		EmergencePlanReviewUrgeEntity emergencePlanReviewUrge = emergencePlanReviewUrgeService.getById(id);
        return R.ok().put("data", emergencePlanReviewUrge);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("epm:emergenceplanreviewurge:save")
    @ApiOperation(value = "保存emergenceplanreviewurge对象", notes = "emergenceplanreviewurge对象", response = R.class)
    public R save(@RequestBody EmergencePlanReviewUrgeEntity emergencePlanReviewUrge){
		emergencePlanReviewUrgeService.save(emergencePlanReviewUrge);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("epm:emergenceplanreviewurge:update")
    @ApiOperation(value = "修改emergenceplanreviewurge对象", notes = "emergenceplanreviewurge对象", response = R.class)
    public R update(@RequestBody EmergencePlanReviewUrgeEntity emergencePlanReviewUrge){
		emergencePlanReviewUrgeService.updateById(emergencePlanReviewUrge);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("epm:emergenceplanreviewurge:delete")
    @ApiOperation(value = "删除emergenceplanreviewurge对象", notes = "emergenceplanreviewurge对象", response = R.class)
    public R delete(@RequestBody String[] ids){
		emergencePlanReviewUrgeService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
