package cn.com.bmsoft.modules.bm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.bm.entity.RecoverRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 备份管理-恢复登记表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
public interface RecoverRecordService extends IService<RecoverRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean updateBatchByIds(Long[] ids);

    RecoverRecordEntity getByRecordId(int id);
}

