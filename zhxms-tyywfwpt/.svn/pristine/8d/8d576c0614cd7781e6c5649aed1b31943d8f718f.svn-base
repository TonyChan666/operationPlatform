package cn.com.bmsoft.modules.am.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import cn.com.bmsoft.modules.sys.controller.AbstractController;
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

import cn.com.bmsoft.modules.am.entity.HandleEntity;
import cn.com.bmsoft.modules.am.service.HandleService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 告警处理表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@RestController
@RequestMapping("am/handle")
@Api(" API")
public class HandleController extends AbstractController {
    @Autowired
    private HandleService handleService;

    /**
     * 分页列表
     */
    /*@GetMapping("/page")
    @RequiresPermissions("am:handle:list")
    @ApiOperation(value = "获取handle分页列表", notes = "handle分页列表", response = R.class)
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = handleService.queryPage(params);
        return R.ok().put("page", page);
    }*/


    /**
     * 信息
     */
    /*@GetMapping("/info/{id}")
    @RequiresPermissions("am:handle:info")
    @ApiOperation(value = "获取handle对象详情", notes = "handle对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		HandleEntity handle = handleService.getById(id);
        return R.ok().put("handle", handle);
    }*/

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("am:handle:save")
    @ApiOperation(value = "保存handle对象", notes = "handle对象", response = R.class)
    public R save(@RequestBody HandleEntity handle){
        if (handle.getAlarmRecodId() == null){
            return R.error("未传告警记录id");
        }
        handle.setCreateUserId(getUserId());
        handle.setCreateTime(new Date());
        handle.setHandleTime(new Date());
        handle.setHandleUserId(getUserId());
		handleService.saveHandle(handle);
        return R.ok();
    }

    /**
     * 修改
     */
    /*@PostMapping("/update")
    @RequiresPermissions("am:handle:update")
    @ApiOperation(value = "修改handle对象", notes = "handle对象", response = R.class)
    public R update(@RequestBody HandleEntity handle){
		handleService.updateById(handle);
        return R.ok();
    }*/

    /**
     * 删除
     */
    /*@GetMapping("/delete")
    @RequiresPermissions("am:handle:delete")
    @ApiOperation(value = "删除handle对象", notes = "handle对象", response = R.class)
        public R delete(@RequestParam String[] ids){
            handleService.removeByIds(Arrays.asList(ids));
            return R.ok();
    }*/

}
