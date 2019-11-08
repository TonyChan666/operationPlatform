package cn.com.bmsoft.modules.rm.service;

import cn.com.bmsoft.modules.rm.entity.StorageEntity;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 资源管理-资源存储表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-24
 */
public interface StorageService extends IService<StorageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public void removeToRecoveryByIds(List<Integer> ids);

    public void saveStorage(StorageEntity storageEntity);

    public R updateStorage(StorageEntity storageEntity);

    public StorageEntity getStorage(Integer id);
}

