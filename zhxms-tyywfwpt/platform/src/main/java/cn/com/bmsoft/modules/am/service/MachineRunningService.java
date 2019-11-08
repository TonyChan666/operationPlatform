package cn.com.bmsoft.modules.am.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.am.entity.MachineRunningEntity;

import java.util.Map;

/**
 * 
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
public interface MachineRunningService extends IService<MachineRunningEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

