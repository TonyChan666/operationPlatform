package cn.com.bmsoft.modules.am.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.am.entity.ModelEntity;

import java.util.List;
import java.util.Map;

/**
 * 告警模板表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
public interface ModelService extends IService<ModelEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void removeModelEntityByIds(List<Long> asList);

}

