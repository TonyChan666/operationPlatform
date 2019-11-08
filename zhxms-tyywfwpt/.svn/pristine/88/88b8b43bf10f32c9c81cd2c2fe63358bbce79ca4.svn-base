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

import cn.com.bmsoft.modules.am.entity.MessageRecordEntity;
import cn.com.bmsoft.modules.am.service.MessageRecordService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 短信记录表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@RestController
@RequestMapping("am/messagerecord")
@Api("短信记录表 API")
public class MessageRecordController {
    @Autowired
    private MessageRecordService messageRecordService;

    /**
     * 分页列表
     */
    @GetMapping("/page")
    @RequiresPermissions("am:messagerecord:list")
    @ApiOperation(value = "获取messagerecord分页列表", notes = "messagerecord分页列表", response = R.class)
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = messageRecordService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("am:messagerecord:info")
    @ApiOperation(value = "获取messagerecord对象详情", notes = "messagerecord对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		MessageRecordEntity messageRecord = messageRecordService.getById(id);
        return R.ok().put("messageRecord", messageRecord);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("am:messagerecord:save")
    @ApiOperation(value = "保存messagerecord对象", notes = "messagerecord对象", response = R.class)
    public R save(@RequestBody MessageRecordEntity messageRecord){
		messageRecordService.save(messageRecord);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("am:messagerecord:update")
    @ApiOperation(value = "修改messagerecord对象", notes = "messagerecord对象", response = R.class)
    public R update(@RequestBody MessageRecordEntity messageRecord){
		messageRecordService.updateById(messageRecord);
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @RequiresPermissions("am:messagerecord:delete")
    @ApiOperation(value = "删除messagerecord对象", notes = "messagerecord对象", response = R.class)
        public R delete(@RequestParam String[] ids){
            messageRecordService.removeByIds(Arrays.asList(ids));
            return R.ok();
    }

}
