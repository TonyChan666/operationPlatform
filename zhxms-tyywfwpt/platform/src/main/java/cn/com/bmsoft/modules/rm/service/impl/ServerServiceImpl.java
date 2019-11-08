package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.ServerDao;
import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;
import cn.com.bmsoft.modules.rm.entity.ServerEntity;
import cn.com.bmsoft.modules.rm.service.RmResourceDeptService;
import cn.com.bmsoft.modules.rm.service.ServerService;
import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.utils.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("serverService")
public class ServerServiceImpl extends ServiceImpl<ServerDao, ServerEntity> implements ServerService {

    @Autowired
    private ServerDao serverDao;

    @Autowired
    private RmResourceDeptService rmResourceDeptService;

//    @Autowired
//    private SysUserRoleService sysUserRoleService;

    final static String[] SEARCH_FIELDS = {"sbmc","sbxlh","ip","sbxh","ywjz","ywry","wllb","sfyx"};

    @Override
    public PageUtils queryPage(Map<String, Object> params, SysUserEntity user) {
        IPage<ServerEntity> page = new Query<ServerEntity>().getPage(params);
        Map<String,String> roleSearchMap = this.getServerSearchMap(user);
        if(roleSearchMap != null) {
            params.putAll(roleSearchMap);
        } else {
            return new PageUtils(page);
        }
        return new PageUtils(serverDao.getServerList(page,params));
    }

    @Override
    public void removeToRecoveryByIds(List<Integer> ids) {
        serverDao.removeToRecoveryByIds(ids);
    }

    @Override
    public ServerEntity getServerEntity(String id,SysUserEntity user) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        Map<String,String> roleSearchMap = this.getServerSearchMap(user);
        if(roleSearchMap != null) {
            params.putAll(roleSearchMap);
        } else {
            return null;
        }
        ServerEntity serverEntity = serverDao.getServer(params);
        if(serverEntity == null){
            return null;
        }
        List<Map<String, Object>> resourceDeptNames = rmResourceDeptService.getResourceDeptNames(serverEntity.getId() + "", RmParams.RM_TABLE_NAME.get(RmParams.SERVER_ENTITY_NAME));
        List<Integer> ywzids = new ArrayList<>();
        String ywzmc = "";
        for (Map<String, Object> map : resourceDeptNames) {
            ywzids.add(Integer.parseInt(map.get("DEPT_ID")+""));
            ywzmc = (StringUtil.isEmpty(ywzmc)?"":(ywzmc+",")) + (StringUtil.isEmpty(map.get("DEPT_NAME")+"")?"":map.get("DEPT_NAME"));
        }
        serverEntity.setYwzid(ywzids);
        serverEntity.setYwzmc(ywzmc);
        return serverEntity;
    }

    public Map<String,String> getServerSearchMap(SysUserEntity user){
        Long userId = user.getUserId();
//        List<Long> roles = sysUserRoleService.queryRoleIdList(userId);

        Map<String,String> result = new HashMap<String,String>();
        if(user.getRoleId() == Constant.ADMIN_ROLE){
            //若是系统超级管理员，则可以查出所有服务器列表，不需要筛选条件
        } else if(user.getRoleId() == Constant.OPERATIONS_GROUP_LEADER_ROLE){
            //若是运维组长，则可以查出对应业务组及供应商的服务器,以及他自己的服务器
            result.put("userId",userId+"");//此处传入userId,根据userid到sys_user_dept表中查询user对应的deptid
            result.put("gys", user.getProviderId()+"");
            result.put("roleywry", userId+"");
        } else if(user.getRoleId() == Constant.GENERAL_OPERATIONS_PERSONNEL_ROLE){
            //若是普通运维人员，只可查出当前运维人员为自己的服务器列表
            result.put("ownywry", userId+"");
        } else {
            //无权查询的人员
            return null;
        }
        return result;

    }

    public R updateServer(ServerEntity serverEntity) {
//        if(serverEntity.getFwqbm() != null ) {
//            if(serverEntity.getFwqbm().length() != 5) {
//                return R.error("请输入五位数容器编码");
//            }else {
//                //容器编码等于"FWQ"+五位编码
//                serverEntity.setFwqbm(RmParams.CODE_PREFIX.get(RmParams.SERVER_ENTITY_NAME) + serverEntity.getFwqbm());
//            }
//        }
        String tableName = RmParams.RM_TABLE_NAME.get(RmParams.SERVER_ENTITY_NAME);
        List<Integer> ywzids = serverEntity.getYwzid();
        List<RmResourceDeptEntity> rmResourceDeptEntitieList = new ArrayList<>();
        rmResourceDeptService.removeResourceDepts(serverEntity.getId()+"", tableName);
        if(!ywzids.isEmpty()){
            for(Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDept = new RmResourceDeptEntity();
                rmResourceDept.setYwzid(ywzid);
                rmResourceDept.setZyid(serverEntity.getId());
                rmResourceDept.setZybm(tableName);
                rmResourceDeptEntitieList.add(rmResourceDept);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntitieList);
        }
        this.updateById(serverEntity);
        return R.ok();
    }

    @Override
    @Transactional
    public void saveServer(ServerEntity server) {
        this.save(server);
        List<Integer> ywzids = server.getYwzid();
        if(!ywzids.isEmpty()) {
            List<RmResourceDeptEntity> rmResourceDeptEntities = new ArrayList<>();
            for (Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDeptEntity = new RmResourceDeptEntity();
                rmResourceDeptEntity.setZyid(server.getId());
                rmResourceDeptEntity.setYwzid(ywzid);
                rmResourceDeptEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.SERVER_ENTITY_NAME));
                rmResourceDeptEntities.add(rmResourceDeptEntity);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntities);
        }
    }

}