package cn.com.bmsoft.modules.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;

import java.util.List;
import java.util.Map;

/**
 * 字典项表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-22
 */
public interface DictionaryItemService extends IService<DictionaryItemEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DictionaryItemEntity> queryByDictCode(String dictCode);
}

