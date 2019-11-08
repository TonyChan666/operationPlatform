package cn.com.bmsoft.modules.dictionary.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.com.bmsoft.validator.ValidatorUtils;
import cn.com.bmsoft.validator.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryItemService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

import javax.validation.Valid;

/**
 * 字典项表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-22
 */
@RestController
@RequestMapping("dictionary/dictionaryitem")
@Api("字典项表 API")
public class DictionaryItemController {

    @Autowired
    private DictionaryItemService dictionaryItemService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("dictionary:dictionaryitem:list")
    @ApiOperation(value = "获取dictionaryitem分页列表", notes = "dictionaryitem分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dictionaryItemService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("dictionary:dictionaryitem:info")
    @ApiOperation(value = "获取dictionaryitem对象详情", notes = "dictionaryitem对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
		DictionaryItemEntity dictionaryItem = dictionaryItemService.getById(id);
        return R.ok().put("dictionaryItem", dictionaryItem);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("dictionary:dictionaryitem:save")
    @ApiOperation(value = "保存dictionaryitem对象", notes = "dictionaryitem对象", response = R.class)
    public R save(@RequestBody DictionaryItemEntity dictionaryItem){
        ValidatorUtils.validateEntity(dictionaryItem, AddGroup.class);
        dictionaryItemService.save(dictionaryItem);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("dictionary:dictionaryitem:update")
    @ApiOperation(value = "修改dictionaryitem对象", notes = "dictionaryitem对象", response = R.class)
    public R update(@RequestBody DictionaryItemEntity dictionaryItem){
		dictionaryItemService.updateById(dictionaryItem);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("dictionary:dictionaryitem:delete")
    @ApiOperation(value = "删除dictionaryitem对象", notes = "dictionaryitem对象", response = R.class)
    public R delete(@RequestBody Integer[] ids){
		dictionaryItemService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
