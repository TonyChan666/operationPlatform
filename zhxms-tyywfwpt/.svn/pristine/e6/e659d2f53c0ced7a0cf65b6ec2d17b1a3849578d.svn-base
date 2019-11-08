package cn.com.bmsoft.modules.rm.dao;

import cn.com.bmsoft.modules.rm.entity.LinkDeviceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 资源管理-链路设备表
 * 
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
@Mapper
public interface LinkDeviceDao extends BaseMapper<LinkDeviceEntity> {

    public void saveLinkDevices(List<LinkDeviceEntity> linkDevices);

    public void deleteLinkDevices(List<String> ids);

    public List<LinkDeviceEntity> deleteLinkDevices(Integer id);

    public void removeLinkDevicesByLinkId(List<Integer> linkId);
}
