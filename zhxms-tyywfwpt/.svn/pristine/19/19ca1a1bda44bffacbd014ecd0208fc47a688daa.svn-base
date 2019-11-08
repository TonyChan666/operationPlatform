package cn.com.bmsoft.modules.bm.task;

import cn.com.bmsoft.modules.am.entity.RecordEntity;
import cn.com.bmsoft.modules.am.service.RecordService;
import cn.com.bmsoft.modules.attachment.service.FileSystemService;
import cn.com.bmsoft.modules.bm.entity.CheckLogEntity;
import cn.com.bmsoft.modules.bm.entity.StrategyEntity;
import cn.com.bmsoft.modules.bm.service.CheckLogService;
import cn.com.bmsoft.modules.bm.service.StrategyService;
import cn.com.bmsoft.modules.bsm.service.ServiceService;
import cn.com.bmsoft.modules.job.task.ITask;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("checkLogTast")
public class CheckLogTast implements ITask {
    @Autowired
    private CheckLogService checkLogService;
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private FileSystemService fileSystemService;
    @Autowired
    private SerialNumberService serialNumberService;
    @Autowired
    private RecordService recordService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(String params) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        CheckLogEntity checkLogEntity = new CheckLogEntity();
        //备份日记编号
        cn.com.bmsoft.utils.R result1 = serialNumberService.generator("GD");
        String bfrzbh = (String) result1.get("serialNumber");
        File file = null;
        FileInputStream inputStream = null;
        BufferedReader br = null;
        try {
            file =fileSystemService.downloadFile("/document/"+"2019-10-06"+"/checklog.txt");
            inputStream = new FileInputStream(file);

            InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");
            br = new BufferedReader(isr);
            String line;
            while((line = br.readLine())!=null){
                String ywfwbh = line.substring(line.indexOf("url")+4,line.indexOf("/"));
                String bfzt = line.substring(line.indexOf("bfzt")+5);
                System.out.println(ywfwbh);
                System.out.println(bfzt);
                /*sftp获取业务服务编号*/
                QueryWrapper<StrategyEntity> wrapper = new QueryWrapper <>();
                wrapper.eq("bfbh",ywfwbh);
                StrategyEntity backup = strategyService.getOne(wrapper);
                checkLogEntity.setBfid(backup.getId());
                //checkLogEntity.setBfmc(backup.getBfmc());
                //checkLogEntity.setFwq(backup.getFwqmc());
                //checkLogEntity.setYwfw(backup.getYwfw());
                //  checkLogEntity.setBfsj(backup.getBfsdsj());
                checkLogEntity.setBfzt(Integer.parseInt( bfzt ));//备份状态  sftp获取
                checkLogEntity.setBfrzbh(bfrzbh);
                checkLogEntity.setCjsj(new Date());
                checkLogService.save(checkLogEntity);

                // 生成告警记录
                RecordEntity recordEntity = new RecordEntity();

                recordEntity.setAlarmRecordType("1"); // 告警记录类型
                recordEntity.setResourceType("1"); // 告警资源类型
                //recordEntity.setAlarmObject(backup.getFwqbh()); // 告警服务器编码
                //recordEntity.setAlarmTarget(strategyEntity.getTargetCode()); // 告警指标编码
               // recordEntity.setAlarmValue(targetValue + ""); // 告警值
                recordEntity.setAlarmTime(new Date()); // 告警时间
                //recordEntity.setIpAddress(machineRunningEntity.getIpAddress()); // 告警服务器IP地址

                //recordEntity.setNetworkType(serverEntity.getWllb()); // 服务器所属网络
                //recordEntity.setPoliceType(serverEntity.getYwjz()); // 服务器所属警种
                recordEntity.setHandleUserId(Long.parseLong((backup.getYwryid()) + "")); // 服务器运维人员

                //recordEntity.setAlarmGrade(thresholdGradeEntity.getThresholdLevel()); // 告警级别
                //recordEntity.setAlarmNoticeType(thresholdGradeEntity.getAlarmType()); // 告警通知方式
                recordEntity.setHandleStatus("0");
                recordService.save(recordEntity);
                logger.debug("备份巡查定时任务正在执行，参数为：{}", params);
            }
            br.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
