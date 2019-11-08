package cn.com.bmsoft.modules.dictionary.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.dictionary.dao.DictionaryDao;
import cn.com.bmsoft.modules.dictionary.entity.DictionaryEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryService;


@Service("dictionaryService")
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, DictionaryEntity> implements DictionaryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String code = (String)params.get("code");
        String name = (String)params.get("name");
        String status = (String)params.get("status");
        IPage<DictionaryEntity> page = this.page(
                new Query<DictionaryEntity>().getPage(params),
                new QueryWrapper<DictionaryEntity>().
                        like(StringUtils.isNotBlank(code), "code", code).
                        like(StringUtils.isNotBlank(name), "name", name).
                        eq(StringUtils.isNotBlank(status), "status", status)
        );

        return new PageUtils(page);
    }

    @Override
    public DictionaryEntity queryByCode(String code) {
        return baseMapper.queryByCode(code);
    }

}