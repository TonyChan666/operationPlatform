package cn.com.bmsoft.service;

import cn.com.bmsoft.entity.LogFieldDictionary;
import cn.com.bmsoft.entity.LogFieldValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface LogFieldValueService extends IService<LogFieldValue> {

    /**
     * 传入一个List<Map>
     *     key：字段名
     *     value：值
     * @param mapList
     * @param tableName
     */
    void insertMapList(List<Map<String,Object>> mapList,String tableName);

    /**
     * 从文件中获取日志数据
     * @param datas 数据
     * @param entityList 映射表的行
     * @param tableName 表名
     * @return
     */
    List<LogFieldValue> loadLogFieldValuesFromList(List<String> datas, List<LogFieldDictionary> entityList, String tableName);


    void insert(LogFieldValue value);
}
