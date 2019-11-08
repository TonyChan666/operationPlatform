package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.LinkDeviceDao;
import cn.com.bmsoft.modules.rm.entity.LinkDeviceEntity;
import cn.com.bmsoft.modules.rm.service.LinkDeviceService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("linkDeviceService")
public class LinkDeviceServiceImpl extends ServiceImpl<LinkDeviceDao, LinkDeviceEntity> implements LinkDeviceService {
    @Autowired
    private LinkDeviceDao linkDeviceDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LinkDeviceEntity> page = this.page(
                new Query<LinkDeviceEntity>().getPage(params),
                new QueryWrapper<LinkDeviceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveLinkDevices(List<LinkDeviceEntity> linkDevices) {
        linkDeviceDao.saveLinkDevices(linkDevices);
    }

    @Override
    public R deleteLinkDevices(List<String> ids) {
        if(null == ids || ids.isEmpty()){
            return R.error("删除设备不可为空");
        }
        linkDeviceDao.deleteLinkDevices(ids);
        return R.ok();
    }

    @Override
    public void removeLinkDevicesByLinkId(List<Integer> linkId) {
        linkDeviceDao.removeLinkDevicesByLinkId(linkId);
    }

}