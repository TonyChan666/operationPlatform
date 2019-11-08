package cn.com.bmsoft.modules.sys.service.impl;

import cn.com.bmsoft.modules.sys.dao.SysProviderDao;
import cn.com.bmsoft.modules.sys.entity.SysProviderEntity;
import cn.com.bmsoft.modules.sys.service.SysProviderService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("providerService")
public class SysProviderServiceImpl extends ServiceImpl<SysProviderDao, SysProviderEntity> implements SysProviderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String providerName = (String)params.get("providerName");
        String status = (String)params.get("status");
        IPage<SysProviderEntity> page = this.page(
                new Query<SysProviderEntity>().getPage(params),
                new QueryWrapper<SysProviderEntity>()
                    .select(
                        "provider_id",
                        "provider_code",
                        "provider_name",
                        "provider_desc",
                        "status",
                        "create_time",
                        "(SELECT COUNT(*) FROM sys_user su WHERE su.provider_id = sys_provider.provider_id AND su.status = 1) AS provider_people_num"
                    )
                    .like(StringUtils.isNotBlank(providerName),"provider_name", providerName)
                    .eq(StringUtils.isNotBlank(status),"status", params.get("status"))
                    .isNull("delete_flag")
                    .orderByDesc("create_time")
        );

        return new PageUtils(page);
    }

}