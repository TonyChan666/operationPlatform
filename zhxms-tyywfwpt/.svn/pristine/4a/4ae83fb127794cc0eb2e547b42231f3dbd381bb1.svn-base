package cn.com.bmsoft.modules.epm.service.impl;

import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntityExtend;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;

import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.Query;
import cn.com.bmsoft.modules.epm.dao.EmergencePlanDao;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanService;

import cn.com.bmsoft.utils.PageUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service("emergencePlanService")
public class EmergencePlanServiceImpl extends ServiceImpl<EmergencePlanDao, EmergencePlanEntityExtend> implements EmergencePlanService {

    @Autowired
    private EmergencePlanDao emergencePlanDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmergencePlanEntityExtend> page = this.page(
                new Query<EmergencePlanEntityExtend>().getPage(params),
                new QueryWrapper<EmergencePlanEntityExtend>()
        );

        return new PageUtils(page);
    }

    @Override
    public int deleteByIdLogical(Serializable id) throws Exception {
        EmergencePlanEntityExtend emergencePlanEntity = new EmergencePlanEntityExtend();
        emergencePlanEntity.setId(id.toString());
        emergencePlanEntity.setStatus("0");
        return emergencePlanDao.updateById(emergencePlanEntity);
    }

    @Override
    public int deleteByIdLogicalBatch(String ids[]) throws Exception {
        int result=emergencePlanDao.updateByIds(ids);
        return result;
    }

//    @Override
//    public List<EmergencePlanEntityExtend> unReviewQueryList(Map<String, Object> queryParams) {
//
//        List<EmergencePlanEntityExtend> list = null;
//        try {
//            list = emergencePlanDao.unReviewQueryList(queryParams);
//        } catch (Exception e) {
//            throw e;
//        }
//        return list;
//    }

//    @Override
//    public List<EmergencePlanEntityExtend> emergencePlanQueryList(Map<String, Object> queryParams) {
//        List<EmergencePlanEntityExtend> list = null;
//        try {
//            list = emergencePlanDao.emergencePlanQueryList(queryParams);
//        } catch (Exception e) {
//            throw e;
//        }
//        return list;
//    }

    @Override
    public EmergencePlanEntityExtend findEmergencePlanById(String id) {
        return emergencePlanDao.findEmergencePlanById(id);
    }


    @Override
    public IPage<EmergencePlanEntityExtend> page1(IPage<EmergencePlanEntityExtend> page, Wrapper<EmergencePlanEntityExtend> queryWrapper) {
        return emergencePlanDao.selectPage1(page,queryWrapper);
        //return  null;
    }

    @Override
    public IPage<EmergencePlanEntityExtend> page1(IPage<EmergencePlanEntityExtend> page) {
        return page1(page, Wrappers.emptyWrapper());
    }



    @Override
    public PageUtils emergencePlanQueryList(Map<String, Object> params) {
        IPage<EmergencePlanEntityExtend> page = new Query<EmergencePlanEntityExtend>().getPage(params);
        return new PageUtils(baseMapper.emergencePlanQueryList(page, params));
    }

    @Override
    public PageUtils unReviewQueryList(Map<String, Object> params) {
        IPage<EmergencePlanEntityExtend> page = new Query<EmergencePlanEntityExtend>().getPage(params);
        return new PageUtils(baseMapper.unReviewQueryList(page, params));
    }

    @Override
    public PageUtils unHandleList(Map<String, Object> params) {
        IPage<EmergencePlanEntityExtend> page = new Query<EmergencePlanEntityExtend>().getPage(params);
        return new PageUtils(baseMapper.unHandleList(page, params));
    }

    @Override
    public int updateById1(EmergencePlanEntity entity) {
        return emergencePlanDao.updateById1(entity);
    }


}