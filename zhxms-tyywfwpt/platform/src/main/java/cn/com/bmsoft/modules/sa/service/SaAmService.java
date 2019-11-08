package cn.com.bmsoft.modules.sa.service;

import java.util.List;
import java.util.Map;

/**
 * 告警统计
 *
 * @author luyuwei
 */
public interface SaAmService {

    /**
     * 告警数量趋势统计（可按日、周、月来统计）
     */
    List<Map<String, Object>> quantityTrendOfAlarm(Map<String, Object> queryParam);

    /**
     * 告警类型占比统计
     */
    List<Map<String, Object>> proportionOfAlarmType(Map<String, Object> queryParam);

    /**
     * 排名前十的告警对象统计
     */
    List<Map<String, Object>> alarmObjectOfTopTen(Map<String, Object> queryParam);

    /**
     * 排名前十的告警指标统计
     */
    List<Map<String, Object>> alarmIndexOfTopTen(Map<String, Object> queryParam);

    /**
     * 告警等级分布情况统计
     */
    List<Map<String, Object>> distributionOfAlarmLevel(Map<String, Object> queryParam);

    /**
     * 备份异常情况统计
     */
    List<Map<String, Object>> backupExceptionSa(Map<String, Object> queryParam);

    /**
     * 告警数及解决率统计
     */
    List<Map<String, Object>> alarmSa(Map<String, Object> queryMap);
}
