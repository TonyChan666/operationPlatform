package cn.com.bmsoft.modules.rm.service;

import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.rm.entity.LinkDeviceEntity;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-链路设备表
 *
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
public interface LinkDeviceService extends IService<LinkDeviceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public void saveLinkDevices(List<LinkDeviceEntity> linkDevices);

    public R deleteLinkDevices(List<String> ids);

    public void removeLinkDevicesByLinkId(List<Integer> linkId);
}

