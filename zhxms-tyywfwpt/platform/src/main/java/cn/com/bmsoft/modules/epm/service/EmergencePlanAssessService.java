package cn.com.bmsoft.modules.epm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanAssessEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 应急预案评审表
 *
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
public interface EmergencePlanAssessService extends IService<EmergencePlanAssessEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据ID逻辑删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    int deleteByIdLogical(Serializable id) throws Exception;

    /**
     * 根据ids逻辑批量删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    int deleteByIdLogicalBatch(String id) throws Exception;

    //应急预案评估列表
//    public List<EmergencePlanAssessEntity> emergencePlanAssessQueryList(Map<String, Object> queryParams);

    //应急预案评估列表
    PageUtils emergencePlanAssessQueryList(Map<String, Object> params);




}

