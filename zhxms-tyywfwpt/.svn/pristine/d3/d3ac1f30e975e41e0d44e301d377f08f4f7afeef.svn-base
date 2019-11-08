package cn.com.bmsoft.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.dao.LogCollectionDao;
import cn.com.bmsoft.entity.LogCollection;
import cn.com.bmsoft.service.LogCollectionService;


@Service("logCollectionService")
public class LogCollectionServiceImpl extends ServiceImpl<LogCollectionDao, LogCollection> implements LogCollectionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogCollection> page = this.page(
                new Query<LogCollection>().getPage(params),
                new QueryWrapper<LogCollection>()
        );

        return new PageUtils(page);
    }

    @Override
    public int insert(LogCollection entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public LogCollection selectById(int id) {
        return baseMapper.selectById(id);
    }


}