package cn.com.bmsoft.modules.sa.service;

import java.util.List;
import java.util.Map;

/**
 * 故障统计
 *
 * @author luyuwei
 */
public interface SaFaultService {

    /**
     * 故障数量趋势统计
     */
    List<Map<String, Object>> quantityTrendOfFault(Map<String, Object> queryParam);

    /**
     * 排名前十的故障统计
     */
    List<Map<String, Object>> faultOfTopTen(Map<String, Object> queryParam);

    /**
     * 当月资源类故障次数统计
     */
    List<Map<String, Object>> resourceFaultQuantityOfTheSameMonth(Map<String, Object> queryParam);

    /**
     * 当月服务类故障次数统计
     */
    List<Map<String, Object>> serviceFaultQuantityOfTheSameMonth(Map<String, Object> queryParam);

    /**
     *故障数据分析，包括当月资源故障数，当月服务故障数
     */
    List<Map<String, Object>> alarmCount(Map<String, Object> queryMap);
}
