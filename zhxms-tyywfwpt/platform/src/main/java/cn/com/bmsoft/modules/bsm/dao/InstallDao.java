package cn.com.bmsoft.modules.bsm.dao;

import cn.com.bmsoft.modules.bsm.entity.InstallEntity;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 业务服务管理-应用安装表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-23
 */
@Mapper
public interface InstallDao extends BaseMapper<InstallEntity> {

    boolean updateBatchByIds(int[] ids);

    List<InstallEntity> installList(String ywfwbm);

    List<InstallEntity> installLogList(InstallEntity installEntity);

    boolean updateBatchByYwfwbm(Collection<InstallEntity> list);
    String ywfwbmYz(String ywfwbm);
}
