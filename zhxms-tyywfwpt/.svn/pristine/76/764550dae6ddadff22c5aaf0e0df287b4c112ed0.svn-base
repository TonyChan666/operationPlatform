package cn.com.bmsoft.modules.sa.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 工单统计
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-10-10
 */
@Mapper
public interface SaWomDao {

    /**
     * 工单数量趋势统计（可按日、周、月来统计）
     */
    List<Map<String, Object>> quantityTrendOfWorkOrder(Map<String, Object> queryParam);

    /**
     * 上月工单数统计
     */
    List<Map<String, Object>> workOrderOfLastMonth(Map<String, Object> queryParam);

    /**
     * 上月工单解决率统计
     */
    List<Map<String, Object>> workOrderResolutionRateOfLastMonth(Map<String, Object> queryParam);

    /**
     * 工单类型占比统计
     */
    List<Map<String, Object>> proportionOfWorkOrderType(Map<String, Object> queryParam);

    /**
     * 排名前十的工单创建人统计
     */
    List<Map<String, Object>> workOrderCreaterOfTopTen(Map<String, Object> queryParam);

    /**
     * 获取工单数及解决率
     */
    List<Map<String, Object>> workOrder(Map<String, Object> queryParam);

}
