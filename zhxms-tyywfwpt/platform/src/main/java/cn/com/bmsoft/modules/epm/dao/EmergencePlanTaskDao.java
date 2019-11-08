package cn.com.bmsoft.modules.epm.dao;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanTaskEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 应急预案任务表
 * 
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Mapper
public interface EmergencePlanTaskDao extends BaseMapper<EmergencePlanTaskEntity> {

    public EmergencePlanTaskEntity getEmergencePlanTask(@Param("yjyaid") String yjyaid,@Param("updateUser") String updateUser,@Param("rwmc")String rwmc);

    public List<EmergencePlanTaskEntity> getEmergencePlanTaskList(@Param("params") Map<String,Object> params);
}
