package cn.com.bmsoft.modules.bsm.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.bsm.dao.InstallDao;
import cn.com.bmsoft.modules.bsm.entity.InstallEntity;
import cn.com.bmsoft.modules.bsm.service.InstallService;


@Service("installService")
public class InstallServiceImpl extends ServiceImpl<InstallDao, InstallEntity> implements InstallService {
    @Autowired
    private InstallDao installDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<InstallEntity> wrapper = Wrappers.lambdaQuery();
        InstallEntity bsmInstallEntity = new InstallEntity();

        IPage<InstallEntity> page = this.page(
                new Query<InstallEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean updateBatchByIds(int[] asList) {

        return installDao.updateBatchByIds(asList);
    }

    @Override
    public List<InstallEntity> installList( String ywfwbm) {
        return installDao.installList(ywfwbm);
    }

    @Override
    public List<InstallEntity> selectLog(String ywfwbm) {
        return null;
    }

    @Override
    public List<InstallEntity> installLogList(InstallEntity installEntity) {
        return installDao.installLogList(installEntity);
    }

    @Override
    public boolean updateBatchByYwfwbm(Collection<InstallEntity> list) {
        return installDao.updateBatchByYwfwbm(list);
    }
    @Override
    public String ywfwbmYz(String ywfwbm) {
        return installDao.ywfwbmYz(ywfwbm);
    }
}