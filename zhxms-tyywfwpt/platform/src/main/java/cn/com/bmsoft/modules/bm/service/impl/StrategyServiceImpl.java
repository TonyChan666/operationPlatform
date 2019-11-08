package cn.com.bmsoft.modules.bm.service.impl;

import cn.com.bmsoft.modules.attachment.entity.AttachmentEntity;
import cn.com.bmsoft.modules.attachment.service.AttachmentService;
import cn.com.bmsoft.modules.bm.entity.RecoverRecordEntity;
import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.bsm.service.ServiceService;
import cn.com.bmsoft.modules.rm.entity.ServerEntity;
import cn.com.bmsoft.modules.rm.service.ServerService;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.bm.dao.StrategyDao;
import cn.com.bmsoft.modules.bm.entity.StrategyEntity;
import cn.com.bmsoft.modules.bm.service.StrategyService;
import springfox.documentation.spring.web.json.Json;


@Service("strategyService")
public class StrategyServiceImpl extends ServiceImpl<StrategyDao, StrategyEntity> implements StrategyService {
    @Autowired
    private StrategyDao strategyDao;

    @Autowired
    private ServerService serverService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String ywry = (String) params.get("ywrymc");
        List<Object> ywryids = null;
        //获取运维人员id
        if (StringUtil.isNotBlank(ywry)){
            ywryids = sysUserService.listObjs(new QueryWrapper<SysUserEntity>().select("user_id").like("name", ywry));
            if(ywryids.isEmpty()){
                IPage<SysUserEntity> page = sysUserService.page(
                        new Query<SysUserEntity>().getPage(params),
                        new QueryWrapper<SysUserEntity>().select("user_id").like("name",ywry)
                );
                PageUtils pageUtils = new PageUtils(page);
                return pageUtils;
            }
        }
        QueryWrapper<StrategyEntity> wrapper = new QueryWrapper<>();
        /*模糊查询拼接*/
        String bfmc = (String)params.get("bfmc");
        String fwqid = (String) params.get("fwqid");
        String ywfwid = (String) params.get("ywfwid");
        String status = (String)params.get("status");
        String startTime = (String)params.get("startTime");
        String endTime = (String)params.get("endTime");

        wrapper.isNull("delete_flag");
        wrapper.like(StringUtils.isNotBlank(bfmc),"bfmc",bfmc);
        wrapper.eq(StringUtils.isNotBlank(status),"status",status);
        wrapper.eq(StringUtils.isNotBlank(ywfwid),"ywfwid",ywfwid);
        wrapper.eq(StringUtils.isNotBlank(fwqid),"fwqid",fwqid);
        wrapper.in(ywryids != null && ywryids.size() > 0, "ywryid", ywryids);
        wrapper.ge(StringUtils.isNotBlank(startTime),"cjsj",startTime);
        wrapper.le(StringUtils.isNotBlank(endTime),"cjsj",endTime);

        IPage<StrategyEntity> page = this.page(
                new Query<StrategyEntity>().getPage(params),
                wrapper.select("id",
                        "bfbh",
                        "bfmc",
                        "fwqid",
                        "bflj",
                        "ywfwid",
                        "bfsdsj",
                        "cjsj",
                        "ywryid",
                        "status",
                        "(select t.name from sys_user t where t.user_id = bm_strategy.ywryid) ywrymc",
                        "(select t.sbmc from rm_server t where t.id = bm_strategy.fwqid) fwqmc",
                        "(select t.ywfwmc from bsm_service t where t.id = bm_strategy.ywfwid) ywfw")
        );
        return new PageUtils(page);
    }



    @Override
    public boolean updateBatchByIds(Long[] ids) {

        return strategyDao.updateBatchByIds(ids);
    }

    @Override
    public StrategyEntity getByStrategyId(int id) {
        StrategyEntity strategyEntity = getOne(new QueryWrapper<StrategyEntity>()
                .select("*",
                        "(select t.name from sys_user t where t.user_id = bm_strategy.ywryid) ywrymc",
                        "(select t.sbmc from rm_server t where t.id = bm_strategy.fwqid) fwqmc",
                        "(select t.ywfwmc from bsm_service t where t.id = bm_strategy.ywfwid) ywfw",
                        "(select t.original_file_name from c_attachment t where t.id = bm_strategy.fjid) fjmc")
                .eq("id", id));
        return strategyEntity;
    }

}