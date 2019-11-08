package cn.com.bmsoft.modules.wom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.wom.entity.HandleInformationEntity;

import java.util.Map;

/**
 * 工单管理-工单处理详情表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
public interface HandleInformationService extends IService<HandleInformationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

