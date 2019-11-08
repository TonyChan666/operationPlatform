package cn.com.bmsoft.modules.am.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import cn.com.bmsoft.modules.am.utils.AmCode;
import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryItemService;
import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import cn.com.bmsoft.modules.am.entity.ModelEntity;
import cn.com.bmsoft.modules.am.service.ModelService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 告警模板表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@RestController
@RequestMapping("am/model")
@Api("告警模板表 API")
public class ModelController extends AbstractController {
    @Autowired
    private ModelService modelService;

    @Autowired
    private SerialNumberService serialNumberService;

    @Autowired
    private DictionaryItemService dictionaryItemService;
    /**
     * 分页列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("am:model:list")
    @ApiOperation(value = "获取model分页列表", notes = "model分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = modelService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("am:model:info")
    @ApiOperation(value = "获取model对象详情", notes = "model对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		ModelEntity model = modelService.getById(id);
		model.setSendChannelName(dictionaryItemService.getOne(new QueryWrapper<DictionaryItemEntity>().eq("dict_code", "gjfs")
                .eq("value", model.getSendChannel())).getName());
        return R.ok().put("model", model);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("am:model:save")
    @ApiOperation(value = "保存model对象", notes = "model对象", response = R.class)
    public R save(@RequestBody ModelEntity model){
        model.setSendTypeCode(serialNumberService.generator(AmCode.AM_MODEL.getName()).get("serialNumber")+"");
        model.setCreateUserId(getUserId());
        model.setCreateTime(new Date());
		modelService.save(model);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("am:model:update")
    @ApiOperation(value = "修改model对象", notes = "model对象", response = R.class)
    public R update(@RequestBody ModelEntity model){
        model.setUpdateUserId(getUserId());
        model.setUpdateTime(new Date());
		modelService.updateById(model);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("am:model:delete")
    @ApiOperation(value = "删除model对象", notes = "model对象", response = R.class)
        public R delete(@RequestBody Long[] ids){
            modelService.removeModelEntityByIds(Arrays.asList(ids));
            return R.ok();
    }

    @PostMapping("/updateStatus")
//    @RequiresPermissions("rm:model:updateStatus")
    @ApiOperation(value = "更新model对象的isActive状态", notes = "model对象", response = R.class)
    public R updateStatus(@RequestBody ModelEntity model){
        Integer id = model.getId();
        if(StringUtil.isEmpty(id+"")){
            return R.error("请指定要更新的资源");
        }
        model.setUpdateUserId(getUserId());
        model.setUpdateTime(new Date());
        modelService.updateById(model);
        return R.ok();
    }

}
