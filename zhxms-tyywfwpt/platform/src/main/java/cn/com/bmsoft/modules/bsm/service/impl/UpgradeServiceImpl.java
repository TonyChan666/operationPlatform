package cn.com.bmsoft.modules.bsm.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.bsm.dao.UpgradeDao;
import cn.com.bmsoft.modules.bsm.entity.UpgradeEntity;
import cn.com.bmsoft.modules.bsm.service.UpgradeService;


@Service("upgradeService")
public class UpgradeServiceImpl extends ServiceImpl<UpgradeDao, UpgradeEntity> implements UpgradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UpgradeEntity> page = this.page(
                new Query<UpgradeEntity>().getPage(params),
                new QueryWrapper<UpgradeEntity>()
        );

        return new PageUtils(page);
    }

}