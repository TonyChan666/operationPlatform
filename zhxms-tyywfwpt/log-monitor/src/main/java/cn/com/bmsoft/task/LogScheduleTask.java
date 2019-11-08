package cn.com.bmsoft.task;

import cn.com.bmsoft.entity.*;
import cn.com.bmsoft.service.*;
import cn.com.bmsoft.utils.FTPUtil;
import cn.com.bmsoft.modules.job.task.ITask;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 日志采集调度任务--定时任务
 */
@Component("logScheduleTask")
public class LogScheduleTask implements ITask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LogCollectionService logCollectionService;
    @Autowired
    private CollectionTaskService taskService;
    @Autowired
    private LogFieldDictionaryService dictionaryService;
    @Autowired
    private LogFieldValueService valueService;


    @Override
    public void run(String params) {
        logger.info("1、开始执行日志采集、监控调度任务......");
        JSONObject jsonObject = new JSONObject().parseObject(params);
        int taskId = jsonObject.getInteger("taskId");
        loadDatasBySFTP(taskId);
    }

    /**
     * 通过SFTP从服务器获得日志数据
     * @param taskId 任务id
     */
    private void loadDatasBySFTP(int taskId) {
        LogCollection lcEntity = new LogCollection();
        CollectionTask task = taskService.getBaseMapper().selectById(taskId);

        lcEntity.setCollectionTaskid(taskId);
        lcEntity.setLogMold(task.getMoldType().equals("device")?1:0);
        lcEntity.setRemark(task.getRemark());
        //采集结果  0：失败  1：成功  2：采集中
        lcEntity.setCollectionState(2);
        lcEntity.setCollectionStarttime(new Date());
        //3.插入采集任务信息
        logCollectionService.insert(lcEntity);
        //2.从服务器加载数据
        logger.info("2、从服务器加载数据......");
        List<String> datas = FTPUtil.load(task);
        //3.更新采集任务信息
        lcEntity.setCollectionEndtime(new Date());
        if(datas != null && datas.size() > 0){
            lcEntity.setCollectionState(1);
        }else {
            lcEntity.setCollectionState(0);
        }
        logCollectionService.updateById(lcEntity);



        //4.解析对应采集日志数据
        List<LogFieldDictionary> entityList = dictionaryService.selectListByTbName(task.getTableName());
        List<LogFieldValue> list = valueService.loadLogFieldValuesFromList(datas,entityList,task.getTableName());
        for(LogFieldValue entity : list){
            valueService.insert(entity);
        }
        logger.info("3、采集完成......");
    }
}
