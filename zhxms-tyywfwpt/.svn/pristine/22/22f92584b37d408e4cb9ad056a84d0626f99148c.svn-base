package cn.com.bmsoft.modules.rm.controller;

import cn.com.bmsoft.modules.rm.entity.StorageEntity;
import cn.com.bmsoft.modules.rm.service.StorageService;
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
 * 资源管理-资源存储表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 *
 * @update zhangyibing zhangyibing@bmsoft.com.cn
 */
@RestController
@RequestMapping("rm/storage")
@Api("资源管理-资源存储表 API")
public class StorageController extends AbstractController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private SerialNumberService serialNumberService;
    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("rm:storage:list")
    @ApiOperation(value = "获取storage分页列表", notes = "storage分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = storageService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("rm:storage:info")
    @ApiOperation(value = "获取storage对象详情", notes = "storage对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
		StorageEntity storage = storageService.getStorage(id);
        return R.ok().put("storage", storage);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("rm:storage:save")
    @ApiOperation(value = "保存storage对象", notes = "storage对象", response = R.class)
    public R save(@RequestBody StorageEntity storage){
//        if(storage.getCcbh() == null || storage.getCcbh().length() != 5) {
//            return R.error("请输入五位数存储资源编码");
//        }
        //设备编号等于"CC"+五位编码
        R r = serialNumberService.generator(RmParams.CODE_PREFIX.get(RmParams.STORAGE_ENTITY_NAME));
        storage.setCcbh(r.get(RmParams.SERIAL_NUMBER)+"");
        ValidatorUtils.validateEntity(storage, AddGroup.class);
        //创建时间
        storage.setCjsj(new Date());
        //创建人
        storage.setCjrid(getUserId().intValue());
        //保存
		storageService.saveStorage(storage);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("rm:storage:update")
    @ApiOperation(value = "修改storage对象", notes = "storage对象", response = R.class)
    public R update(@RequestBody StorageEntity storage){
        storage.setXgsj(new Date());
        storage.setXgrid(getUserId().intValue());
		return storageService.updateStorage(storage);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("rm:storage:delete")
    @ApiOperation(value = "删除storage对象", notes = "storage对象", response = R.class)
    public R delete(@RequestBody Integer[] ids){
        if(ids == null || ids.length == 0){
            return R.error("请指定要删除的资源");
        }
        List<StorageEntity> entities = new ArrayList<StorageEntity>();
        for(Integer id : ids){
            StorageEntity entity = new StorageEntity();
            entity.setId(id);
            entity.setDeleteFlag("1");
            entities.add(entity);
        }
        storageService.updateBatchById(entities);
        return R.ok();
    }

    @PostMapping("/updateStatus")
//    @RequiresPermissions("rm:storage:updateStatus")
    @ApiOperation(value = "更新storage对象的sfyx状态", notes = "storage对象", response = R.class)
    public R updateStatus(@RequestBody StorageEntity storage){
        Integer id = storage.getId();
        if(StringUtil.isEmpty(id+"")){
            return R.error("请指定要更新的资源");
        }
        storage.setXgrid(getUserId().intValue());
        storage.setXgsj(new Date());
        storageService.updateById(storage);
        return R.ok();
    }

}
