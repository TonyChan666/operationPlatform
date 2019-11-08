package cn.com.bmsoft.modules.serial.dao;

import cn.com.bmsoft.modules.serial.entity.SerialNumberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公共流水号表
 * 
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-10-10
 */
@Mapper
public interface SerialNumberDao extends BaseMapper<SerialNumberEntity> {
	
}
