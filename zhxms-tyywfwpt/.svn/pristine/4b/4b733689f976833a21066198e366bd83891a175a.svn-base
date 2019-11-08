package cn.com.bmsoft.modules.sys.service.impl;

import cn.com.bmsoft.modules.sys.dao.SysUserDeptDao;
import cn.com.bmsoft.modules.sys.entity.SysUserDeptEntity;
import cn.com.bmsoft.modules.sys.service.SysUserDeptService;
import cn.com.bmsoft.utils.MapUtils;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysUserDeptService")
public class SysUserDeptServiceImpl extends ServiceImpl<SysUserDeptDao, SysUserDeptEntity> implements SysUserDeptService {

    @Autowired
    private SysUserDeptDao sysUserDeptDao;

    @Override
    public void saveOrUpdate(Integer userId, List<Integer> deptIds) {
        //先删除用户与业务组关系
        this.removeByMap(new MapUtils().put("user_id", userId));

        if(deptIds == null || deptIds.size() == 0) {
            return ;
        }

        List<SysUserDeptEntity> sysUserDeptEntities = new ArrayList<>();
        //保存用户与角色关系
        for(Integer deptId : deptIds){
            SysUserDeptEntity sysUserDeptEntity = new SysUserDeptEntity();
            sysUserDeptEntity.setUserId(userId);
            sysUserDeptEntity.setDeptId(deptId);
            sysUserDeptEntities.add(sysUserDeptEntity);
        }
        sysUserDeptDao.saves(sysUserDeptEntities);
    }

    @Override
    public List<Integer> queryDeptIdList(Long userId) {
        return baseMapper.queryDeptIdList(userId);
    }

    @Override
    public String queryDeptName(Long userId) {
        List<String> deptNameList = sysUserDeptDao.queryDeptNameList(userId);
        String result = "";
        if(deptNameList == null || deptNameList.size() == 0){
            return result;
        }
        for(String deptName : deptNameList){
            result += deptName+",";
        }
        return result;
    }

    @Override
    public Map<Integer, String> queryDeptNameByUserIds(List<Long> userIds) {
        Map<Integer, String> result = new HashMap<>();
        List<SysUserDeptEntity> deptNames = sysUserDeptDao.queryDeptNameByUserIds(userIds);
        for(SysUserDeptEntity userDep : deptNames) {
            if(StringUtil.isEmpty(userDep.getDeptName())){
                continue;
            }
            String currDeptName = result.get(userDep.getUserId());
            if(StringUtil.isEmpty(currDeptName)){
                result.put(userDep.getUserId(), userDep.getDeptName());
            } else {
                result.put(userDep.getUserId(), currDeptName + "," + userDep.getDeptName());
            }
        }
        return result;
    }
}
