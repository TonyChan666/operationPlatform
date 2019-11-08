package cn.com.bmsoft.utils;
import cn.com.bmsoft.entity.CollectionTask;
import cn.com.bmsoft.entity.LogFieldDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class ConfUtil {
    private static Environment env;
    @Autowired
    public void set(Environment env) {
        this.env = env;
    }

    /**
     * 从配置文件中读取（采集任务明细）
     * @param  task 文件对象
     * @return 需要录入的映射数据
     */
    public static List<LogFieldDictionary> getFieldDictionaryByProperies(CollectionTask task){
        List<LogFieldDictionary> entityList = new ArrayList<>();
        //读取类别
        String moldType = task.getMoldType();
        String tableName = task.getTableName();
        //读取列数
        String columns = env.getProperty("monitor.log."+tableName+"."+moldType+".columns");
        String[] columnList = columns.split(",");
        //遍历对应的映射关系
        for(int i=0;i<columnList.length;i++){
            LogFieldDictionary entity = new LogFieldDictionary();
            entity.setTableName(tableName);
            entity.setColumnId(i+1);
            entity.setFieldCode(env.getProperty("monitor.log."+tableName+".device.columns."+columnList[i]+".code"));
            entity.setFieldName(env.getProperty("monitor.log."+tableName+".device.columns."+columnList[i]+".name"));
            entity.setFieldDescription(env.getProperty("monitor.log."+tableName+".device.columns."+columnList[i]+".description"));
            entityList.add(entity);
        }
        return entityList;
    }

    /**
     * 从配置文件获取采集任务明细
     * @return
     */
    public static List<CollectionTask> getCollectTaskByProperies(){
        List<CollectionTask> list = new ArrayList<>();
        String moldstr = env.getProperty("monitor.log.mold");
        String[] molds = moldstr.split(",");
        for(int i=0;i < molds.length;i ++){
            CollectionTask entity = new CollectionTask();
            entity.setTableName(molds[i]);
            entity.setCollectType(Integer.parseInt(env.getProperty("monitor.log."+molds[i]+".collectType")));
            entity.setMoldType(env.getProperty("monitor.log."+molds[i]+".moldType"));
            entity.setPrequency(env.getProperty("monitor.log."+molds[i]+".prequency"));
            entity.setStatus(Integer.parseInt(env.getProperty("monitor.log."+molds[i]+".status")));
            entity.setCollectIp(env.getProperty("monitor.log."+molds[i]+".ip"));
            entity.setCollectPort(Integer.parseInt(env.getProperty("monitor.log."+molds[i]+".port")));
            entity.setCollectPath(env.getProperty("monitor.log."+molds[i]+".path"));
            entity.setCollectFileName(env.getProperty("monitor.log."+molds[i]+".fileName"));
            entity.setCollectUsername(env.getProperty("monitor.log."+molds[i]+".username"));
            entity.setCollectPassword(env.getProperty("monitor.log."+molds[i]+".password"));
            entity.setRemark(env.getProperty("monitor.log."+molds[i]+".remark"));
            list.add(entity);
        }
        return list;
    }


}
