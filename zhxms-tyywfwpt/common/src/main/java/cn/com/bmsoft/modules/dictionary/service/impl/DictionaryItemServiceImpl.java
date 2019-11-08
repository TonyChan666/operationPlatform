package cn.com.bmsoft.modules.dictionary.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.dictionary.dao.DictionaryItemDao;
import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryItemService;


@Service("dictionaryItemService")
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemDao, DictionaryItemEntity> implements DictionaryItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictionaryItemEntity> page = this.page(
                new Query<DictionaryItemEntity>().getPage(params),
                new QueryWrapper<DictionaryItemEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<DictionaryItemEntity> queryByDictCode(String dictCode) {

        return baseMapper.queryByDictCode(dictCode);
    }

}