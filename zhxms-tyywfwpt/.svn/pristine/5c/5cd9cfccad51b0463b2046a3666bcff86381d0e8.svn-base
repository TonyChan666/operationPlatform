package cn.com.bmsoft.modules.sa.service.impl;

import cn.com.bmsoft.modules.sa.dao.SaFaultDao;
import cn.com.bmsoft.modules.sa.service.SaFaultService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("saFaultService")
public class SaFaultServiceImpl implements SaFaultService {

    @Autowired
    SaFaultDao saFaultDao;

    @Override
    public List<Map<String, Object>> quantityTrendOfFault(Map<String, Object> queryParam){
        return saFaultDao.quantityTrendOfFault(queryParam);
    }

    @Override
    public List<Map<String, Object>> faultOfTopTen(Map<String, Object> queryParam){
        return saFaultDao.faultOfTopTen(queryParam);
    }

    @Override
    public List<Map<String, Object>> resourceFaultQuantityOfTheSameMonth(Map<String, Object> queryParam){
        return saFaultDao.resourceFaultQuantityOfTheSameMonth(queryParam);
    }

    @Override
    public List<Map<String, Object>> serviceFaultQuantityOfTheSameMonth(Map<String, Object> queryParam){
        return saFaultDao.serviceFaultQuantityOfTheSameMonth(queryParam);
    }

    @Override
    public List<Map<String, Object>> alarmCount(Map<String, Object> queryMap) {
        List<Map<String, Object>> result = saFaultDao.alarmCount(queryMap);
        if(result.isEmpty()){
            return new ArrayList<>();
        }
        Map<String,Object> alarmData = new HashMap<>();
        for(Map<String,Object> map : result){
            //1: 资源类告警，2：服务类告警
            if(StringUtils.equals(map.get("alarm_record_type")+"","1")) {
                alarmData.put("rm_alarm_count", map.get("count"));
            }else if (StringUtils.equals(map.get("alarm_record_type")+"","2")) {
                alarmData.put("se_alarm_count", map.get("count"));
            }
        }
        List<Map<String, Object>> r = new ArrayList<>();
        r.add(alarmData);
        return r;
    }
}
