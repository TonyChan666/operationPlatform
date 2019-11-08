package cn.com.bmsoft.modules.sys.service;

import cn.com.bmsoft.modules.sys.entity.SysUserDeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface SysUserDeptService extends IService<SysUserDeptEntity> {

    public void saveOrUpdate(Integer userId, List<Integer> deptIds);

    public List<Integer> queryDeptIdList(Long userId);

    public String queryDeptName(Long userId);

    public Map<Integer, String> queryDeptNameByUserIds(List<Long> userIds);
}
