package cn.com.bmsoft.modules.bsm.service;

import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.bsm.entity.InstallEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 业务服务管理-应用安装表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
public interface InstallService extends IService<InstallEntity> {

    PageUtils queryPage(Map<String, Object> params);
    boolean updateBatchByIds(int[] ids);

    List<InstallEntity> installList(String ywfwbm);

    List<InstallEntity> selectLog(String ywfwbm);

    List<InstallEntity> installLogList(InstallEntity installEntity);

    boolean updateBatchByYwfwbm(Collection<InstallEntity> list);

    String ywfwbmYz(String ywfwbm);

    // boolean saveBatch(List<ServiceEntity> list);
}

