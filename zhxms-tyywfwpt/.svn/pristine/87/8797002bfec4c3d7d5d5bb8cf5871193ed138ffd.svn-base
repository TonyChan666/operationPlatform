package cn.com.bmsoft.modules.sa.service.impl;

import cn.com.bmsoft.modules.sa.dao.SaAmDao;
import cn.com.bmsoft.modules.sa.service.SaAmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("saAmService")
public class SaAmServiceImpl implements SaAmService {

    @Autowired
    SaAmDao saAmDao;

    @Override
    public List<Map<String, Object>> quantityTrendOfAlarm(Map<String, Object> queryParam) {
        return saAmDao.quantityTrendOfAlarm(queryParam);
    }

    @Override
    public List<Map<String, Object>> proportionOfAlarmType(Map<String, Object> queryParam) {
        return saAmDao.proportionOfAlarmType(queryParam);
    }

    @Override
    public List<Map<String, Object>> alarmObjectOfTopTen(Map<String, Object> queryParam) {
        return saAmDao.alarmObjectOfTopTen(queryParam);
    }

    @Override
    public List<Map<String, Object>> alarmIndexOfTopTen(Map<String, Object> queryParam) {
        return saAmDao.alarmIndexOfTopTen(queryParam);
    }

    @Override
    public List<Map<String, Object>> distributionOfAlarmLevel(Map<String, Object> queryParam) {
        return saAmDao.distributionOfAlarmLevel(queryParam);
    }

    @Override
    public List<Map<String, Object>> backupExceptionSa(Map<String, Object> queryParam) {
        return saAmDao.backupExceptionSa(queryParam);
    }

    @Override
    public List<Map<String, Object>> alarmSa(Map<String, Object> queryMap) {
        return saAmDao.alarmSa(queryMap);
    }
}
