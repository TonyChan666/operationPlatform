package cn.com.bmsoft.modules.bm.service.impl;

import cn.com.bmsoft.modules.bm.entity.StrategyEntity;
import cn.com.bmsoft.modules.bm.service.StrategyService;
import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.bsm.service.ServiceService;
import cn.com.bmsoft.modules.rm.entity.ServerEntity;
import cn.com.bmsoft.modules.rm.service.ServerService;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import org.apache.commons.lang.StringUtils;
import cn.com.bmsoft.modules.bm.dao.CheckLogDao;
import cn.com.bmsoft.modules.bm.entity.CheckLogEntity;
import cn.com.bmsoft.modules.bm.service.CheckLogService;


@Service("checkLogService")
public class CheckLogServiceImpl extends ServiceImpl<CheckLogDao, CheckLogEntity> implements CheckLogService {
    @Autowired
    private CheckLogDao checkLogDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CheckLogEntity> page = checkLogDao.queryPageList(
                new Query<CheckLogEntity>().getPage(params),params
        );
        return new PageUtils(page);
    }

    @Override
    public int countToday(){
        LambdaQueryWrapper<CheckLogEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.apply("to_days(create_time) = to_days(now()) ");
        return checkLogDao.selectCount(queryWrapper);
    }
    @Override
    public int countYesterday(){
        LambdaQueryWrapper<CheckLogEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.apply("TO_DAYS( NOW( )) - TO_DAYS( create_time) = 1 ");
        return checkLogDao.selectCount(queryWrapper);
    }
    @Override
    public int countAngTime(Date startTime, Date endTime){
        LambdaQueryWrapper<CheckLogEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.ge(CheckLogEntity::getCjsj,startTime);
        queryWrapper.le(CheckLogEntity::getCjsj,endTime);
        return checkLogDao.selectCount(queryWrapper);
    }
    @Override
    public int countThirtyDays(){
        LambdaQueryWrapper<CheckLogEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.apply("DATE_SUB(CURDATE(), INTERVAL 30 DAY) <date(create_time)");
        return checkLogDao.selectCount(queryWrapper);
    }
    @Override
    public int countSevenDays(){
        LambdaQueryWrapper<CheckLogEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.apply("DATE_SUB(CURDATE(), INTERVAL 7 DAY) <date(create_time)");
        return checkLogDao.selectCount(queryWrapper);
    }

    @Override
    public boolean updateBatchByIds(Long[] ids) {
        return checkLogDao.updateBatchByIds(ids);
    }

    @Override
    public PageUtils queryPageList(Map<String, Object> params) {
        IPage<CheckLogEntity> page = checkLogDao.queryPageList(
                new Query<CheckLogEntity>().getPage(params),params
        );
        return new PageUtils(page);
    }

    @Override
    public CheckLogEntity selectlogId(int id){
        CheckLogEntity checkLogEntity = checkLogDao.selectlogId(id);
        return checkLogEntity;
    }

}