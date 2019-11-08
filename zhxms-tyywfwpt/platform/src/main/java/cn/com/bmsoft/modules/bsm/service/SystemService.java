package cn.com.bmsoft.modules.bsm.service;

import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.bsm.entity.SystemEntity;

import java.util.Map;

/**
 * 业务服务管理-应用系统表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
public interface SystemService extends IService<SystemEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateBatchByIds(Long[] ids);



    SystemEntity getSystemById(int id);

    String ywfwbmYz(String ywfwbm);
}

