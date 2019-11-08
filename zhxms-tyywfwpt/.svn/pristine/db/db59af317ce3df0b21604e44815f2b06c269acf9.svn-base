package cn.com.bmsoft.modules.sa.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 服务器资源统计
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-10-11
 */
@Mapper
public interface SaRmBsmDao {

    /**
     * 服务器资源统计分页列表
     */
    IPage<Map<String, Object>> pageServerResourceStatistic(Map<String, Object> queryParam);

    /**
     * 资源业务服务信息分页列表
     */
    IPage<Map<String, Object>> pageBusinessServiceInfo(Map<String, Object> queryParam);

    /**
     * 资源容器信息分页列表
     */
    IPage<Map<String, Object>> pageContainerInfo(Map<String, Object> queryParam);

    /**
     * 资源组件信息分页列表
     */
    IPage<Map<String, Object>> pageComponentInfo(Map<String, Object> queryParam);

    /**
     * 资源存储信息分页列表
     */
    IPage<Map<String, Object>> pageStorageInfo(Map<String, Object> queryParam);

    /**
     * 资源链路信息分页列表
     */
    IPage<Map<String, Object>> pageLinkInfo(Map<String, Object> queryParam);

}
