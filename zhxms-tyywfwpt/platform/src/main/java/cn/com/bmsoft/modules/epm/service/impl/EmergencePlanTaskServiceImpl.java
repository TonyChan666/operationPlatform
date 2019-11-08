package cn.com.bmsoft.modules.epm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.epm.dao.EmergencePlanTaskDao;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanTaskEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanTaskService;


@Service("emergencePlanTaskService")
public class EmergencePlanTaskServiceImpl extends ServiceImpl<EmergencePlanTaskDao, EmergencePlanTaskEntity> implements EmergencePlanTaskService {

    @Autowired
    private EmergencePlanTaskDao emergencePlanTaskDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmergencePlanTaskEntity> page = this.page(
                new Query<EmergencePlanTaskEntity>().getPage(params),
                new QueryWrapper<EmergencePlanTaskEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public EmergencePlanTaskEntity getEmergencePlanTask(String yjyaid, String updateUser,String rwmc) {
        return emergencePlanTaskDao.getEmergencePlanTask(yjyaid,updateUser,rwmc);
    }

    @Override
    public List<EmergencePlanTaskEntity> getEmergencePlanTaskList(Map<String, Object> params) {
        return emergencePlanTaskDao.getEmergencePlanTaskList(params);
    }

}