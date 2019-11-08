package cn.com.bmsoft.modules.dictionary.dao;

import cn.com.bmsoft.modules.dictionary.entity.DictionaryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-22
 */
@Mapper
public interface DictionaryDao extends BaseMapper<DictionaryEntity> {

    DictionaryEntity queryByCode(String code);
	
}
