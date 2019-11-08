package cn.com.bmsoft.modules.am.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.bmsoft.modules.am.entity.ThresholdGradeEntity;
import cn.com.bmsoft.modules.am.service.ThresholdGradeService;
import cn.com.bmsoft.modules.am.utils.AmCode;
import cn.com.bmsoft.modules.am.utils.AmParams;
import cn.com.bmsoft.modules.am.vo.AmStrategyVo;
import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryItemService;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.utils.SpringContextUtils;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import cn.com.bmsoft.modules.am.entity.AmStrategyEntity;
import cn.com.bmsoft.modules.am.service.AmStrategyService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 告警策略表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@RestController
@RequestMapping("am/strategy")
@Api("告警策略表 API")
public class AmStrategyController extends AbstractController {
    @Autowired
    private AmStrategyService amStrategyService;

    @Autowired
    private ThresholdGradeService thresholdGradeService;

    @Autowired
    private SerialNumberService serialNumberService;

    @Autowired
    private DictionaryItemService dictionaryItemService;

    /**
     * 分页列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("am:strategy:list")
    @ApiOperation(value = "获取Strategy分页列表", notes = "strategy分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = amStrategyService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("am:strategy:info")
    @ApiOperation(value = "获取strategy对象详情", notes = "strategy对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		AmStrategyEntity strategy = amStrategyService.getById(id);
        AmStrategyVo amStrategyVo = EntityToVo(strategy);
		//查找阂值列表
        amStrategyVo.setThresholdGradeEntities(thresholdGradeService.list(new QueryWrapper<ThresholdGradeEntity>()
                .eq("strategy_id", id)
                .isNull("delete_flag")));
        String[] ids = strategy.getResourceList().split(",");
        //获取事项名称数组
        IService bean = (IService) SpringContextUtils.getBean(AmParams.CODE_PREFIX .get(strategy.getResourceType()));
        String name = AmParams.RM_TABLE_NAME.get(strategy.getResourceType());
        List<Map<String, Object>> items = bean.listMaps(new QueryWrapper<>()
                .select("id", name + " name")
                .in("id", Arrays.asList(ids)));
       amStrategyVo.setItems(items);
        return R.ok().put("strategy", amStrategyVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("am:strategy:save")
    @ApiOperation(value = "保存strategy对象", notes = "strategy对象", response = R.class)
    public R save(@RequestBody AmStrategyVo strategy){
        if (strategy.getThresholdGradeEntities().size() == 0){
            return R.error("请配置阂值规则");
        }
        if (strategy.getAlarmProjectType() == null || strategy.getAlarmProjectType().isEmpty()){
            return R.error("未指定策略类型");
        }
        //创建时间
        strategy.setCreateTime(new Date());
        //创建人
        strategy.setCreateUserId(getUserId());
        //设置策略编码
        if (Integer.valueOf(strategy.getAlarmProjectType()) == AmCode.AM_RECOURCE.ordinal()){
            strategy.setAlarmStrategyCode(serialNumberService.generator(AmCode.AM_RECOURCE.getName()).get("serialNumber")+"");//ZYGJCL+随机六位数
        }else {
            strategy.setAlarmStrategyCode(serialNumberService.generator(AmCode.AM_SERVICE.getName()).get("serialNumber")+"");//FWGJCL+随机六位数
        }
		amStrategyService.saveAmStrategyEntity(strategy);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("am:strategy:update")
    @ApiOperation(value = "修改strategy对象", notes = "strategy对象", response = R.class)
    public R updateActive(@RequestBody AmStrategyVo strategy){
        //更新时间
        strategy.setUpdateTime(new Date());
        //更新人
        strategy.setUpdateUserId(getUserId());
        amStrategyService.updateAmStrategyById(strategy);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("am:strategy:delete")
    @ApiOperation(value = "删除strategy对象", notes = "strategy对象", response = R.class)
    public R delete(@RequestBody Long[] ids){
            amStrategyService.removeStrategyByIds(Arrays.asList(ids));
            return R.ok();
    }

    /**
     *  获取资源名称
     */
    @GetMapping("/getItems")
    @ApiOperation(value = "获取资源名称", notes = "资源对象", response = R.class)
    public R getItems(@RequestParam Map<String, Object> params){
        PageUtils page = amStrategyService.queryItemPage(params);
        return R.ok().put("page", page);
    }

    /**
     *  获取字典
     */
    @GetMapping("/getDictionaryItem/{value}")
    @ApiOperation(value = "获取资源名称", notes = "资源对象", response = R.class)
    public R getDictionaryItem(@PathVariable("value")String value, @RequestParam(value = "resourceType",required = false) String resourceType){
        List<DictionaryItemEntity> items = null;
        QueryWrapper<DictionaryItemEntity> wrapper = new QueryWrapper<DictionaryItemEntity>();
        if (value.equals("gjywlb")) {
            wrapper.eq("remark", "business");
            value = "gjzylb";
        }else if (value.equals("gjzylb")){
            wrapper.ne("remark", "business");
        }else if (value.equals("gjzb") && StringUtil.isNotBlank(resourceType)){
            wrapper.like("remark", resourceType);
        }
        items = dictionaryItemService.list(wrapper.eq("dict_code", value));
        return R.ok().put("items", items);
    }

    /**
     * entity转换为Vo
     * 请求参数描述、请求参数示例、响应参数描述、响应参数示例
     * @param vo
     * @return
     */
    private AmStrategyVo EntityToVo(AmStrategyEntity amStrategyEntity) {
        AmStrategyVo vo = new AmStrategyVo();
        BeanCopier copier = BeanCopier.create(AmStrategyEntity.class, AmStrategyVo.class, false);
        copier.copy(amStrategyEntity, vo, null);
        return vo;
    }

}
