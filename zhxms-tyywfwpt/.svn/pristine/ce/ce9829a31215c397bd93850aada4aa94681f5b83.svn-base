package cn.com.bmsoft.service.impl;

import cn.com.bmsoft.dao.LogFieldDictionaryDao;
import cn.com.bmsoft.dao.LogFieldValueDao;
import cn.com.bmsoft.entity.LogFieldDictionary;
import cn.com.bmsoft.entity.LogFieldValue;
import cn.com.bmsoft.service.LogFieldValueService;
import cn.com.bmsoft.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service("LogFieldValueService")
public class LogFieldValueServiceImpl extends ServiceImpl<LogFieldValueDao,LogFieldValue> implements LogFieldValueService {

    @Autowired
    private LogFieldValueDao fieldValueDao;
    @Autowired
    private LogFieldDictionaryDao dictionaryDao;

    @Override
    public void insertMapList(List<Map<String, Object>> mapList,String tableName) {

        for(Map<String, Object> map : mapList){
            for (Map.Entry<String, Object> entry : map.entrySet()) {

                QueryWrapper<LogFieldDictionary> wrapper = new QueryWrapper<LogFieldDictionary>()
                        .eq("table_name",tableName)
                        .eq("field_name",entry.getKey());
                LogFieldDictionary dic = dictionaryDao.selectOne(wrapper);
                String code = dic.getFieldCode();

                LogFieldValue value = new LogFieldValue();
                value.setRecordId(IdWorker.getInstance().nextId());
                value.setFieldCode(code);
                value.setFieldName(entry.getKey());

                value.setFieldValue(entry.getValue().toString());
                value.setTableName(tableName);

                fieldValueDao.insert(value);
            }

        }

    }

    @Override
    public List<LogFieldValue> loadLogFieldValuesFromList(List<String> datas, List<LogFieldDictionary> entityList, String tableName) {
        List<LogFieldValue> list = new ArrayList<>();

        if(datas != null && datas.size() > 0){
            for(String data : datas){
                long racordId = IdWorker.getInstance().nextId();
                //切割数据
                String[] colDatas = data.split(" ");
                //根据每列所需要第几列取值
                for(LogFieldDictionary en : entityList){
                    int col = en.getColumnId();
                    if(colDatas.length >= col){
                        LogFieldValue value = new LogFieldValue();
                        value.setTableName(tableName);
                        value.setRecordId(racordId);
                        value.setFieldName(en.getFieldName());
                        value.setFieldCode(en.getFieldCode());
                        value.setFieldValue(colDatas[col-1]);
                        list.add(value);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void insert(LogFieldValue value) {
        baseMapper.insert(value);
    }
}
