package cn.com.bmsoft.modules.epm.service;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntityExtend;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 应急预案表
 *
 * @author wgl  wuguoliang@bmsoft.com.cn
 * @since 2019-09-23
 */
public interface EmergencePlanService extends IService<EmergencePlanEntityExtend> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * 根据ID逻辑删除
     * @param id
     * @return
     * @throws Exception
     */
    int deleteByIdLogical(Serializable id) throws Exception;

    /**
     * 根据ids逻辑批量删除
     * @param
     * @return
     * @throws Exception
     */
    int deleteByIdLogicalBatch(String ids[]) throws Exception;


    /**
     * 获取待评审列表
     * @param
     * @return
     */
//    public List<EmergencePlanEntityExtend> unReviewQueryList(Map<String,Object> queryParams);

    //应急预案列表
//    public List<EmergencePlanEntityExtend> emergencePlanQueryList(Map<String,Object> queryParams);

    public EmergencePlanEntityExtend findEmergencePlanById(String id);


    //应急预案列表
    PageUtils emergencePlanQueryList(Map<String, Object> params);

    //获取待评审列表
    PageUtils unReviewQueryList(Map<String, Object> params);

    //应急预案首页待办列表
    PageUtils unHandleList(Map<String, Object> params);



    int updateById1(EmergencePlanEntity entity);





    /**
     * 翻页查询
     *
     * @param page         翻页对象
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    IPage<EmergencePlanEntityExtend> page1(IPage<EmergencePlanEntityExtend> page, Wrapper<EmergencePlanEntityExtend> queryWrapper);

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     * @see Wrappers#emptyWrapper()
     */
    default IPage<EmergencePlanEntityExtend> page1(IPage<EmergencePlanEntityExtend> page) {
        return page1(page, Wrappers.emptyWrapper());
    }

}

