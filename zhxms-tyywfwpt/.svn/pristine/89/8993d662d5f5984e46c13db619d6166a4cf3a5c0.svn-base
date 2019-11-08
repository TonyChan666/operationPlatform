package cn.com.bmsoft.modules.epm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.epm.dao.EmergencePlanAssessDao;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanAssessEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanAssessService;


@Service("emergencePlanAssessService")
public class EmergencePlanAssessServiceImpl extends ServiceImpl<EmergencePlanAssessDao, EmergencePlanAssessEntity> implements EmergencePlanAssessService {
    @Autowired
    private EmergencePlanAssessDao emergencePlanAssessDao;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmergencePlanAssessEntity> page = this.page(
                new Query<EmergencePlanAssessEntity>().getPage(params),
                new QueryWrapper<EmergencePlanAssessEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int deleteByIdLogical(Serializable id) throws Exception {
        EmergencePlanAssessEntity emergencePlanAssess = new EmergencePlanAssessEntity();
        emergencePlanAssess.setId(id.toString());
        emergencePlanAssess.setStatus("0");
        return emergencePlanAssessDao.updateById(emergencePlanAssess);
    }

    @Override
    public int deleteByIdLogicalBatch(String id) throws Exception {
        EmergencePlanAssessEntity emergencePlanAssess = new EmergencePlanAssessEntity();
        int result=0;
        String[] ids = id.split(",");
        for (int i = 0, len = ids.length; i < len; i++) {
            emergencePlanAssess.setId(ids[i]);
            emergencePlanAssess.setStatus("0");
            result = emergencePlanAssessDao.updateById(emergencePlanAssess);
        }
        return result;
    }


//    @Override
//    public List<EmergencePlanAssessEntity> emergencePlanAssessQueryList(Map<String, Object> queryParams) {
//        List<EmergencePlanAssessEntity> list=null;
//        try {
//            list=emergencePlanAssessDao.emergencePlanAssessQueryList(queryParams);
//        } catch (Exception e) {
//            throw e;
//        }
//        return list;
//    }
//



    @Override
    public PageUtils emergencePlanAssessQueryList(Map<String, Object> params) {
        IPage<EmergencePlanAssessEntity> page = new Query<EmergencePlanAssessEntity>().getPage(params);
        return new PageUtils(baseMapper.emergencePlanAssessQueryList(page, params));
    }

}