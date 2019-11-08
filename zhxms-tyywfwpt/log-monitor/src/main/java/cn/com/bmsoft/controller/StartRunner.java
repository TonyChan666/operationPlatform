package cn.com.bmsoft.controller;

import cn.com.bmsoft.entity.CollectionTask;
import cn.com.bmsoft.entity.LogFieldDictionary;
import cn.com.bmsoft.service.CollectionTaskService;
import cn.com.bmsoft.service.LogFieldDictionaryService;
import cn.com.bmsoft.service.RioOperationService;
import cn.com.bmsoft.utils.ConfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 项目启动时会执行的类
 * @Date:2019/10/15
 */
@Component
@Order(value = 1)//多个执行类时代表执行顺序
public class StartRunner implements ApplicationRunner {
    private static Environment env;
    @Autowired
    public void set(Environment env) {
        this.env = env;
    }

    @Autowired
    private CollectionTaskService taskService;
    @Autowired
    private LogFieldDictionaryService dictionaryService;
    @Autowired
    private RioOperationService operationService;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("读取配置文件...");
        //1.读取配置文件
        loadProperties();
        logger.info("读取配置文件完成");
        //2.初始化日志信息
        //operationService.loadBaseData("insert");

    }

    /**
     * 读取配置文件
     */
    private void loadProperties(){
        //获取数据采集任务信息
        List<CollectionTask> taskEntityList = ConfUtil.getCollectTaskByProperies();
        for(CollectionTask taskEntity : taskEntityList){
            int taskId = 0;
            //如果没有该任务，则插入（根据taskname判断，如mold0）
            if(taskService.selectOne(taskEntity.getTableName()) == null){
                taskService.insert(taskEntity);
            }else {
                taskId = taskService.selectOne(taskEntity.getTableName()).getId();
                taskEntity.setId(taskId);
                taskService.update(taskEntity);
            }
            //获取采集任务明细
            List<LogFieldDictionary> dictionaryList = ConfUtil.getFieldDictionaryByProperies(taskEntity);
            for(LogFieldDictionary dictionary : dictionaryList){
                dictionaryService.update(dictionary);
            }
        }
    }


}
