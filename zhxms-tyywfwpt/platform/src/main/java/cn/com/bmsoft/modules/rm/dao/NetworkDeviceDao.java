package cn.com.bmsoft.modules.rm.dao;

import cn.com.bmsoft.modules.rm.entity.NetworkDeviceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-网络设备表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
@Mapper
public interface NetworkDeviceDao extends BaseMapper<NetworkDeviceEntity> {

    public void removeToRecoveryByIds(List<Integer> ids);

    IPage<NetworkDeviceEntity> getNetworkDeviceList(IPage<NetworkDeviceEntity> page,@Param("params") Map<String, Object> params);

    NetworkDeviceEntity getNetworkDevice(Integer id);
}
