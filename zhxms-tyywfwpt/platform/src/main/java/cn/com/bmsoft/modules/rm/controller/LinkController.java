package cn.com.bmsoft.modules.rm.controller;

import cn.com.bmsoft.modules.rm.entity.LinkDeviceEntity;
import cn.com.bmsoft.modules.rm.entity.LinkEntity;
import cn.com.bmsoft.modules.rm.service.LinkService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
* @auther zhangyibing  zhangyibing@bmsoft.com.cn
* @Date 2019/9/27
* @desription 链路登记表
**/
@RestController
@RequestMapping("rm/link")
@Api("链路登记表 API")
public class LinkController extends AbstractController {

    @Autowired
    private LinkService linkService;

    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("rm:link:list")
    @ApiOperation(value = "获取link分页列表", notes = "link分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = linkService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("rm:link:info")
    @ApiOperation(value = "获取link对象详情", notes = "link对象详情", response = R.class)
    public R info(@PathVariable("id") Integer id){
        Map<String,Object> result = new HashMap<String,Object>();
        LinkEntity link = linkService.getLink(id);
        if(link == null){
            return R.ok().put("link", result);
        }
        List<LinkDeviceEntity> devices = linkService.getDevicesBylinkId(id);
        link.setLinkDevices(devices);
        return R.ok().put("link", link);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("rm:link:save")
    @ApiOperation(value = "保存link对象,以及linkDevice对象", notes = "link对象", response = R.class)
    public R save(@RequestBody Map<String, Object> params){
        return linkService.saveLink(params,getUser());
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("rm:link:update")
    @ApiOperation(value = "修改link对象", notes = "link对象", response = R.class)
    public R update(@RequestBody LinkEntity link){
        link.setXgsj(new Date());
        link.setXgrid(getUserId().intValue());
        return linkService.updateLink(link);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("rm:link:delete")
    @ApiOperation(value = "删除link对象", notes = "link对象", response = R.class)
    public R delete(@RequestBody Integer[] ids){
        if(ids == null || ids.length == 0){
            return R.error("请指定要删除的资源");
        }
        List<LinkEntity> entities = new ArrayList<LinkEntity>();
        for(Integer id : ids){
            LinkEntity entity = new LinkEntity();
            entity.setId(id);
            entity.setDeleteFlag("1");
            entities.add(entity);
        }
        linkService.updateBatchById(entities);
        return R.ok();
    }

    @PostMapping("/updateStatus")
//    @RequiresPermissions("rm:link:updateStatus")
    @ApiOperation(value = "更新link对象的sfyx状态", notes = "link对象", response = R.class)
    public R updateStatus(@RequestBody LinkEntity link){
        Integer id = link.getId();
        if(StringUtil.isEmpty(id+"")){
            return R.error("请指定要更新的资源");
        }
        link.setXgrid(getUserId().intValue());
        link.setXgsj(new Date());
        linkService.updateById(link);
        return R.ok();
    }

}
