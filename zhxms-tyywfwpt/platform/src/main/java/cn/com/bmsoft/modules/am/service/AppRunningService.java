package cn.com.bmsoft.modules.am.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.am.entity.AppRunningEntity;

import java.util.Map;

/**
 * 
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
public interface AppRunningService extends IService<AppRunningEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

