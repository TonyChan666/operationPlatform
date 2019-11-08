package cn.com.bmsoft.modules.am.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 首页数据统计
 * 
 * @author tanjidong
 * @since 2019-10-10
 */
@Mapper
public interface IndexStatisticsDao {

    /**
     * 工单数量趋势统计
     */
    List<Map<String, Object>> queryTrendOrderCount(Map<String, Object> queryParam);
    
    /**
     * 资源占比统计
     * @param queryParam
     * @return
     */
    List<Map<String, Object>> queryResourcePercent(Map<String, Object> queryParam);
    
    /**
    * 按用户查询资源数量
    * @param queryParam
    * @return
    */
   int queryResourceByUserId(Map<String, Object> queryParam);
   
   /**
    * 按用户ID查询未解决的告警记录数量
    * @param queryParam
    * @return
    */
   int queryAlarmCountByUserId(Map<String, Object> queryParam);
   
   /**
    * 按用户ID查询未解决的告警记录数量
    * @param queryParam
    * @return
    */
   int queryOrderCountByUserId(Map<String, Object> queryParam);

}
