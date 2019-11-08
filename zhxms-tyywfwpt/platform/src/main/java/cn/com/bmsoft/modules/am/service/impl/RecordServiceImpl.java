package cn.com.bmsoft.modules.am.service.impl;

import cn.com.bmsoft.modules.am.utils.AmParams;
import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryItemService;
import cn.com.bmsoft.modules.rm.entity.DeploymentInfoEntity;
import cn.com.bmsoft.modules.rm.entity.ServerEntity;
import cn.com.bmsoft.modules.rm.service.DeploymentInfoService;
import cn.com.bmsoft.modules.rm.service.ServerService;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.SpringContextUtils;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.am.dao.RecordDao;
import cn.com.bmsoft.modules.am.entity.RecordEntity;
import cn.com.bmsoft.modules.am.service.RecordService;


@Service("recordService")
public class RecordServiceImpl extends ServiceImpl<RecordDao, RecordEntity> implements RecordService {

    @Autowired
    private DictionaryItemService dictionaryItemService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private DeploymentInfoService deploymentInfoService;

    @Autowired
    private ServerService serverService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<RecordEntity> queryWrapper = new QueryWrapper<RecordEntity>();
        //添加时间判断
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        if (StringUtil.isNotBlank(startTime)){
            queryWrapper.ge("alarm_time", startTime);
        }
        if (StringUtil.isNotBlank(endTime)){
            queryWrapper.le("alarm_time", endTime);
        }
        IPage<RecordEntity> page = this.page(
                new Query<RecordEntity>().getPage(params),
                queryWrapper
                    .select("id",
                            "alarm_record_code",
                            "alarm_record_type",
                            "alarm_object",
                            "handle_status",
                            "handle_result",
                            "alarm_time",
                            "time_limit",
                            "resource_type",
                            "alarm_value",
                            "order_code",
                            "(select t.name from c_dictionary_item t where t.dict_code='gjzb' and t.value=am_record.alarm_target) alarm_target",
                            "(select t.name from c_dictionary_item t where t.dict_code='gjdj' and t.value=am_record.alarm_target) alarm_grade")
                    .like(StringUtil.isNotBlank((String)params.get("alarmObjectName")), "alarm_object_name", params.get("alarmObjectName"))
                    .like(StringUtil.isNotBlank((String)params.get("alarmTarget")), "alarm_target", params.get("alarmTarget"))
                    .like(StringUtil.isNotBlank((String)params.get("alarmRecordCode")), "alarm_record_code", params.get("alarmRecordCode"))
                    .like(StringUtil.isNotBlank((String)params.get("alarmNoticeType")), "alarm_notice_type", params.get("alarmNoticeType")+"")
                    .like(StringUtil.isNotBlank((String)params.get("orderCode")), "order_code", params.get("orderCode"))
                    .eq(StringUtil.isNotBlank((String)params.get("alarmGrade")), "alarm_grade", params.get("alarmGrade"))
                    .eq(StringUtil.isNotBlank((String)params.get("handleStatus")), "handle_status", params.get("handleStatus"))
                    .eq(StringUtil.isNotBlank((String)params.get("resourceType")), "resource_type", params.get("resourceType"))
                    .eq(StringUtil.isNotBlank((String)params.get("alarmRecordType")), "alarm_record_type", params.get("alarmRecordType"))
                    .eq(params.get("operationUserId") != null, "operation_user_id", params.get("operationUserId"))
                    // .in("alarmObject", )
                    .isNull("delete_flag")
        );
        long nowTime = System.currentTimeMillis();
        //计算超时余时
        page.getRecords().forEach(recordEntity -> {
            if (recordEntity.getHandleStatus().equals("1")) {
                long deadline = recordEntity.getAlarmTime().getTime() + (Long.valueOf(recordEntity.getTimeLimit())*60*60*1000);
                long time = nowTime - deadline;
                String lastTime;
                if (time < 0){
                    time = -time;
                    lastTime = "余时" + timeStamp2Date(time);
                }else {
                    lastTime = "超时" + timeStamp2Date(time);
                }
                recordEntity.setLastTime(lastTime);
            }
            setRecord(recordEntity);
        });
        return new PageUtils(page);
    }

    @Override
    public RecordEntity getByRecordId(String id) {
        RecordEntity record = getById(id);
        record.setAlarmGrade(dictionaryItemService.getOne(new QueryWrapper<DictionaryItemEntity>()
                .eq("dict_code", "gjdj")
                .eq("value", record.getAlarmGrade())).getName());
        record.setAlarmTarget(dictionaryItemService.getOne(new QueryWrapper<DictionaryItemEntity>()
                .eq("dict_code", "gjzb")
                .eq("value", record.getAlarmTarget())).getName());
        //从用户表查姓名
        SysUserEntity handleUser = sysUserService.getById(record.getHandleUserId());
        SysUserEntity operationUser = sysUserService.getById(record.getOperationUserId());
        record.setHandleUser(handleUser==null?null:handleUser.getName());
        record.setOperationUser(operationUser==null?null:operationUser.getName());
        if (StringUtil.isNotBlank(record.getAlarmNoticeType())) {
            //获取value字符串
            String[] alarmNoticeList = record.getAlarmNoticeType().split(",");
            //获取名称集合
            List list = dictionaryItemService.listObjs(new QueryWrapper<DictionaryItemEntity>()
                    .select("name")
                    .eq("dict_code","gjfs")
                    .in("value", Arrays.asList(alarmNoticeList)));
            //组合名称字符串以“，”相隔
            String alarmNoticeName = String.join(",", list);
            record.setAlarmNoticeType(alarmNoticeName);
        }
        long nowTime = System.currentTimeMillis();
        //计算余时
        if (record.getHandleStatus().equals("1")) {
            long deadline = record.getAlarmTime().getTime() + (Long.valueOf(record.getTimeLimit())*60*60*1000);
            long time = deadline - nowTime;
            if (time>0) {
                record.setLastTime(timeStamp2Date(time));
            }
        }
        record.setTimeLimit(record.getTimeLimit() + "H");
        setRecord(record);
        return record;
    }

    //设置服务或资源对象信息
    private void setRecord(RecordEntity record) {
        String resourceType = record.getResourceType();
        record.setResourceType(AmParams.TABLE_NAME.get(resourceType));
        IService bean = (IService) SpringContextUtils.getBean(AmParams.CODE_PREFIX .get(resourceType));
        Map beanMap = bean.getMap(new QueryWrapper<>().eq("id", record.getAlarmObject()));
        //设置告警对象名称
        record.setAlarmObjectName(beanMap!=null?beanMap.get(AmParams.RM_TABLE_NAME.get(resourceType))+"":null);
        if (record.getAlarmRecordType().equals("2")){
            //设置服务器名称
            List fwqids = deploymentInfoService.listObjs(new QueryWrapper<DeploymentInfoEntity>()
                          .select("fwqid")
                          .eq("zyid", record.getAlarmObject())
                          .eq("zybm","bsm_service"));
            List sbmcList = serverService.listObjs(new QueryWrapper<ServerEntity>()
                  .select("sbmc")
                  .in("id", fwqids));
            record.setServerName(String.join(",", sbmcList));
        }
    }


    //时间戳转时间字符串
    private String timeStamp2Date(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return (time/(60*60*1000)) + ":" +
                String.format("%02d", c.get(Calendar.MINUTE)) + ":" +
                String.format("%02d", c.get(Calendar.SECOND));
    }

}