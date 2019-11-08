package cn.com.bmsoft.modules.dictionary.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryItemService;
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

import cn.com.bmsoft.modules.dictionary.entity.DictionaryEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 字典表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-22
 */
@RestController
@RequestMapping("dictionary/dictionary")
@Api("字典表 API")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictionaryItemService dictionaryItemService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("dictionary:dictionary:list")
    @ApiOperation(value = "获取dictionary分页列表", notes = "dictionary分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dictionaryService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("dictionary:dictionary:info")
    @ApiOperation(value = "获取dictionary对象详情", notes = "dictionary对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
		DictionaryEntity dictionary = dictionaryService.getById(id);
        List<DictionaryItemEntity> items = dictionaryItemService.queryByDictCode(dictionary.getCode());
        dictionary.setItems(items);
        return R.ok().put("dictionary", dictionary);
    }

    /**
     * 字典查询
     * @param code
     * @return 对应字典项列表
     */
    @GetMapping("/queryByCode/{code}")
    public R info(@PathVariable("code") String code){
        List<DictionaryItemEntity> items = dictionaryItemService.queryByDictCode(code);
        return R.ok().put("items", items);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("dictionary:dictionary:save")
    @ApiOperation(value = "保存dictionary对象", notes = "dictionary对象", response = R.class)
    public R save(@RequestBody DictionaryEntity dictionary){
		dictionaryService.save(dictionary);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("dictionary:dictionary:update")
    @ApiOperation(value = "修改dictionary对象", notes = "dictionary对象", response = R.class)
    public R update(@RequestBody DictionaryEntity dictionary){
		dictionaryService.updateById(dictionary);
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @RequiresPermissions("dictionary:dictionary:delete")
    @ApiOperation(value = "删除dictionary对象", notes = "dictionary对象", response = R.class)
    public R delete(@RequestParam Integer[] ids){
		dictionaryService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
