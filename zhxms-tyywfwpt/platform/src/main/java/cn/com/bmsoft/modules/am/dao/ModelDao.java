package cn.com.bmsoft.modules.am.dao;

import cn.com.bmsoft.modules.am.entity.ModelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 告警模板表
 * 
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@Mapper
public interface ModelDao extends BaseMapper<ModelEntity> {
	
}
