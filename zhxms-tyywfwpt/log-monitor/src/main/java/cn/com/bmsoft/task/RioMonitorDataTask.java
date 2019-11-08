package cn.com.bmsoft.task;

import cn.com.bmsoft.modules.job.task.ITask;
import cn.com.bmsoft.service.LogFieldValueService;
import cn.com.bmsoft.service.RioMonitorService;
import cn.com.bmsoft.service.RioOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component("RioMonitorDataTask")
public class RioMonitorDataTask implements ITask {

    @Autowired
    private RioMonitorService monitorService;
    @Autowired
    private RioOperationService operationService;
    @Autowired
    private LogFieldValueService valueService;


    @Override
    public void run(String params) {

        try {
            //获取所有服务id
            List<String> ids = operationService.selectServiceIds();
            //查找数据
            List<Map<String, Object>> datas = null;
            datas = monitorService.getRioMonitorDataBySvcIds(ids);
            //插入数据
            valueService.insertMapList(datas,"rio");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
