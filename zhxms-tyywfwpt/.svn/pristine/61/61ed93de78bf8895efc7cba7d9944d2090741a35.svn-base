package cn.com.bmsoft.modules.wom.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.wom.dao.BusinessBindingDao;
import cn.com.bmsoft.modules.wom.entity.BusinessBindingEntity;
import cn.com.bmsoft.modules.wom.service.BusinessBindingService;


@Service("businessBindingService")
public class BusinessBindingServiceImpl extends ServiceImpl<BusinessBindingDao, BusinessBindingEntity> implements BusinessBindingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String mbmc = (String)params.get("mbmc");
        String mbid = (String)params.get("mbid");
        IPage<BusinessBindingEntity> page = this.page(
                new Query<BusinessBindingEntity>().getPage(params),
                new QueryWrapper<BusinessBindingEntity>().like(StringUtils.isNotBlank(mbmc),"mbmc",mbmc)
                .like(StringUtils.isNotBlank(mbid),"mbid",mbid)
        );

        return new PageUtils(page);
    }

}