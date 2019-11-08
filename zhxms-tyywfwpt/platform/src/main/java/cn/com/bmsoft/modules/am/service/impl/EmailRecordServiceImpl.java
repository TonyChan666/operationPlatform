package cn.com.bmsoft.modules.am.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.am.dao.EmailRecordDao;
import cn.com.bmsoft.modules.am.entity.EmailRecordEntity;
import cn.com.bmsoft.modules.am.service.EmailRecordService;


@Service("emailRecordService")
public class EmailRecordServiceImpl extends ServiceImpl<EmailRecordDao, EmailRecordEntity> implements EmailRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<EmailRecordEntity> page = this.page(
                new Query<EmailRecordEntity>().getPage(params),
                new QueryWrapper<EmailRecordEntity>().isNull("delete_flag")
        );

        return new PageUtils(page);
    }

}