package cn.com.bmsoft.modules.bm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.bm.entity.StrategyEntity;

import java.util.List;
import java.util.Map;

/**
 * 备份管理-备份策略表
 *
 * @author sufang  luyuwei@bmsoft.com.cn
 * @since 2019-09-23
 */
public interface StrategyService extends IService<StrategyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean updateBatchByIds(Long[] ids);

    StrategyEntity getByStrategyId(int id);
}

