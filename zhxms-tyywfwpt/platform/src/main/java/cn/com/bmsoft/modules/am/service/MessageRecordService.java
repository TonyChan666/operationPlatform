package cn.com.bmsoft.modules.am.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.am.entity.MessageRecordEntity;

import java.util.Map;

/**
 * 短信记录表
 *
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
public interface MessageRecordService extends IService<MessageRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

