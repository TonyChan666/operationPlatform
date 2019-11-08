package cn.com.bmsoft.modules.epm.dao;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntity;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntityExtend;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 应急预案表
 * 
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Mapper
public interface EmergencePlanDao extends BaseMapper<EmergencePlanEntityExtend> {

    //待评审列表
    public List<EmergencePlanEntityExtend> unReviewQueryList(Map<String,Object> queryParams);

    //应急预案列表
    public List<EmergencePlanEntityExtend> emergencePlanQueryList(Map<String,Object> queryParams);


    public EmergencePlanEntityExtend findEmergencePlanById(String id);

    public int updateByIds(@Param("ids") String ids[]);


    IPage<EmergencePlanEntityExtend> selectPage1(IPage<EmergencePlanEntityExtend> page, @Param(Constants.WRAPPER) Wrapper<EmergencePlanEntityExtend> queryWrapper);

    //应急预案列表
    IPage<EmergencePlanEntityExtend> emergencePlanQueryList(IPage<EmergencePlanEntityExtend> page,@Param("params") Map<String, Object> params);

    //待评审列表
    IPage<EmergencePlanEntityExtend> unReviewQueryList(IPage<EmergencePlanEntityExtend> page, @Param("params") Map<String, Object> params);

    //应急预案首页待办列表
    IPage<EmergencePlanEntityExtend> unHandleList(IPage<EmergencePlanEntityExtend> page, @Param("params") Map<String, Object> params);

    public int updateById1(EmergencePlanEntity emergencePlanEntity);
}
