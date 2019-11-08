package cn.com.bmsoft.modules.rm.controller;

import cn.com.bmsoft.modules.rm.entity.ComponentEntity;
import cn.com.bmsoft.modules.rm.service.ComponentService;
import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.utils.StringUtil;
import cn.com.bmsoft.validator.ValidatorUtils;
import cn.com.bmsoft.validator.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 资源管理-资源组件表
 *
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
@RestController
@RequestMapping("rm/component")
@Api("资源管理-资源组件表 API")
public class ComponentController extends AbstractController {
    @Autowired
    private ComponentService componentService;

    @Autowired
    private SerialNumberService serialNumberService;

    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("rm:component:list")
    @ApiOperation(value = "获取component分页列表", notes = "component分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = componentService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("rm:component:info")
    @ApiOperation(value = "获取component对象详情", notes = "component对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
        ComponentEntity component = componentService.getComponentById(id);
        return R.ok().put("component", component);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("rm:component:save")
    @ApiOperation(value = "保存component对象", notes = "component对象", response = R.class)
    public R save(@RequestBody ComponentEntity component){
        //暂时不做限制
//        if(component.getZjbh() == null || component.getZjbh().length() != 5) {
//            return R.error("请输入五位数组件编号");
//        }
        //组件编号等于"ZJ"+五位编码
        R r = serialNumberService.generator(RmParams.CODE_PREFIX.get(RmParams.COMPONENT_ENTITY_NAME));
        component.setZjbh(r.get(RmParams.SERIAL_NUMBER)+"");
        ValidatorUtils.validateEntity(component, AddGroup.class);
//        component.setZjbh(RmParams.CODE_PREFIX.get(RmParams.COMPONENT_ENTITY_NAME) + component.getZjbh());
        //创建时间
        component.setCjsj(new Date());
        //创建人
        component.setCjrid(getUserId().intValue());
        //保存
		return componentService.saveComponent(component, getUser());
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("rm:component:update")
    @ApiOperation(value = "修改component对象", notes = "component对象", response = R.class)
    public R update(@RequestBody ComponentEntity component){
        component.setXgrid(getUserId().intValue());
        component.setXgsj(new Date());
		return componentService.updateComponent(component);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("rm:component:delete")
    @ApiOperation(value = "删除component对象", notes = "component对象", response = R.class)
    public R delete(@RequestBody Integer[] ids){
        if(ids == null || ids.length == 0){
            return R.error("请指定要删除的资源");
        }
        List<ComponentEntity> entities = new ArrayList<ComponentEntity>();
        for(Integer id : ids){
            ComponentEntity entity = new ComponentEntity();
            entity.setId(id);
            entity.setDeleteFlag("1");
            entities.add(entity);
        }
        componentService.updateBatchById(entities);
        return R.ok();
    }

    @PostMapping("/updateStatus")
//    @RequiresPermissions("rm:component:updateStatus")
    @ApiOperation(value = "更新component对象的sfyx状态", notes = "component对象", response = R.class)
    public R updateStatus(@RequestBody ComponentEntity component){
        Integer id = component.getId();
        if(StringUtil.isEmpty(id+"")){
            return R.error("请指定要更新的资源");
        }
        component.setXgrid(getUserId().intValue());
        component.setXgsj(new Date());
        componentService.updateById(component);
        return R.ok();
    }

}
