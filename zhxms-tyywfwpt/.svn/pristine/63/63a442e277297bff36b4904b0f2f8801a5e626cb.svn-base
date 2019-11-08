package cn.com.bmsoft.modules.wom.service.impl;

import cn.com.bmsoft.modules.bm.entity.CheckLogEntity;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.modules.sys.service.SysUserService;
import cn.com.bmsoft.utils.Constant;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.wom.dao.InformationDao;
import cn.com.bmsoft.modules.wom.entity.InformationEntity;
import cn.com.bmsoft.modules.wom.service.InformationService;
import org.springframework.transaction.annotation.Transactional;


@Service("informationService")
public class InformationServiceImpl extends ServiceImpl<InformationDao, InformationEntity> implements InformationService {
     @Autowired
    private InformationDao informationDao;
    @Autowired
    private SysUserService sysUserService;

    Map<Long, String> sysUserMap = new HashMap<>();
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<InformationEntity> wrapper = new QueryWrapper<>();
          /*模糊查询拼接*/
        String cjr = (String)params.get("cjr");
        Long roleId  = Long.valueOf(params.get("roleId").toString());
        Long role  = Long.valueOf(params.get("role").toString());
        List<Object> cjrids = null;
        //获取运维人员id
        if (StringUtil.isNotBlank(cjr)) {
            QueryWrapper sysUser = new QueryWrapper<SysUserEntity>().select("user_id").like("name", cjr);
            cjrids = sysUserService.listObjs(sysUser);
            if (cjrids.isEmpty()) {
                IPage<SysUserEntity> page = sysUserService.page(
                        new Query<SysUserEntity>().getPage(params),
                        sysUser
                );
                PageUtils pageUtils = new PageUtils(page);
                return pageUtils;
            }
            params.put("cjrids",cjrids);
        }

        IPage<InformationEntity> page = null;
        if (roleId == Constant.OPERATIONS_GROUP_LEADER_ROLE ) {  //和运维组长
            params.put("ywryid",role);
            params.put("cjrid",role);
            page = informationDao.ywzzList(
                    new Query<InformationEntity>().getPage(params),
                    params
            );
        }else{
            params.put("ywryid",role);
            page = informationDao.ptywryList(
                    new Query<InformationEntity>().getPage(params),
                    params
            );
        }

       /* wrapper.like(StringUtils.isNotBlank(gdbt),"gdbt",gdbt);
        wrapper.like(StringUtils.isNotBlank(gdlx),"gdlx",gdlx);
        wrapper.like(StringUtils.isNotBlank(yxj),"yxj",yxj);
        wrapper.like(StringUtils.isNotBlank(gdzt),"gdzt",gdzt);
        wrapper.in(cjrids != null && cjrids.size()>0, "cjrid",cjrids);
        wrapper.isNull("delete_flag");
        wrapper.ge(StringUtils.isNotBlank(startTime),"cjsj",startTime);
        wrapper.le(StringUtils.isNotBlank(endTime),"cjsj",endTime);
        wrapper.orderByDesc("cjsj");*/

       /* IPage<InformationEntity> page = informationDao.ywzzList(
                new Query<InformationEntity>().getPage(params),
                wrapper
        );*/
       /* getByRecord();
        List<InformationEntity> records = page.getRecords();
        for(InformationEntity record: records ){
            setRecord(record);
        }*/
       // page.setRecords(records);
        return new PageUtils(page);
    }
    @Override
    public PageUtils getDaiChuLiList(Map<String, Object> params,Collection<String> list) {
        IPage<InformationEntity> page = informationDao.getDaiChuLiList(
                new Query<InformationEntity>().getPage(params),params,list
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils getDaiBanPageList(Map<String, Object> params) {
    InformationEntity informationEntity = (InformationEntity)params.get("informationEntity");
    LambdaQueryWrapper<InformationEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(InformationEntity::getSfyx,"1");
        queryWrapper.eq(InformationEntity::getGdzt,"");//代办状态
        queryWrapper.eq(InformationEntity::getGdbt,informationEntity.getGdbt());//工单标题
        queryWrapper.eq(InformationEntity::getGdlx,informationEntity.getGdlx()); //工单类型
        queryWrapper.eq(InformationEntity::getYxj,informationEntity.getYxj()); //工单优先级
        if(informationEntity.getXgsj()!=null){
            queryWrapper.ge(InformationEntity::getXgsj,"");//提交时间  >=
        }
        if(informationEntity.getXgsj()!=null){

        }
        IPage<InformationEntity> page = this.page(
                new Query<InformationEntity>().getPage(params),
                new QueryWrapper<InformationEntity>()
        );
        getByRecord();
        List<InformationEntity> records = page.getRecords();
        for(InformationEntity record: records ){
            setRecord(record);
        }
        page.setRecords(records);
        return new PageUtils(page);
    }

    @Override
    public PageUtils getDaiBanLiList(Map<String, Object> params) {

        return null;
    }

    /*@Override
    public PageUtils getdaiShengHePageList(Map<String, Object> params, Collection<String> list) {
        IPage<InformationEntity> page = informationDao.getdaiShengHePageList(
                new Query<InformationEntity>().getPage(params),params,list
        );
        return new PageUtils(page);
    }*/

    @Override
    public PageUtils getDaiPaiFaPageList(Map<String, Object> params, Collection<String> list) {
        IPage<InformationEntity> page = informationDao.getDaiPaiFaPageList(
                new Query<InformationEntity>().getPage(params),params,list
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils getDaiBanJiePageList(Map<String, Object> params) {
        IPage<InformationEntity> page = informationDao.getDaiBanJiePageList(
                new Query<InformationEntity>().getPage(params),params
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils getShenQingPageList(Map<String, Object> params, Long user) {

        IPage<InformationEntity> page = this.page(
                new Query<InformationEntity>().getPage(params),
                new QueryWrapper<InformationEntity>()
                .eq("cjrid",user)
                .eq("sfyx","1")
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void deleteByIds(Long[] ids) {
        informationDao.deleteInformation(ids);
        informationDao.deleteProcess(ids);
        informationDao.deleteHandleInformation(ids);
    }

    @Override
    public InformationEntity getInformationById(int id) {
        return informationDao.getInformationById(id);
    }

    @Override
    public PageUtils getdaiShengHePageList(Map<String, Object> params) {
        IPage<InformationEntity> page = informationDao.getdaiShengHePageList(
                new Query<InformationEntity>().getPage(params),params
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils getDaiPaiFaPageList(Map<String, Object> params) {
        IPage<InformationEntity> page = informationDao.getDaiPaiFaPageList(
                new Query<InformationEntity>().getPage(params),params
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils warnList(Map<String, Object> params) {

        IPage<InformationEntity> page = informationDao.warnList(
                new Query<InformationEntity>().getPage(params),
                params
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils home(Map<String, Object> params) {
        IPage<InformationEntity> page = informationDao.home(
                new Query<InformationEntity>().getPage(params),
                params
        );
        return new PageUtils(page);
    }
    //获取具体字段值
    public void setRecord(InformationEntity record){
        record.setCjr(sysUserMap.get(record.getCjrid()));
    }
    //获取实体map
    public void getByRecord(){
        sysUserService.list().forEach(sysUserEntity -> {
            sysUserMap.put(sysUserEntity.getUserId(), sysUserEntity.getName());
        });
    }
}