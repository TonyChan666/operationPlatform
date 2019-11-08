package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.LinkDao;
import cn.com.bmsoft.modules.rm.entity.LinkDeviceEntity;
import cn.com.bmsoft.modules.rm.entity.LinkEntity;
import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;
import cn.com.bmsoft.modules.rm.service.LinkDeviceService;
import cn.com.bmsoft.modules.rm.service.LinkService;
import cn.com.bmsoft.modules.rm.service.RmResourceDeptService;
import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.utils.StringUtil;
import cn.com.bmsoft.validator.ValidatorUtils;
import cn.com.bmsoft.validator.group.AddGroup;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkDao, LinkEntity> implements LinkService  {

    @Autowired
    private LinkDeviceService linkDeviceService;

    @Autowired
    private LinkDao linkDao;

    @Autowired
    private SerialNumberService serialNumberService;

    @Autowired
    private RmResourceDeptService rmResourceDeptService;

    final static String[] SEARCH_FIELDS = {"llmc","dk","ywryid","sfyx"};

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LinkEntity> page = new Query<LinkEntity>().getPage(params);
        return new PageUtils(linkDao.getLinkList(page,params));
    }

    @Override
    @Transactional
    public R saveLink(Map<String, Object> params, SysUserEntity user) {
        LinkEntity link = JSON.parseObject(JSON.toJSONString(params), LinkEntity.class);
//        if(link.getLlbm()==null || link.getLlbm().length() != 5){
//            return R.error("请输入五位数链路编码");
//        }
        R r = serialNumberService.generator(RmParams.CODE_PREFIX.get(RmParams.LINK_ENTITY_NAME));
        link.setLlbm(r.get(RmParams.SERIAL_NUMBER)+"");
        ValidatorUtils.validateEntity(link, AddGroup.class);
        link.setCjsj(new Date());
        link.setCjrid(user.getUserId().intValue());
        List<Map<String,String>> linkDevicesList =  (List<Map<String,String>>)params.get("linkDevices");
        if(linkDevicesList.isEmpty()){
            return R.error("请至少添加一个设备");
        }
        this.save(link);
        List<LinkDeviceEntity> linkDevices = new ArrayList<LinkDeviceEntity>();
        for(Map<String,String> linkDevice : linkDevicesList){
            LinkDeviceEntity temp = JSON.parseObject(JSON.toJSONString(linkDevice), LinkDeviceEntity.class);
            temp.setLlid(link.getId());
            temp.setSbbmmc(RmParams.RM_TABLE_NAME_C.get(temp.getSbbm()));
            linkDevices.add(temp);
        }
        linkDeviceService.saveLinkDevices(linkDevices);

        List<Integer> ywzids = link.getYwzid();
        if(!ywzids.isEmpty()) {
            List<RmResourceDeptEntity> rmResourceDeptEntities = new ArrayList<>();
            for (Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDeptEntity = new RmResourceDeptEntity();
                rmResourceDeptEntity.setZyid(link.getId());
                rmResourceDeptEntity.setYwzid(ywzid);
                rmResourceDeptEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.LINK_ENTITY_NAME));
                rmResourceDeptEntities.add(rmResourceDeptEntity);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntities);
        }
        return R.ok();
    }

    @Override
    @Transactional
    public R deleteLink(Integer[] ids) {
        if(null == ids || ids.length == 0){
            return R.error("请选择要删除的链路");
        }
        List<Integer> idsList = Arrays.asList(ids);
        linkDao.removeToRecoveryByIds(idsList);
        return R.ok();
    }

    @Override
    public List<LinkDeviceEntity> getDevicesBylinkId(Integer id) {
        return linkDao.getDevicesBylinkId(id);
    }

    @Override
    public R updateLink(LinkEntity linkEntity) {
//        if(linkEntity.getLlbm() != null ) {
//            if(linkEntity.getLlbm().length() != 5) {
//                return R.error("请输入五位数存储资源编码");
//            }else{
//                //设备编号等于"LINE"+五位编码
//                linkEntity.setLlbm(RmParams.CODE_PREFIX.get(RmParams.LINK_ENTITY_NAME) + linkEntity.getLlbm());
//            }
//        }
        linkDeviceService.removeLinkDevicesByLinkId(Arrays.asList(new Integer[]{linkEntity.getId()}));
        List<LinkDeviceEntity> linkDevices = linkEntity.getLinkDevices();
        if(null != linkDevices && linkDevices.size() != 0) {
            for (LinkDeviceEntity ld : linkDevices) {
                ld.setSbbmmc(RmParams.RM_TABLE_NAME_C.get(ld.getSbbm()));
                ld.setLlid(linkEntity.getId());
            }
            linkDeviceService.saveLinkDevices(linkDevices);
        }

        String tableName = RmParams.RM_TABLE_NAME.get(RmParams.LINK_ENTITY_NAME);
        List<Integer> ywzids = linkEntity.getYwzid();
        List<RmResourceDeptEntity> rmResourceDeptEntitieList = new ArrayList<>();
        rmResourceDeptService.removeResourceDepts(linkEntity.getId()+"", tableName);
        if(!ywzids.isEmpty()){
            for(Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDept = new RmResourceDeptEntity();
                rmResourceDept.setYwzid(ywzid);
                rmResourceDept.setZyid(linkEntity.getId());
                rmResourceDept.setZybm(tableName);
                rmResourceDeptEntitieList.add(rmResourceDept);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntitieList);
        }
        this.updateById(linkEntity);
        return R.ok();
    }

    @Override
    public LinkEntity getLink(Integer id) {
        LinkEntity link = linkDao.getLink(id);
        if(link == null) {
            return null;
        }
        List<Map<String, Object>> resourceDeptNames = rmResourceDeptService.getResourceDeptNames(link.getId() + "", RmParams.RM_TABLE_NAME.get(RmParams.LINK_ENTITY_NAME));
        List<Integer> ywzids = new ArrayList<>();
        String ywzmc = "";
        for (Map<String, Object> map : resourceDeptNames) {
            ywzids.add(Integer.parseInt(map.get("DEPT_ID")+""));
            ywzmc = (StringUtil.isEmpty(ywzmc)?"":(ywzmc+",")) + (StringUtil.isEmpty(map.get("DEPT_NAME")+"")?"":map.get("DEPT_NAME"));
        }
        link.setYwzid(ywzids);
        link.setYwzmc(ywzmc);
        return link;
    }
}
