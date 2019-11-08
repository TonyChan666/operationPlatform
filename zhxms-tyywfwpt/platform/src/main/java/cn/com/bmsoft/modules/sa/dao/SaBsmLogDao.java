package cn.com.bmsoft.modules.sa.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 业务服务日志统计
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-10-11
 */
@Mapper
public interface SaBsmLogDao {

    /**
     * 调用情况趋势图
     */
    Map<String, Object> serviceCallTrendStatistics(Map<String, Object> queryParam);

    /**
     * 调用次数统计
     */
    Map<String, Object> serviceCallCountStatistics(Map<String, Object> queryParam);

}
