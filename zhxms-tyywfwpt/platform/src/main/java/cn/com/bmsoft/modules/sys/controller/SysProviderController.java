package cn.com.bmsoft.modules.sys.controller;

import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.entity.SysProviderEntity;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.service.SysProviderService;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.validator.ValidatorUtils;
import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 供应商管理
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-27
 */
@RestController
@RequestMapping("sys/provider")
@Api("供应商管理 API")
public class SysProviderController extends AbstractController{

    @Autowired
    private SysProviderService sysProviderService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SerialNumberService serialNumberService;

    /**
     * 分页列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("sys:provider:list")
    @ApiOperation(value = "获取provider分页列表", notes = "provider分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysProviderService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @GetMapping("/select")
    @ApiOperation(value = "获取provider列表", notes = "provider列表", response = R.class)
    public R select(@RequestParam Map<String, Object> params){

        List<SysProviderEntity> SysProviderList = sysProviderService.list(new QueryWrapper<SysProviderEntity>()
                        .eq("status", 1)
                        .isNull("delete_flag"));
        return R.ok().put("list", SysProviderList);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{providerId}")
    // @RequiresPermissions("sys:provider:info")
    @ApiOperation(value = "获取provider对象详情", notes = "provider对象详情", response = R.class)
    public R info(@PathVariable("providerId") Integer providerId){
		SysProviderEntity provider = sysProviderService.getById(providerId);
        return R.ok().put("provider", provider);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("sys:provider:save")
    @ApiOperation(value = "保存provider对象", notes = "provider对象", response = R.class)
    public R save(@RequestBody SysProviderEntity provider){
        provider.setCreateTime(new Date());
        provider.setCreateUserId(getUserId());
        ValidatorUtils.validateEntity(provider, AddGroup.class);
        // 设置供应商编号
        R codeR = serialNumberService.generator("GYS");
        if((Integer) codeR.get("code") == 200){
            provider.setProviderCode((String)codeR.get("serialNumber"));
        }else {
            return codeR;
        }
		sysProviderService.save(provider);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    // @RequiresPermissions("sys:provider:update")
    @ApiOperation(value = "修改provider对象", notes = "provider对象", response = R.class)
    public R update(@RequestBody SysProviderEntity provider){
        provider.setUpdateTime(new Date());
        provider.setUpdateUserId(getUserId());
		sysProviderService.updateById(provider);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    // @RequiresPermissions("sys:provider:delete")
    @ApiOperation(value = "删除provider对象", notes = "provider对象", response = R.class)
    public R delete(@RequestBody Integer[] providerIds){
        List<Integer> ids = Arrays.asList(providerIds);
        // 循环判断供应商下是否有人员
        for(Integer id : ids){
            List<SysUserEntity> userList = sysUserService.list(new QueryWrapper<SysUserEntity>().eq("provider_id", id));
            if(userList.size() > 0){
                return R.error(500, "供应商下有人员，请先进行移除操作！");
            }
        }
        // 批量逻辑删除更新
        List<SysProviderEntity> providerList = new ArrayList<>();
        for(Integer id : ids){
            SysProviderEntity sysProviderEntity = new SysProviderEntity();
            sysProviderEntity.setProviderId(id);
            sysProviderEntity.setDeleteFlag(0);
            providerList.add(sysProviderEntity);
        }
        sysProviderService.updateBatchById(providerList);

        return R.ok();
    }

}
