package cn.com.bmsoft.modules.am.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.am.entity.ThresholdGradeEntity;

import java.util.List;
import java.util.Map;

/**
 * 阈值等级表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
public interface ThresholdGradeService extends IService<ThresholdGradeEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

