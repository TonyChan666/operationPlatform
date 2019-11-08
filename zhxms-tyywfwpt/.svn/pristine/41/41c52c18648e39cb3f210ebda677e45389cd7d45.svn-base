package cn.com.bmsoft.modules.wom.dao;

import cn.com.bmsoft.modules.wom.entity.InformationEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工单管理-工单信息表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
@Mapper
public interface InformationDao extends BaseMapper<InformationEntity> {

    IPage<InformationEntity> getDaiChuLiList(IPage<InformationEntity> page,@Param("params") Map<String, Object> params,@Param("list") Collection<String> list);

   // IPage<InformationEntity> getdaiShengHePageList(IPage<InformationEntity> page,@Param("params") Map<String, Object> params,@Param("list") Collection<String> list);

    IPage<InformationEntity> getDaiPaiFaPageList(IPage<InformationEntity> page,@Param("params") Map<String, Object> params,@Param("list") Collection<String> list);

    IPage<InformationEntity> getDaiBanJiePageList(IPage<InformationEntity> page,@Param("params") Map<String, Object> params);

    void deleteByIds(List<Long> ids);

    void deleteHandleInformation(Long[] ids);

    void deleteProcess(Long[] ids);

    void deleteInformation(Long[] ids);

    InformationEntity getInformationById(int id);

    IPage<InformationEntity> getdaiShengHePageList(IPage<InformationEntity> page, @Param("params") Map<String, Object> params);

    IPage<InformationEntity> getDaiPaiFaPageList(IPage<InformationEntity> page, @Param("params")Map<String, Object> params);

    IPage<InformationEntity> warnList(IPage<InformationEntity> page,@Param("params") Map<String, Object> params);

    IPage<InformationEntity> home(IPage<InformationEntity> page, @Param("params") Map<String, Object> params);

    IPage<InformationEntity> ywzzList(IPage<InformationEntity> page, @Param("params") Map<String, Object> params);

    IPage<InformationEntity> ptywryList(IPage<InformationEntity> page, @Param("params") Map<String, Object> params);
}
