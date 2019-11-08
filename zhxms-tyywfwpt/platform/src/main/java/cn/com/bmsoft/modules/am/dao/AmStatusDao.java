package cn.com.bmsoft.modules.am.dao;

import cn.com.bmsoft.modules.am.entity.RecordEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AmStatusDao {

    Integer link();

    List<Map<String, Object>> getDictItem();

    List<Map<String, Object>> getStorageStatus();

    List<Map<String, Object>> getServiceStatus();

    IPage<RecordEntity> getAmRecord(IPage<RecordEntity> page, @Param("params")Map<String, Object> params);
}
