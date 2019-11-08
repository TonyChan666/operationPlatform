package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.RmResourceDeptDao;
import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;
import cn.com.bmsoft.modules.rm.service.RmResourceDeptService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("rmResourceDeptService")
public class RmResourceDeptServiceImpl extends ServiceImpl<RmResourceDeptDao, RmResourceDeptEntity> implements RmResourceDeptService {

    @Autowired
    private RmResourceDeptDao rmResourceDeptDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RmResourceDeptEntity> page = this.page(
                new Query<RmResourceDeptEntity>().getPage(params),
                new QueryWrapper<RmResourceDeptEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveResourceDepts(List<RmResourceDeptEntity> rmResourceDeptEntityList) {
        if(!rmResourceDeptEntityList.isEmpty()){
            rmResourceDeptDao.saveResourceDepts(rmResourceDeptEntityList);
        }
    }

    @Override
    public void removeResourceDepts(String zyid, String tableName) {
        rmResourceDeptDao.removeResourceDepts(zyid,tableName);
    }

    @Override
    public List<Map<String, Object>> getResourceDeptNames(String zyid, String tableName) {
        return rmResourceDeptDao.getResourceDeptNames(zyid,tableName);
    }

}