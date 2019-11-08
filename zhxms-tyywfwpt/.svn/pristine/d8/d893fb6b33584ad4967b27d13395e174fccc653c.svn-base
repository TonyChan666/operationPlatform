package cn.com.bmsoft.modules.wom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.wom.dao.ProcessDao;
import cn.com.bmsoft.modules.wom.entity.ProcessEntity;
import cn.com.bmsoft.modules.wom.service.ProcessService;


@Service("processService")
public class ProcessServiceImpl extends ServiceImpl<ProcessDao, ProcessEntity> implements ProcessService {
   @Autowired
    private ProcessDao processDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProcessEntity> page = this.page(
                new Query<ProcessEntity>().getPage(params),
                new QueryWrapper<ProcessEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ProcessEntity> handleProcessInfo(int id) {
        return processDao.handleProcessInfo(id);
    }

    @Override
    public List<ProcessEntity> auditRecordsInfo(int id) {
        return processDao.auditRecordsInfo(id);
    }

    @Override
    public List<ProcessEntity> transferredRecordsInfo(int id) {
        return processDao.transferredRecordsInfo(id);
    }

    @Override
    public ProcessEntity paifaInfo(int id) {
        return processDao.paifaInfo(id);
    }

    @Override
    public ProcessEntity infoBygdbh(String gdbh) {
        return processDao.infoBygdbh(gdbh);
    }

}