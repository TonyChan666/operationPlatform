package cn.com.bmsoft.modules.rm.service;

import cn.com.bmsoft.modules.rm.entity.ContainerEntity;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-资源容器表
 *
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-09-27
 */
public interface ContainerService extends IService<ContainerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public void removeToRecoveryByIds(List<Integer> ids);

    public void saveContainer(ContainerEntity containerEntity);

    public R updateContainer(ContainerEntity containerEntity);

    ContainerEntity getContainer(Integer id);
}

