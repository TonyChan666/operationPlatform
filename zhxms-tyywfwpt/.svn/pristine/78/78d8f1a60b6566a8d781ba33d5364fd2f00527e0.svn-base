package cn.com.bmsoft.modules.am.service;

import cn.com.bmsoft.modules.am.vo.AmStrategyVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.am.entity.AmStrategyEntity;

import java.util.List;
import java.util.Map;

/**
 * 告警策略表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
public interface AmStrategyService extends IService<AmStrategyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAmStrategyEntity(AmStrategyVo strategy);

    void updateAmStrategyById(AmStrategyVo strategy);

    void removeStrategyByIds(List<Long> asList);

    PageUtils queryItemPage(Map<String, Object> params);

}

