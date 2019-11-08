package cn.com.bmsoft.modules.am.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.am.dao.ThresholdGradeDao;
import cn.com.bmsoft.modules.am.entity.ThresholdGradeEntity;
import cn.com.bmsoft.modules.am.service.ThresholdGradeService;


@Service("thresholdGradeService")
public class ThresholdGradeServiceImpl extends ServiceImpl<ThresholdGradeDao, ThresholdGradeEntity> implements ThresholdGradeService {

    @Autowired
    private ThresholdGradeDao thresholdGradeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThresholdGradeEntity> page = this.page(
                new Query<ThresholdGradeEntity>().getPage(params),
                new QueryWrapper<ThresholdGradeEntity>().isNull("delete_flag")
        );

        return new PageUtils(page);
    }


}