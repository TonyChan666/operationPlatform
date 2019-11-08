package cn.com.bmsoft.modules.dictionary.dao;

import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典项表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-22
 */
@Mapper
public interface DictionaryItemDao extends BaseMapper<DictionaryItemEntity> {

    List<DictionaryItemEntity> queryByDictCode(String dictCode);
}
