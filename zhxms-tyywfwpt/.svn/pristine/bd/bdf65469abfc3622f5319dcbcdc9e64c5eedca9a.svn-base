package cn.com.bmsoft.modules.rm.service;

import cn.com.bmsoft.modules.rm.entity.ComponentEntity;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-资源组件表
 *
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
public interface ComponentService extends IService<ComponentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public R saveComponent(ComponentEntity component, SysUserEntity user);

    public void removeToRecoveryByIds(List<Integer> ids);

    public ComponentEntity getComponentById(Integer id);

    public R updateComponent(ComponentEntity component);
}

