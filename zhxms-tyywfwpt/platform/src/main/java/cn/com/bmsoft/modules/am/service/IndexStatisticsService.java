package cn.com.bmsoft.modules.am.service;

import java.util.List;
import java.util.Map;

public interface IndexStatisticsService {

	/**
	 * 工单数量趋势统计
	 */
	List<Map<String, Object>> queryTrendOrderCount(Map<String, Object> queryParam);

	/**
	 * 资源占比统计
	 * 
	 * @param queryParam
	 * @return
	 */
	List<Map<String, Object>> queryResourcePercent(Map<String, Object> queryParam);

	/**
	 * 按用户查询资源数量
	 * 
	 * @param queryParam
	 * @return
	 */
	int queryResourceByUserId(Map<String, Object> queryParam);

	/**
	 * 按用户ID查询未解决的告警记录数量
	 * 
	 * @param queryParam
	 * @return
	 */
	int queryAlarmCountByUserId(Map<String, Object> queryParam);

	/**
	 * 按用户ID查询未解决的告警记录数量
	 * 
	 * @param queryParam
	 * @return
	 */
	int queryOrderCountByUserId(Map<String, Object> queryParam);

}
