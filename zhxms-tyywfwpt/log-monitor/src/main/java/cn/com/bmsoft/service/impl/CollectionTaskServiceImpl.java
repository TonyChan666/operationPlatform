package cn.com.bmsoft.service.impl;

import cn.com.bmsoft.dao.CollectionTaskDao;
import cn.com.bmsoft.service.CollectionTaskService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;


import cn.com.bmsoft.entity.CollectionTask;



@Service("collectionTaskService")
public class CollectionTaskServiceImpl extends ServiceImpl<CollectionTaskDao, CollectionTask> implements CollectionTaskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectionTask> page = this.page(
                new Query<CollectionTask>().getPage(params),
                new QueryWrapper<CollectionTask>()
        );

        return new PageUtils(page);
    }

    @Override
    public int insert(CollectionTask entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public int update(CollectionTask entity) {
        return baseMapper.updateById(entity);
    }

    @Override
    public CollectionTask selectOne(String tableName) {
        QueryWrapper<CollectionTask> queryWrapper = new QueryWrapper<CollectionTask>()
                .eq("table_name",tableName);
        return baseMapper.selectOne(queryWrapper);
    }

}