package cn.com.bmsoft.modules.bm.dao;

import cn.com.bmsoft.modules.bm.entity.RecoverRecordEntity;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 备份管理-恢复登记表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Mapper
public interface RecoverRecordDao extends BaseMapper<RecoverRecordEntity> {

    boolean updateBatchByIds(@Param("ids") Long[] ids);
    IPage<RecoverRecordEntity> selectRecoverRecorListPage(IPage<RecoverRecordEntity> page, @Param("params") Map<String, Object> params);
    //IPage<RecoverRecordEntity> selectRecoverRecorListPage(IPage<RecoverRecordEntity> page, @Param("params") Map<String, Object> params);

}
