package cn.com.bmsoft.modules.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.dictionary.entity.DictionaryEntity;

import java.util.Map;

/**
 * 字典表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-22
 */
public interface DictionaryService extends IService<DictionaryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    DictionaryEntity queryByCode(String code);
}

