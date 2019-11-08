package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.ProbeDao;
import cn.com.bmsoft.modules.rm.entity.ProbeEntity;
import cn.com.bmsoft.modules.rm.service.ProbeService;
import cn.com.bmsoft.modules.rm.utils.SearchParamsUtils;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("probeService")
public class ProbeServiceImpl extends ServiceImpl<ProbeDao, ProbeEntity> implements ProbeService {

    @Autowired
    private ProbeDao probeDao;

    final static String[] SEARCH_FIELDS = {"tzyymc","tzpz","jrpz","ywjz","sfyx"};

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<ProbeEntity> probeEntityQueryWrapper = new QueryWrapper<>();
        probeEntityQueryWrapper = new SearchParamsUtils<ProbeEntity>().mapParams2Wrapper(SEARCH_FIELDS,params,probeEntityQueryWrapper);
        probeEntityQueryWrapper.isNull("delete_flag");
        IPage<ProbeEntity> page = this.page(
                new Query<ProbeEntity>().getPage(params),
                probeEntityQueryWrapper.select("id",
                        "tzbh",
                        "tzyymc",
                        "tzpz",
                        "jrpz",
                        "ywjz",
                        "pzfwq",
                        "acess_key",
                        "tspl",
                        "tzsm",
                        "cjrid",
                        "cjsj",
                        "xgrid",
                        "xgsj",
                        "sfyx",
                        "delete_flag",
                        "(select t.name from c_dictionary_item t where t.dict_code='tzpz' and t.value=rm_probe.tzpz) `tzpzmc`",
                        "(select t.name from c_dictionary_item t where t.dict_code='jrpz' and t.value=rm_probe.jrpz) `jrpzmc`",
                        "(select t.name from c_dictionary_item t where t.dict_code='ywjz' and t.value=rm_probe.ywjz) `ywjzmc`")
        );

        return new PageUtils(page);
    }

    @Override
    public void removeToRecoveryByIds(List<Integer> ids) {
        probeDao.removeToRecoveryByIds(ids);
    }

}