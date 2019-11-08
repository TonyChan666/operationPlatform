package cn.com.bmsoft.modules.am.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.am.dao.AppRunningDao;
import cn.com.bmsoft.modules.am.entity.AppRunningEntity;
import cn.com.bmsoft.modules.am.service.AppRunningService;


@Service("appRunningService")
public class AppRunningServiceImpl extends ServiceImpl<AppRunningDao, AppRunningEntity> implements AppRunningService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppRunningEntity> page = this.page(
                new Query<AppRunningEntity>().getPage(params),
                new QueryWrapper<AppRunningEntity>().isNull("delete_flag")
        );

        return new PageUtils(page);
    }

}