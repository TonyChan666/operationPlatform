package cn.com.bmsoft.modules.am.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.am.dao.MachineRunningDao;
import cn.com.bmsoft.modules.am.entity.MachineRunningEntity;
import cn.com.bmsoft.modules.am.service.MachineRunningService;


@Service("machineRunningService")
public class MachineRunningServiceImpl extends ServiceImpl<MachineRunningDao, MachineRunningEntity> implements MachineRunningService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MachineRunningEntity> page = this.page(
                new Query<MachineRunningEntity>().getPage(params),
                new QueryWrapper<MachineRunningEntity>().isNull("delete_flag")
        );

        return new PageUtils(page);
    }

}