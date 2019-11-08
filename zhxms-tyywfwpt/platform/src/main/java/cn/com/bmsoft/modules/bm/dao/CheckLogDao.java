package cn.com.bmsoft.modules.bm.dao;

import cn.com.bmsoft.modules.bm.entity.CheckLogEntity;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 备份管理-备份巡查日志表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Mapper
public interface CheckLogDao extends BaseMapper<CheckLogEntity> {

    boolean updateBatchByIds(Long[] ids);

    CheckLogEntity selectlogId(int id);

    IPage<CheckLogEntity> queryPageList(IPage<CheckLogEntity> page, @Param("params")Map<String, Object> params);
}
