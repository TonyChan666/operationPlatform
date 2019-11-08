package cn.com.bmsoft.modules.wom.dao;

import cn.com.bmsoft.modules.wom.entity.BusinessBindingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工单管理-工单业务绑定表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-10-12
 */
@Mapper
public interface BusinessBindingDao extends BaseMapper<BusinessBindingEntity> {
	
}
