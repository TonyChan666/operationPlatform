package cn.com.bmsoft.modules.rm.controller;

import cn.com.bmsoft.modules.rm.entity.ServerEntity;
import cn.com.bmsoft.modules.rm.service.ServerService;
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
 * 服务器登记表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 *
 * @update zhangyibing zhangyibing@bmsoft.com.cn
 */
@RestController
@RequestMapping("rm/server")
@Api("服务器登记表 API")
public class ServerController extends AbstractController {
    @Autowired
    private ServerService serverService;
    @Autowired
    private SerialNumberService serialNumberService;
    /**
     * 列表
     */
    @GetMapping("/list")
//    @RequiresPermissions("rm:server:list")
    @ApiOperation(value = "获取server分页列表", notes = "server分页列表", response = R.class)
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serverService.queryPage(params,getUser());
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
//    @RequiresPermissions("rm:server:info")
    @ApiOperation(value = "获取server对象详情", notes = "server对象详情", response = R.class)
    public R info(@PathVariable("id") String id){
        ServerEntity server = serverService.getServerEntity(id,this.getUser());
        return R.ok().put("server", server);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//    @RequiresPermissions("rm:server:save")
    @ApiOperation(value = "保存server对象", notes = "server对象", response = R.class)
    public R save(@RequestBody ServerEntity server){
//        if(server.getFwqbm() == null || server.getFwqbm().length() != 5) {
//            return R.error("请输入五位数服务器编码");
//        }
        //服务器编码等于"FWQ"+五位编码
        R r = serialNumberService.generator(RmParams.CODE_PREFIX.get(RmParams.SERVER_ENTITY_NAME));
        server.setFwqbm(r.get(RmParams.SERIAL_NUMBER)+"");
        ValidatorUtils.validateEntity(server, AddGroup.class);
//        server.setFwqbm(RmParams.CODE_PREFIX.get(RmParams.SERVER_ENTITY_NAME) + server.getFwqbm());
        //创建时间
        server.setCjsj(new Date());
        //创建人
        server.setCjrid(getUserId().intValue());
        //保存
		serverService.saveServer(server);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
//    @RequiresPermissions("rm:server:update")
    @ApiOperation(value = "修改server对象", notes = "server对象", response = R.class)
    public R update(@RequestBody ServerEntity server){
        //设置最后修改时间
        server.setXgsj(new Date());
        //设置最后修改人
        server.setXgrid(getUserId().intValue());
        //修改
		serverService.updateServer(server);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
//    @RequiresPermissions("rm:server:delete")
    @ApiOperation(value = "删除server对象", notes = "server对象", response = R.class)
    public R delete(@RequestBody Integer[] ids){
        //只是逻辑删除，不在页面上显示，修改deleteFlag字段值为1
        if(ids == null || ids.length == 0){
            return R.error("请指定要删除的资源");
        }
        List<ServerEntity> servers = new ArrayList<ServerEntity>();
        for(Integer id : ids){
            ServerEntity server = new ServerEntity();
            server.setId(id);
            server.setDeleteFlag("1");
            servers.add(server);
        }
        serverService.updateBatchById(servers);
        return R.ok();
    }

    @PostMapping("/updateStatus")
//    @RequiresPermissions("rm:server:updateStatus")
    @ApiOperation(value = "更新server对象的sfyx状态", notes = "server对象", response = R.class)
    public R updateStatus(@RequestBody ServerEntity server){
        Integer id = server.getId();
        if(StringUtil.isEmpty(id+"")){
            return R.error("请指定要更新的资源");
        }
        server.setXgrid(getUserId().intValue());
        server.setXgsj(new Date());
        serverService.updateById(server);
        return R.ok();
    }

}
