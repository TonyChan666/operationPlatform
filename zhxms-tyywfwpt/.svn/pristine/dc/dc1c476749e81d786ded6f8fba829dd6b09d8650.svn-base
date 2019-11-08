package cn.com.bmsoft.modules.rm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zhangyibing  zhangyibing@bmsoft.com.cn
 * @since 2019-11-03
 */
public interface RmResourceDeptService extends IService<RmResourceDeptEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveResourceDepts(List<RmResourceDeptEntity> rmResourceDeptEntityList) ;

    void removeResourceDepts(String zyid, String tableName);

    List<Map<String,Object>> getResourceDeptNames(String zyid, String tableName);
}

