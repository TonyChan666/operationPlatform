package cn.com.bmsoft.modules.epm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanTaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 应急预案任务表
 *
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
public interface EmergencePlanTaskService extends IService<EmergencePlanTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public EmergencePlanTaskEntity getEmergencePlanTask(String yjyaid,String updateUser,String rwmc);

    public List<EmergencePlanTaskEntity> getEmergencePlanTaskList(Map<String,Object> params);
}

