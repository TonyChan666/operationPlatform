package cn.com.bmsoft.modules.bm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.bm.entity.CheckLogEntity;

import java.util.Date;
import java.util.Map;

/**
 * 备份管理-备份巡查日志表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
public interface CheckLogService extends IService<CheckLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
    int countToday();

    int countYesterday();

    int countAngTime(Date startTime, Date endTime);

    int countThirtyDays();

    int countSevenDays();
    CheckLogEntity selectlogId(int id);

    boolean updateBatchByIds(Long[] ids);

    PageUtils queryPageList(Map<String, Object> params);
}

