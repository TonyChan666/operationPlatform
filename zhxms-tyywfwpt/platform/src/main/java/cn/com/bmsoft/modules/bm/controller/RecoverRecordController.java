package cn.com.bmsoft.modules.bm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import cn.com.bmsoft.modules.serial.service.SerialNumberService;
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

import cn.com.bmsoft.modules.bm.entity.RecoverRecordEntity;
import cn.com.bmsoft.modules.bm.service.RecoverRecordService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 备份管理-恢复登记表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("bm/recoverrecord")
@Api("备份管理-恢复登记表 API")
public class RecoverRecordController extends AbstractController {
    @Autowired
    private RecoverRecordService recoverRecordService;
    @Autowired
    private SerialNumberService serialNumberService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("bm:recoverrecord:list")
    @ApiOperation(value = "获取recoverrecord分页列表", notes = "recoverrecord分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = recoverRecordService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("bm:recoverrecord:info")
    @ApiOperation(value = "获取recoverrecord对象详情", notes = "recoverrecord对象详情", response = R.class)
    public R info(@PathVariable("id") int id){
		RecoverRecordEntity recoverRecord = recoverRecordService.getByRecordId(id);
        return R.ok().put("recoverRecord", recoverRecord);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("bm:recoverrecord:save")
    @ApiOperation(value = "保存recoverrecord对象", notes = "recoverrecord对象", response = R.class)
    public R save(@RequestBody RecoverRecordEntity recoverRecord){
        Long userId = getUserId();
        cn.com.bmsoft.utils.R result = serialNumberService.generator("HFCL");
        String hfclbh = (String) result.get("serialNumber");
        Date date = new Date();
        recoverRecord.setHfclbh(hfclbh);
        recoverRecord.setCjrid(userId);
        recoverRecord.setCjsj(date);
        recoverRecord.setXgrid(userId);
        recoverRecord.setXgsj(date);
        recoverRecordService.save(recoverRecord);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
   // @RequiresPermissions("bm:recoverrecord:update")
    @ApiOperation(value = "修改recoverrecord对象", notes = "recoverrecord对象", response = R.class)
    public R update(@RequestBody RecoverRecordEntity recoverRecord){
        recoverRecord.setXgrid(getUserId());
        recoverRecord.setXgsj(new Date());
        recoverRecordService.updateById(recoverRecord);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("bm:recoverrecord:delete")
    @ApiOperation(value = "删除recoverrecord对象", notes = "recoverrecord对象", response = R.class)
    public R delete(@RequestBody Long[] ids){
		recoverRecordService.updateBatchByIds(ids);
        return R.ok();
    }

}
