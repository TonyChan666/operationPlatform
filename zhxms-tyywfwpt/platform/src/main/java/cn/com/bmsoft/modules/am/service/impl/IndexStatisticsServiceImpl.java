package cn.com.bmsoft.modules.am.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bmsoft.modules.am.dao.IndexStatisticsDao;
import cn.com.bmsoft.modules.am.service.IndexStatisticsService;

@Service("indexStatisticsService")
public class IndexStatisticsServiceImpl implements IndexStatisticsService {

	@Autowired
	IndexStatisticsDao indexStatisticsDao;

	@Override
	public List<Map<String, Object>> queryTrendOrderCount(Map<String, Object> queryParam) {
		// TODO Auto-generated method stub
		return indexStatisticsDao.queryTrendOrderCount(queryParam);
	}

	@Override
	public List<Map<String, Object>> queryResourcePercent(Map<String, Object> queryParam) {
		// TODO Auto-generated method stub
		return indexStatisticsDao.queryResourcePercent(queryParam);
	}

	@Override
	public int queryResourceByUserId(Map<String, Object> queryParam) {
		// TODO Auto-generated method stub
		return indexStatisticsDao.queryResourceByUserId(queryParam);
	}

	@Override
	public int queryAlarmCountByUserId(Map<String, Object> queryParam) {
		// TODO Auto-generated method stub
		return indexStatisticsDao.queryAlarmCountByUserId(queryParam);
	}

	@Override
	public int queryOrderCountByUserId(Map<String, Object> queryParam) {
		// TODO Auto-generated method stub
		return indexStatisticsDao.queryOrderCountByUserId(queryParam);
	}

	

}
