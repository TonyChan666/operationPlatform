package cn.com.bmsoft.modules.bm.service.impl;

import cn.com.bmsoft.modules.attachment.entity.AttachmentEntity;
import cn.com.bmsoft.modules.attachment.service.AttachmentService;
import cn.com.bmsoft.modules.bm.entity.StrategyEntity;
import cn.com.bmsoft.modules.bm.service.StrategyService;
import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.bsm.service.ServiceService;
import cn.com.bmsoft.modules.rm.entity.ServerEntity;
import cn.com.bmsoft.modules.rm.service.ServerService;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.bm.dao.RecoverRecordDao;
import cn.com.bmsoft.modules.bm.entity.RecoverRecordEntity;
import cn.com.bmsoft.modules.bm.service.RecoverRecordService;


@Service("recoverRecordService")
public class RecoverRecordServiceImpl extends ServiceImpl<RecoverRecordDao, RecoverRecordEntity> implements RecoverRecordService {
    @Autowired
    private RecoverRecordDao recoverRecordDao;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private StrategyService strategyService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String ywry = (String) params.get("ywrymc");
        String bfmc = (String)params.get("bfmc");
        if (StringUtil.isNotBlank(ywry)){
           List<Object> ywryids = sysUserService.listObjs(new QueryWrapper<SysUserEntity>().select("user_id").like("name",ywry));
            if(ywryids.isEmpty()){
                IPage<SysUserEntity> page = sysUserService.page(
                        new Query<SysUserEntity>().getPage(params),
                        new QueryWrapper<SysUserEntity>().select("user_id").like("name",ywry)
                );
                PageUtils pageUtils = new PageUtils(page);
                return pageUtils;
            }
            params.put("ywryids", ywryids);
        }
        if (StringUtil.isNotBlank(bfmc)){
            List<Object> bfclids = new ArrayList<Object>();
            bfclids = strategyService.listObjs(new QueryWrapper<StrategyEntity>().select("id").like("bfmc", bfmc));
            if(bfclids.isEmpty()){
                IPage<StrategyEntity> page = strategyService.page(
                        new Query<StrategyEntity>().getPage(params),
                        new QueryWrapper<StrategyEntity>().select("id").like("bfmc",bfmc)
                );
                PageUtils pageUtils = new PageUtils(page);
                return pageUtils;
            }
            params.put("bfclids", bfclids);
        }
        IPage<RecoverRecordEntity> page = recoverRecordDao.selectRecoverRecorListPage(
                new Query<RecoverRecordEntity>().getPage(params),
                params
        );
        return new PageUtils(page);
    }

    @Override
    public boolean updateBatchByIds(Long[] ids) {
        return recoverRecordDao.updateBatchByIds(ids);
    }

    @Override
    public RecoverRecordEntity getByRecordId(int id) {
        RecoverRecordEntity recoverRecord = getOne(new QueryWrapper<RecoverRecordEntity>()
                .select("*",
                        "(select t.name from sys_user t where t.user_id = bm_recover_record.ywryid) ywry",
                        "(select t.sbmc from rm_server t where t.id = bm_recover_record.fwqid) fwqmc",
                        "(select t.ywfwmc from bsm_service t where t.id = bm_recover_record.ywfwid) ywfw",
                        "(select t.bfmc from bm_strategy t where t.id = bm_recover_record.bfid) bfmc",
                        "(select t.original_file_name from c_attachment t where t.id = bm_recover_record.fjid) fjmc")
                .eq("id", id));
        return recoverRecord;
    }


}