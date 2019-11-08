package cn.com.bmsoft.modules.bsm.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

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

import cn.com.bmsoft.modules.bsm.entity.InstallEntity;
import cn.com.bmsoft.modules.bsm.service.InstallService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;

import javax.xml.crypto.Data;

/**
 * 业务服务管理-应用安装表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("bsm/install")
@Api("业务服务管理-应用安装表 API")
public class InstallController extends AbstractController {
    @Autowired
    private InstallService installService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("bsm:install:list")
    @ApiOperation(value = "获取install分页列表", notes = "install分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = installService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("bsm:install:info")
    @ApiOperation(value = "获取install对象详情", notes = "install对象详情", response = R.class)
    public R info(@PathVariable("id") int id){
		InstallEntity install = installService.getById(id);
        return R.ok().put("install", install);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("bsm:install:save")
    @ApiOperation(value = "保存install对象", notes = "install对象", response = R.class)
    public R save(@RequestBody InstallEntity install){
		installService.save(install);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("bsm:install:update")
    @ApiOperation(value = "修改install对象", notes = "install对象", response = R.class)
    public R update(@RequestBody InstallEntity install){
        Long user = getUser().getUserId();
        //install.setStatus("1");
        installService.updateById(install);
        InstallEntity install1 = new InstallEntity();
        //install1.setStatus("2");
        install1.setXgrid(user);
        install1.setXgsj(new Date());
        install1.setFwqid(install.getFwqid());
        //install.setAzfwq(install.getAzfwq());
        install.setAzwz(install.getAzwz());
        install.setBbh(install.getBbh());
        install1.setDkh(install.getDkh());
        installService.save(install1);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("bsm:install:delete")
    @ApiOperation(value = "删除install对象", notes = "install对象", response = R.class)
    public R delete(@RequestBody int[] ids){
		//installService.updateBatchByIds(ids);
        installService.removeById(ids);
        return R.ok();
    }
    /**
     * ywfwbm验证唯一性
     */
    @GetMapping("/ywfwbmYz/{ywfwbm}")
    //@RequiresPermissions("bsm:install:info")
    @ApiOperation(value = "获取install对象详情", notes = "install对象详情", response = R.class)
    public R ywfwbmYz(@PathVariable("ywfwbm") String ywfwbm){
        /*基础信息*/
        String ywfwbm1 = installService.ywfwbmYz(ywfwbm);
        if(!ywfwbm1.equals(null)) {
            return R.error("业务服务编码已存在");
        }
        return R.ok();
    }
}
