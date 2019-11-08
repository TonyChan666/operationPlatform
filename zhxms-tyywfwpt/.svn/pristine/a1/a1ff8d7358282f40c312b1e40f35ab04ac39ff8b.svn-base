package cn.com.bmsoft.modules.am.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.am.dao.MessageRecordDao;
import cn.com.bmsoft.modules.am.entity.MessageRecordEntity;
import cn.com.bmsoft.modules.am.service.MessageRecordService;


@Service("messageRecordService")
public class MessageRecordServiceImpl extends ServiceImpl<MessageRecordDao, MessageRecordEntity> implements MessageRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MessageRecordEntity> page = this.page(
                new Query<MessageRecordEntity>().getPage(params),
                new QueryWrapper<MessageRecordEntity>().isNull("delete_flag")
        );

        return new PageUtils(page);
    }

}