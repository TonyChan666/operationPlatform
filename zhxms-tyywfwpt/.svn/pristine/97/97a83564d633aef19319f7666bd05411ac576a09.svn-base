package cn.com.bmsoft.modules.bm.dao;

import cn.com.bmsoft.modules.bm.entity.StrategyEntity;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 备份管理-备份策略表
 * 
 * @author sufang  luyuwei@bmsoft.com.cn
 * @since 2019-09-23
 */
@Mapper
public interface StrategyDao extends BaseMapper<StrategyEntity> {

    boolean updateBatchByIds(@Param("ids") Long[] ids);
}
