package cn.com.bmsoft.modules.wom.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.wom.dao.HandleInformationDao;
import cn.com.bmsoft.modules.wom.entity.HandleInformationEntity;
import cn.com.bmsoft.modules.wom.service.HandleInformationService;


@Service("handleInformationService")
public class HandleInformationServiceImpl extends ServiceImpl<HandleInformationDao, HandleInformationEntity> implements HandleInformationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HandleInformationEntity> page = this.page(
                new Query<HandleInformationEntity>().getPage(params),
                new QueryWrapper<HandleInformationEntity>()
        );

        return new PageUtils(page);
    }

}