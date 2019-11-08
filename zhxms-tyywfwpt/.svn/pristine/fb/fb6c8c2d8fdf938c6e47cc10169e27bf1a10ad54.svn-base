package cn.com.bmsoft.modules.sys.controller;
import cn.com.bmsoft.modules.sys.entity.SysDeptEntity;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.service.SysDeptService;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.validator.ValidatorUtils;
import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 组织机构表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-26
 */
@RestController
@RequestMapping("sys/dept")
@Api("组织机构表 API")
public class SysDeptController extends AbstractController{

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysUserService sysUserService;


    /**
     * 信息
     */
    @GetMapping("/info/{deptId}")
    // @RequiresPermissions("sys:dept:info")
    @ApiOperation(value = "获取dept对象详情", notes = "dept对象详情", response = R.class)
    public R info(@PathVariable("deptId") Integer deptId){
		SysDeptEntity sysDept = sysDeptService.getById(deptId);
        return R.ok().put("sysDept", sysDept);
    }

    /**
     * 组织机构树
     * @param
     * @return List<SysDeptEntity>
     */
    @GetMapping("/tree")
    public R tree(){
        List<SysDeptEntity> nodes = sysDeptService.listByAll(1);
        ArrayList<SysDeptEntity> rootNode = new ArrayList<>();
        for(SysDeptEntity node : nodes){
            if(node.getDeptId() == 1){
                rootNode.add(node);
            }
        }
        for(SysDeptEntity node : rootNode){
            List<SysDeptEntity> child = getChild(node.getDeptId(), nodes);
            node.setChildren(child);
        }
        return R.ok().put("sysDept", rootNode);
    }

    /**
     * 获取组织树递归方法
     * @param id
     * @param allNode
     * @return
     */
    private List<SysDeptEntity> getChild(int id, List<SysDeptEntity> allNode) {
        //存放子菜单的集合
        ArrayList<SysDeptEntity> listChild = new ArrayList<>();
        for (SysDeptEntity node : allNode) {
            if (node.getDeptParentId() == id) {
                listChild.add(node);
            }
        }
        //递归：
        for (SysDeptEntity node : listChild) {
            node.setChildren(getChild(node.getDeptId(), allNode));
        }
        if (listChild.size() == 0) {
            return null;
        }
        return listChild;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("sys:dept:save")
    @ApiOperation(value = "保存dept对象", notes = "dept对象", response = R.class)
    public R save(@RequestBody SysDeptEntity sysDept){
        ValidatorUtils.validateEntity(sysDept, AddGroup.class);
        sysDept.setCreateUserId(getUserId());
        sysDept.setCreateTime(new Date());
		sysDeptService.save(sysDept);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    // @RequiresPermissions("sys:dept:update")
    @ApiOperation(value = "修改dept对象", notes = "dept对象", response = R.class)
    public R update(@RequestBody SysDeptEntity sysDept){
        sysDept.setUpdateTime(new Date());
        sysDept.setUpdateUserId(getUserId());
		sysDeptService.updateById(sysDept);
        return R.ok();
    }

    /**
     * 删除
     */
    @GetMapping("/delete/{deptId}")
    // @RequiresPermissions("sys:dept:delete")
    @ApiOperation(value = "删除dept对象", notes = "dept对象", response = R.class)
    public R delete(@PathVariable Integer deptId){

        // 判断是否有子节点
		List<SysDeptEntity> deptList = sysDeptService.list(new QueryWrapper<SysDeptEntity>()
                .eq(deptId != null, "dept_parent_id", deptId)
                .isNull("delete_flag"));
		if(deptList.size() >0 ){
            return R.error(500,"该机构下有其它机构，请先删除下面的机构信息!");
        }

        // 判断服务组下是否有人员
        List<SysUserEntity> userList = sysUserService.list(new QueryWrapper<SysUserEntity>().inSql("user_id","select user_id from sys_user_dept where dept_id = '"+deptId+"'"));
        if(userList.size() > 0){
            return R.error(500, "服务组下有人员，请先进行移除操作！");
        }

        // 逻辑删除
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        sysDeptEntity.setDeleteFlag(0);
        sysDeptEntity.setUpdateTime(new Date());
        sysDeptEntity.setUpdateUserId(getUserId());
        sysDeptService.update(sysDeptEntity, new QueryWrapper<SysDeptEntity>().eq("dept_id", deptId));

        return R.ok();
    }

    /**
     * 列表(没分页）
     */
    @GetMapping("/deptList")
    // @RequiresPermissions("sys:dept:list")
    @ApiOperation(value = "获取列表", notes = "dept列表", response = R.class)
    public R deptList(@RequestParam Map<String, Object> params){
        List<SysDeptEntity> list = (List<SysDeptEntity>) sysDeptService.listByMap(params);
        return R.ok().put("list", list);
    }

}
