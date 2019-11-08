package cn.com.bmsoft.modules.rm.controller;

import cn.com.bmsoft.modules.rm.entity.ContainerEntity;
import cn.com.bmsoft.modules.rm.service.ContainerService;
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
 * 资源管理-资源容器表
 *
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
@RestController
@RequestMapping("rm/container")
@Api("资源管理-资源容器表 API")
public class ContainerController extends AbstractController {
    @Autowired
    private ContainerService containerService;
    @Autowired
    private SerialNumberService serialNumberService;
    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("rm:container:list")
    @ApiOperation(value = "获取container分页列表", notes = "container分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = containerService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("rm:container:info")
    @ApiOperation(value = "获取container对象详情", notes = "container对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
        ContainerEntity container = containerService.getContainer(id);
        return R.ok().put("container", container);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("rm:container:save")
    @ApiOperation(value = "保存container对象", notes = "container对象", response = R.class)
    public R save(@RequestBody ContainerEntity container){
//        if(container.getRqbm() == null || container.getRqbm().length() != 5) {
//            return R.error("请输入五位数容器编码");
//        }
        //容器编码等于"RQ"+五位编码
        R r = serialNumberService.generator(RmParams.CODE_PREFIX.get(RmParams.CONTAINER_ENTITY_NAME));
        container.setRqbm(r.get(RmParams.SERIAL_NUMBER)+"");
        ValidatorUtils.validateEntity(container, AddGroup.class);
//        container.setRqbm(RmParams.CODE_PREFIX.get(RmParams.CONTAINER_ENTITY_NAME) + container.getRqbm());
        //创建时间
        container.setCjsj(new Date());
        //创建人
        container.setCjrid(getUserId().intValue());
        //保存
		containerService.saveContainer(container);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("rm:container:update")
    @ApiOperation(value = "修改container对象", notes = "container对象", response = R.class)
    public R update(@RequestBody ContainerEntity container){
        container.setXgsj(new Date());
        container.setXgrid(getUserId().intValue());
		return containerService.updateContainer(container);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("rm:container:delete")
    @ApiOperation(value = "删除container对象", notes = "container对象", response = R.class)
    public R delete(@RequestBody Integer[] ids){
        //只是逻辑删除，不在页面上显示，修改deleteFlag字段值为1
        if(ids == null || ids.length == 0){
            return R.error("请指定要删除的资源");
        }
        List<ContainerEntity> entities = new ArrayList<ContainerEntity>();
        for(Integer id : ids){
            ContainerEntity entity = new ContainerEntity();
            entity.setId(id);
            entity.setDeleteFlag("1");
            entities.add(entity);
        }
        containerService.updateBatchById(entities);
        return R.ok();
    }

    @PostMapping("/updateStatus")
//    @RequiresPermissions("rm:container:updateStatus")
    @ApiOperation(value = "更新container对象的sfyx状态", notes = "container对象", response = R.class)
    public R updateStatus(@RequestBody ContainerEntity container){
        Integer id = container.getId();
        if(StringUtil.isEmpty(id+"")){
            return R.error("请指定要更新的资源");
        }
        container.setXgrid(getUserId().intValue());
        container.setXgsj(new Date());
        containerService.updateById(container);
        return R.ok();
    }

}
