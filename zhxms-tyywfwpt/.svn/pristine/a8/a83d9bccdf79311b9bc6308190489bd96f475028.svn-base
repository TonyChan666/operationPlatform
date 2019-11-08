package cn.com.bmsoft.modules.am.controller;

import java.util.Arrays;
import java.util.Map;

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

import cn.com.bmsoft.modules.am.entity.EmailRecordEntity;
import cn.com.bmsoft.modules.am.service.EmailRecordService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 邮件记录表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@RestController
@RequestMapping("am/emailrecord")
@Api("邮件记录表 API")
public class EmailRecordController {
    @Autowired
    private EmailRecordService emailRecordService;

    /**
     * 分页列表
     */
    @GetMapping("/page")
    @RequiresPermissions("am:emailrecord:list")
    @ApiOperation(value = "获取emailrecord分页列表", notes = "emailrecord分页列表", response = R.class)
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = emailRecordService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("am:emailrecord:info")
    @ApiOperation(value = "获取emailrecord对象详情", notes = "emailrecord对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		EmailRecordEntity emailRecord = emailRecordService.getById(id);
        return R.ok().put("emailRecord", emailRecord);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("am:emailrecord:save")
    @ApiOperation(value = "保存emailrecord对象", notes = "emailrecord对象", response = R.class)
    public R save(@RequestBody EmailRecordEntity emailRecord){
		emailRecordService.save(emailRecord);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("am:emailrecord:update")
    @ApiOperation(value = "修改emailrecord对象", notes = "emailrecord对象", response = R.class)
    public R update(@RequestBody EmailRecordEntity emailRecord){
		emailRecordService.updateById(emailRecord);
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @RequiresPermissions("am:emailrecord:delete")
    @ApiOperation(value = "删除emailrecord对象", notes = "emailrecord对象", response = R.class)
        public R delete(@RequestParam String[] ids){
            emailRecordService.removeByIds(Arrays.asList(ids));
            return R.ok();
    }

}
