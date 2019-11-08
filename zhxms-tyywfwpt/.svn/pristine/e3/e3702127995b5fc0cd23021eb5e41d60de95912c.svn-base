package cn.com.bmsoft.service.impl;

import cn.com.bmsoft.dao.LogFieldDictionaryDao;
import cn.com.bmsoft.entity.LogFieldDictionary;
import cn.com.bmsoft.service.LogFieldDictionaryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogFieldDictionaryServiceImpl extends ServiceImpl<LogFieldDictionaryDao, LogFieldDictionary> implements LogFieldDictionaryService {


    @Override
    public void InsertList(List<LogFieldDictionary> dictionaries) {
        for(LogFieldDictionary dictionary : dictionaries){
            baseMapper.insert(dictionary);
        }
    }

    @Override
    public void update(LogFieldDictionary entity) {
        QueryWrapper<LogFieldDictionary> queryWrapper = new QueryWrapper<LogFieldDictionary>()
                .eq("field_code",entity.getFieldCode())
                .eq("table_name",entity.getTableName());
        LogFieldDictionary dic = baseMapper.selectOne(queryWrapper);
        if(dic == null){
            baseMapper.insert(entity);
        }else {
            entity.setId(dic.getId());
            baseMapper.updateById(entity);
        }
    }

    @Override
    public List<LogFieldDictionary> selectListByTbName(String tableName) {
        QueryWrapper<LogFieldDictionary> queryWrapper = new QueryWrapper<LogFieldDictionary>()
                .eq("table_name",tableName);
        List<LogFieldDictionary> list = baseMapper.selectList(queryWrapper);
        return list;
    }
}
