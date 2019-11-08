package cn.com.bmsoft.modules.epm.dao;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanAssessEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 应急预案评审表
 * 
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Mapper
public interface EmergencePlanAssessDao extends BaseMapper<EmergencePlanAssessEntity> {
    //应急预案评估列表
    //public List<EmergencePlanAssessEntity> emergencePlanAssessQueryList(Map<String, Object> queryParams);

    //应急预案评估列表
    IPage<EmergencePlanAssessEntity> emergencePlanAssessQueryList(IPage<EmergencePlanAssessEntity> page,@Param("params") Map<String, Object> params);
}
