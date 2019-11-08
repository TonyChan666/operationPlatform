package cn.com.bmsoft.task;

import cn.com.bmsoft.modules.job.task.ITask;
import cn.com.bmsoft.service.RioOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;


/**
 * 腾讯里约平台日志
 *      基础数据采集任务
 *（机构、系统、应用、服务基础信息）
 * （一周更新一次）
 */
@Component("RioBaseDataTask")
public class RioBaseDataTask implements ITask {



    @Autowired
    private RioOperationService operationService;

    @Override
    public void run(String params) {

        try {
            operationService.loadBaseData("update");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }









}
