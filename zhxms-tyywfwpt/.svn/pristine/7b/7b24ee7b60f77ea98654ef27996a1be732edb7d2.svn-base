package cn.com.bmsoft.modules.bm.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import cn.com.bmsoft.modules.am.utils.AmParams;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.utils.SpringContextUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import cn.com.bmsoft.modules.bm.entity.StrategyEntity;
import cn.com.bmsoft.modules.bm.service.StrategyService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 备份管理-备份策略表
 *
 * @author sufang  sufanf@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("bm/strategy")
@Api("备份管理-备份策略表 API")
public class StrategyController extends AbstractController {
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private SerialNumberService serialNumberService;

    /**
     * 列表
     */
    @GetMapping("/list")
  /*  @RequiresPermissions("bm:strategy:list")*/
    @ApiOperation(value = "获取strategy分页列表", notes = "strategy分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = strategyService.queryPage(params);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("bm:strategy:info")
    @ApiOperation(value = "获取strategy对象详情", notes = "strategy对象详情", response = R.class)
    public R info(@PathVariable("id") int id){
		StrategyEntity strategy = strategyService.getByStrategyId(id);
        return R.ok().put("strategy", strategy);
    }
    /**
     *  获取资源名称
     */
    @GetMapping("/getResource/{value}")
    @ApiOperation(value = "获取资源名称", notes = "资源对象", response = R.class)
    public R getResources(@PathVariable String value){
        List list = Collections.singletonList("");
        //利用反射获取service
        IService bean = (IService) SpringContextUtils.getBean(AmParams.CODE_PREFIX.get(value));
        if(value.equals("rm_server")) {
            list = bean.list(new QueryWrapper<>().eq("sfyx", "1").isNull("delete_flag"));
            System.out.println(list);
        }else {
            list = bean.list(new QueryWrapper<>().eq("status", "1").isNull("delete_flag"));
        }
        //List list = bean.list();
        return R.ok().put("resourceNameList", list);
    }
    /**
     * 信息
     */
    @GetMapping("/infoBfbh/{bfbh}")
    //@RequiresPermissions("bm:strategy:info")
    @ApiOperation(value = "获取strategy对象详情", notes = "strategy对象详情", response = R.class)
    public R infoBfbh(@PathVariable("bfbh") String bfbh){
        QueryWrapper<StrategyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("bfbh",bfbh);
        StrategyEntity strategy = strategyService.getOne(wrapper);
        return R.ok().put("strategy", strategy);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("bm:strategy:save")
    @ApiOperation(value = "保存strategy对象", notes = "strategy对象", response = R.class)
    public R save(@RequestBody StrategyEntity strategy){
        Long userId = getUserId();
        cn.com.bmsoft.utils.R result = serialNumberService.generator("BFCL");
        String bfbh = (String) result.get("serialNumber");
        Date cjsj = new Date();
        strategy.setBfbh(bfbh);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strategy.setCjrid(userId);
        strategy.setCjsj(cjsj);
        strategy.setXgrid(userId);
        strategy.setXgsj(cjsj);
        strategyService.save(strategy);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("bm:strategy:update")
    @ApiOperation(value = "修改strategy对象", notes = "strategy对象", response = R.class)
    public R update(@RequestBody StrategyEntity strategy){
        Long userId = getUserId();
        Date zhxgsj = new Date();
        strategy.setXgrid(userId);
        strategy.setXgsj(zhxgsj);
        strategyService.updateById(strategy);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("bm:strategy:delete")
    @ApiOperation(value = "删除strategy对象", notes = "strategy对象", response = R.class)
    public R delete(@RequestBody Long[] ids){
		strategyService.updateBatchByIds(ids);
        return R.ok();
    }

}
