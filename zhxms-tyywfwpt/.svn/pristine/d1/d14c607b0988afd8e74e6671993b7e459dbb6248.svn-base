package cn.com.bmsoft.modules.rm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.rm.entity.ProbeEntity;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-探针登记表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
public interface ProbeService extends IService<ProbeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public void removeToRecoveryByIds(List<Integer> ids);
}

