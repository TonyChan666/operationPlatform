package cn.com.bmsoft.modules.epm.dao;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanReviewEntity;
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
public interface EmergencePlanReviewDao extends BaseMapper<EmergencePlanReviewEntity> {
    //应急预案评审列表
    IPage<EmergencePlanReviewEntity> emergencePlanReviewQueryList(IPage<EmergencePlanReviewEntity> page,@Param("params") Map<String, Object> params);


    //应急预案评审列表不分页
    public List<EmergencePlanReviewEntity> emergencePlanReviewQueryList1(@Param("params") Map<String, Object> params);



}
