package cn.com.bmsoft.modules.epm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.epm.dao.EmergencePlanReviewDao;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanReviewEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanReviewService;


@Service("emergencePlanReviewService")
public class EmergencePlanReviewServiceImpl extends ServiceImpl<EmergencePlanReviewDao, EmergencePlanReviewEntity> implements EmergencePlanReviewService {
    @Autowired
    EmergencePlanReviewDao emergencePlanReviewDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmergencePlanReviewEntity> page = this.page(
                new Query<EmergencePlanReviewEntity>().getPage(params),
                new QueryWrapper<EmergencePlanReviewEntity>()
        );

        return new PageUtils(page);
    }


    public int deleteByIdLogical(Serializable id) throws Exception {
        EmergencePlanReviewEntity emergencePlanReview = new EmergencePlanReviewEntity();
        emergencePlanReview.setId(id.toString());
        emergencePlanReview.setStatus("0");
        return emergencePlanReviewDao.updateById(emergencePlanReview);
    }


    public int deleteByIdLogicalBatch(String id) throws Exception {
        EmergencePlanReviewEntity emergencePlanReview = new EmergencePlanReviewEntity();
        int result = 0;
        String[] ids = id.split(",");
        for (int i = 0, len = ids.length; i < len; i++) {
            emergencePlanReview.setId(ids[i]);
            emergencePlanReview.setStatus("0");
            result = emergencePlanReviewDao.updateById(emergencePlanReview);
        }

        return result;
    }


//    @Override
//    public List<EmergencePlanReviewEntity> emergencePlanReviewQueryList(Map<String, Object> queryParams) {
//        List<EmergencePlanReviewEntity> list=null;
//        try {
//            list=emergencePlanReviewDao.emergencePlanReviewQueryList(queryParams);
//        } catch (Exception e) {
//            throw e;
//        }
//        return list;
//    }



    @Override
    public PageUtils emergencePlanReviewQueryList(Map<String, Object> params) {
        IPage<EmergencePlanReviewEntity> page = new Query<EmergencePlanReviewEntity>().getPage(params);
        return new PageUtils(baseMapper.emergencePlanReviewQueryList(page, params));
    }

    @Override
    public List<EmergencePlanReviewEntity> emergencePlanReviewList(Map<String, Object> params) {
        return baseMapper.emergencePlanReviewQueryList1(params);
    }

}

