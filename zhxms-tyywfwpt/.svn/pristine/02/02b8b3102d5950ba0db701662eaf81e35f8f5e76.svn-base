package cn.com.bmsoft.modules.bm.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.com.bmsoft.modules.attachment.service.FileSystemService;
import cn.com.bmsoft.modules.bm.entity.StrategyEntity;
import cn.com.bmsoft.modules.bm.service.StrategyService;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import cn.com.bmsoft.modules.bm.entity.CheckLogEntity;
import cn.com.bmsoft.modules.bm.service.CheckLogService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
/**
 * 备份管理-备份巡查日志表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("bm/checklog")
@Api("备份管理-备份巡查日志表 API")
public class CheckLogController extends AbstractController  {
    @Autowired
    private CheckLogService checkLogService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("bm:checklog:list")
    @ApiOperation(value = "获取checklog分页列表", notes = "checklog分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = checkLogService.queryPage(params);
//        PageUtils page = checkLogService.queryPageList(params);
        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @GetMapping("/info/{id}")
   // @RequiresPermissions("bm:checklog:selectlogId")
    @ApiOperation(value = "获取checklog对象详情", notes = "checklog对象详情", response = R.class)
    public R selectlogId(@PathVariable("id") int id){
        CheckLogEntity checkLog = checkLogService.selectlogId(id);
        return R.ok().put("data", checkLog);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("bm:checklog:update")
    @ApiOperation(value = "修改checklog对象", notes = "checklog对象", response = R.class)
    public R update(@RequestBody CheckLogEntity checkLog){
		checkLogService.updateById(checkLog);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("bm:checklog:delete")
    @ApiOperation(value = "删除checklog对象", notes = "checklog对象", response = R.class)
    public R delete(@RequestBody Long[] ids){
		checkLogService.updateBatchByIds(ids);
        return R.ok();
    }
    /*
     *备份监控报告统计
     * */
    @GetMapping("/get-backup-report")
    //@RequiresPermissions("bm:checklog:get-backup-report")
    @ApiOperation(value = "获取BackupCheckLog分页列表",notes = "备份巡查日志表分页列表",response = R.class)
    public R getBackupReport( @RequestBody(required = false) Date startTime, Date endTime) {
        System.out.println("=================="+startTime +"    "+ endTime);
        Map<String,Integer> map = new HashMap<String,Integer>();
        /* 1、今天 */
        // List<BackupCheckLog> today = checkLogService.today();
        int today = checkLogService.countToday();
        /* 2、昨天 */
        // List<BackupCheckLog> yesterday = checkLogService.yesterday();
        int yesterday = checkLogService.countYesterday();
        /* 3、近7天 */
        //List<BackupCheckLog> sevenDays = checkLogService.sevenDays();
        int sevenDays = checkLogService.countSevenDays();
        /* 4、近30天 */
        // List<BackupCheckLog> thirtyDays = checkLogService.thirtyDays();
        int thirtyDays = checkLogService.countThirtyDays();
        /* 5、任意时间段 */
        // List<BackupCheckLog> angTime = checkLogService.angTime();
        int angTime = checkLogService.countAngTime(startTime,endTime);
        map.put("today",today);
        map.put("yesterday",yesterday);
        map.put("sevenDays",sevenDays);
        map.put("thirtyDays",thirtyDays);
        map.put("angTime",angTime);
        return R.ok();
    }
}
