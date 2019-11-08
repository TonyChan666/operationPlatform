package cn.com.bmsoft.modules.sys.dao;

import cn.com.bmsoft.modules.sys.entity.SysUserDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDeptDao extends BaseMapper<SysUserDeptEntity> {

    void saves(List<SysUserDeptEntity> sysUserDeptEntities);

    /**
     * 根据用户ID，获取业务组ID列表
     */
    List<Integer> queryDeptIdList(Long userId);

    List<String> queryDeptNameList(Long userId);

    List<SysUserDeptEntity> queryDeptNameByUserIds(List<Long> ids);
}
