package cn.com.bmsoft.modules.rm.dao;

import cn.com.bmsoft.modules.rm.entity.LinkDeviceEntity;
import cn.com.bmsoft.modules.rm.entity.LinkEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @auther zhangyibing  zhangyibing@bmsoft.com.cn
* @Date 2019/9/27
* @desription 链路登记
**/
@Mapper
public interface LinkDao extends BaseMapper<LinkEntity> {

    public List<LinkDeviceEntity> getDevicesBylinkId(Integer linkId);

    public void removeToRecoveryByIds(List<Integer> ids);

    LinkEntity getLink(Integer id);

    IPage<LinkEntity> getLinkList(IPage<LinkEntity> page,@Param("params") Map<String, Object> params);
}
