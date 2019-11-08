package cn.com.bmsoft.modules.rm.controller;

import cn.com.bmsoft.modules.rm.entity.ProbeEntity;
import cn.com.bmsoft.modules.rm.service.ProbeService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.utils.StringUtil;
import cn.com.bmsoft.validator.ValidatorUtils;
import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 资源管理-探针登记表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
@RestController
@RequestMapping("rm/probe")
@Api("资源管理-探针登记表 API")
public class ProbeController extends AbstractController {
    @Autowired
    private ProbeService probeService;
    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("rm:probe:list")
    @ApiOperation(value = "获取probe分页列表", notes = "probe分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = probeService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("rm:probe:info")
    @ApiOperation(value = "获取probe对象详情", notes = "probe对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
        ProbeEntity probe = probeService.getOne(new QueryWrapper<ProbeEntity>().isNull("delete_flag").eq("id",id));
        return R.ok().put("probe", probe);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("rm:probe:save")
    @ApiOperation(value = "保存probe对象", notes = "probe对象", response = R.class)
    public R save(@RequestBody ProbeEntity probe){
        if(StringUtil.isEmpty(probe.getTzbh())){
            return R.error("请输入探针编号！");
        }
        ProbeEntity tz = probeService.getOne(new QueryWrapper<ProbeEntity>().eq("tzbh", probe.getTzbh()));
        if(tz != null){
            return R.error("该编号已存在，请重新输入！");
        }
        ValidatorUtils.validateEntity(probe, AddGroup.class);
        //创建时间
        probe.setCjsj(new Date());
        //创建人
        probe.setCjrid(getUserId().intValue());
        //保存
		probeService.save(probe);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("rm:probe:update")
    @ApiOperation(value = "修改probe对象", notes = "probe对象", response = R.class)
    public R update(@RequestBody ProbeEntity probe){
        probe.setXgsj(new Date());
        probe.setXgrid(getUserId().intValue());
		probeService.updateById(probe);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("rm:probe:delete")
    @ApiOperation(value = "删除probe对象", notes = "probe对象", response = R.class)
    public R delete(@RequestBody Integer[] ids){
        if(ids == null || ids.length == 0){
            return R.error("请指定要删除的资源");
        }
        List<ProbeEntity> entities = new ArrayList<ProbeEntity>();
        for(Integer id : ids){
            ProbeEntity entity = new ProbeEntity();
            entity.setId(id);
            entity.setDeleteFlag("1");
            entities.add(entity);
        }
        probeService.updateBatchById(entities);
        return R.ok();
    }

    @PostMapping("/updateStatus")
//    @RequiresPermissions("rm:probe:updateStatus")
    @ApiOperation(value = "更新probe对象的sfyx状态", notes = "probe对象", response = R.class)
    public R updateStatus(@RequestBody ProbeEntity probe){
        Integer id = probe.getId();
        if(StringUtil.isEmpty(id+"")){
            return R.error("请指定要更新的资源");
        }
        probe.setXgrid(getUserId().intValue());
        probe.setXgsj(new Date());
        probeService.updateById(probe);
        return R.ok();
    }

}
