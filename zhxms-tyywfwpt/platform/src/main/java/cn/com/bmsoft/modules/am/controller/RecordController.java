package cn.com.bmsoft.modules.am.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.com.bmsoft.modules.am.utils.AmParams;
import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryItemService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
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

import cn.com.bmsoft.modules.am.entity.RecordEntity;
import cn.com.bmsoft.modules.am.service.RecordService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

/**
 * 告警记录表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@RestController
@RequestMapping("am/record")
@Api("告警记录表 API")
public class RecordController extends AbstractController {
    @Autowired
    private RecordService recordService;


    /**
     * 分页列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("am:record:list")
    @ApiOperation(value = "获取record分页列表", notes = "record分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        String status = (String) params.get("status");
        if (StringUtil.isNotBlank(status) && status.equals("1")){
            params.put("operationUserId", getUserId());
        }
        PageUtils page = recordService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("am:record:info")
    @ApiOperation(value = "获取record对象详情", notes = "record对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
		RecordEntity record = recordService.getByRecordId(id);
        return R.ok().put("record", record);
    }

    /**
     * 保存
     */
    /*@PostMapping("/save")
    @RequiresPermissions("am:record:save")
    @ApiOperation(value = "保存record对象", notes = "record对象", response = R.class)
    public R save(@RequestBody RecordEntity record){
		recordService.save(record);
        return R.ok();
    }*/

    /**
     * 修改
     */
    /*@PostMapping("/update")
    @RequiresPermissions("am:record:update")
    @ApiOperation(value = "修改record对象", notes = "record对象", response = R.class)
    public R update(@RequestBody RecordEntity record){
		recordService.updateById(record);
        return R.ok();
    }*/

    /**
     * 删除
     */
    /*@GetMapping("/delete")
    @RequiresPermissions("am:record:delete")
    @ApiOperation(value = "删除record对象", notes = "record对象", response = R.class)
        public R delete(@RequestParam String[] ids){
            recordService.removeByIds(Arrays.asList(ids));
            return R.ok();
    }*/

}
