package cn.com.bmsoft.modules.epm.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.epm.dao.EmergencePlanReviewUrgeDao;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanReviewUrgeEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanReviewUrgeService;


@Service("emergencePlanReviewUrgeService")
public class EmergencePlanReviewUrgeServiceImpl extends ServiceImpl<EmergencePlanReviewUrgeDao, EmergencePlanReviewUrgeEntity> implements EmergencePlanReviewUrgeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmergencePlanReviewUrgeEntity> page = this.page(
                new Query<EmergencePlanReviewUrgeEntity>().getPage(params),
                new QueryWrapper<EmergencePlanReviewUrgeEntity>()
        );

        return new PageUtils(page);
    }

}