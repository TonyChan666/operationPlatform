package cn.com.bmsoft.modules.rm.dao;

import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-11-03
 */
@Mapper
public interface RmResourceDeptDao extends BaseMapper<RmResourceDeptEntity> {

    public void saveResourceDepts(List<RmResourceDeptEntity> rmResourceDeptEntityList);

    void removeResourceDepts(String zyid, String tableName);

    List<Map<String,Object>> getResourceDeptNames(String zyid, String tableName);
	
}
