package cn.com.bmsoft.modules.rm.service;

import cn.com.bmsoft.modules.rm.entity.LinkDeviceEntity;
import cn.com.bmsoft.modules.rm.entity.LinkEntity;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @auther zhangyibing  zhangyibing@bmsoft.com.cn
* @Date 2019/9/27
* @desription 链路登记表
**/
public interface LinkService extends IService<LinkEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public R saveLink(Map<String, Object> params, SysUserEntity user);

    public R deleteLink(Integer[] ids);

    public List<LinkDeviceEntity> getDevicesBylinkId(Integer id);

    public R updateLink(LinkEntity linkEntity);

    LinkEntity getLink(Integer id);
}
