package cn.com.bmsoft.modules.rm.controller;

import cn.com.bmsoft.modules.rm.entity.NetworkDeviceEntity;
import cn.com.bmsoft.modules.rm.service.NetworkDeviceService;
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
 * 资源管理-网络设备表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 *
 * @update zhangyibing zhangyibing@bmsoft.com.cn
 */
@RestController
@RequestMapping("rm/networkdevice")
@Api("资源管理-网络设备表 API")
public class NetworkDeviceController extends AbstractController {
    @Autowired
    private NetworkDeviceService networkDeviceService;
    @Autowired
    private SerialNumberService serialNumberService;
    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("rm:networkdevice:list")
    @ApiOperation(value = "获取networkdevice分页列表", notes = "networkdevice分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = networkDeviceService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("rm:networkdevice:info")
    @ApiOperation(value = "获取networkdevice对象详情", notes = "networkdevice对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
		NetworkDeviceEntity networkDevice = networkDeviceService.getNetworkDevice(id);
        return R.ok().put("networkDevice", networkDevice);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("rm:networkdevice:save")
    @ApiOperation(value = "保存networkdevice对象", notes = "networkdevice对象", response = R.class)
    public R save(@RequestBody NetworkDeviceEntity networkDevice){
//        if(networkDevice.getSbbh() == null || networkDevice.getSbbh().length() != 5) {
//            return R.error("请输入五位数设备编码");
//        }
        //设备编码等于"wssb"+五位编码
        R r = serialNumberService.generator(RmParams.CODE_PREFIX.get(RmParams.NETWORKDEVICE_ENTITY_NAME));
        networkDevice.setSbbh(r.get(RmParams.SERIAL_NUMBER)+"");
        ValidatorUtils.validateEntity(networkDevice, AddGroup.class);
//        networkDevice.setSbbh(RmParams.CODE_PREFIX.get(RmParams.NETWORKDEVICE_ENTITY_NAME) + networkDevice.getSbbh());
        //创建时间
        networkDevice.setCjsj(new Date());
        //创建人
        networkDevice.setCjrid(getUserId().intValue());
        //保存
		networkDeviceService.saveNetworkDevice(networkDevice);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("rm:networkdevice:update")
    @ApiOperation(value = "修改networkdevice对象", notes = "networkdevice对象", response = R.class)
    public R update(@RequestBody NetworkDeviceEntity networkDevice){
        networkDevice.setXgsj(new Date());
        networkDevice.setXgrid(getUserId().intValue());
        return networkDeviceService.updateNetworkDevice(networkDevice);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("rm:networkdevice:delete")
    @ApiOperation(value = "删除networkdevice对象", notes = "networkdevice对象", response = R.class)
    public R delete(@RequestBody Integer[] ids){
        if(ids == null || ids.length == 0){
            return R.error("请指定要删除的资源");
        }
        //只是逻辑删除，不在页面上显示，修改deleteFlag字段值为1
        List<NetworkDeviceEntity> entities = new ArrayList<NetworkDeviceEntity>();
        for(Integer id : ids){
            NetworkDeviceEntity entity = new NetworkDeviceEntity();
            entity.setId(id);
            entity.setDeleteFlag("1");
            entities.add(entity);
        }
        networkDeviceService.updateBatchById(entities);
        return R.ok();
    }

    @PostMapping("/updateStatus")
//    @RequiresPermissions("rm:networkdevice:updateStatus")
    @ApiOperation(value = "更新networkdevice对象的sfyx状态", notes = "networkdevice对象", response = R.class)
    public R updateStatus(@RequestBody NetworkDeviceEntity networkdevice){
        Integer id = networkdevice.getId();
        if(StringUtil.isEmpty(id+"")){
            return R.error("请指定要更新的资源");
        }
        networkdevice.setXgrid(getUserId().intValue());
        networkdevice.setXgsj(new Date());
        networkDeviceService.updateById(networkdevice);
        return R.ok();
    }

}
