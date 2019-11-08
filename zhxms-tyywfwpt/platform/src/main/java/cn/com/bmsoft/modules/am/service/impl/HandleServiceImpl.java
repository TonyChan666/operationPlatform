package cn.com.bmsoft.modules.am.service.impl;

import cn.com.bmsoft.modules.am.entity.RecordEntity;
import cn.com.bmsoft.modules.am.service.RecordService;
import cn.com.bmsoft.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.am.dao.HandleDao;
import cn.com.bmsoft.modules.am.entity.HandleEntity;
import cn.com.bmsoft.modules.am.service.HandleService;
import org.springframework.transaction.annotation.Transactional;


@Service("handleService")
public class HandleServiceImpl extends ServiceImpl<HandleDao, HandleEntity> implements HandleService {

    @Autowired
    private RecordService recordService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HandleEntity> page = this.page(
                new Query<HandleEntity>().getPage(params),
                new QueryWrapper<HandleEntity>().isNull("delete_flag")
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveHandle(HandleEntity handle) {
        RecordEntity recordEntity = recordService.getById(handle.getAlarmRecodId());
        recordEntity.setHandlePlan(handle.getHandlePlan());
        recordEntity.setHandleUserId(handle.getHandleUserId());
        recordEntity.setHandleResult(handle.getHandleResult());
        recordEntity.setHandleStatus(handle.getHandleResult());
        recordEntity.setHandleTime(handle.getHandleTime());
        recordEntity.setReasonAnalysis(handle.getReasonAanalysis());
        String costTime = timeStamp2Date(recordEntity.getHandleTime().getTime() - recordEntity.getAlarmTime().getTime());
        recordEntity.setCostTime(costTime);
        handle.setId(((int) ((Math.random() * 9 + 1) * 100000)));
        this.save(handle);
        recordService.updateById(recordEntity);
    }

    //时间戳转时间字符串
    private String timeStamp2Date(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return (time/(60*60*1000)) + ":" +
                String.format("%02d", c.get(Calendar.MINUTE)) + ":" +
                String.format("%02d", c.get(Calendar.SECOND));
    }

}