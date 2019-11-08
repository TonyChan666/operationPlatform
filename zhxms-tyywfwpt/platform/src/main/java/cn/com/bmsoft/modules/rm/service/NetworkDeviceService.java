package cn.com.bmsoft.modules.rm.service;

import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.rm.entity.NetworkDeviceEntity;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-网络设备表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
public interface NetworkDeviceService extends IService<NetworkDeviceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public void removeToRecoveryByIds(List<Integer> ids);

    public R updateNetworkDevice(NetworkDeviceEntity networkDeviceEntity);

    NetworkDeviceEntity getNetworkDevice(Integer id);

    void saveNetworkDevice(NetworkDeviceEntity networkDevice);
}

