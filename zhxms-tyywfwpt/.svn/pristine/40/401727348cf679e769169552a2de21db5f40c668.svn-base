package cn.com.bmsoft.service.impl;

import cn.com.bmsoft.dao.RioAppDao;
import cn.com.bmsoft.dao.RioOrganDao;
import cn.com.bmsoft.dao.RioServiceDao;
import cn.com.bmsoft.dao.RioSystemDao;
import cn.com.bmsoft.dto.RioAppDto;
import cn.com.bmsoft.dto.RioOrganDto;
import cn.com.bmsoft.dto.RioServiceDto;
import cn.com.bmsoft.dto.RioSystemDto;
import cn.com.bmsoft.entity.*;
import cn.com.bmsoft.service.RioMonitorService;
import cn.com.bmsoft.service.RioOperationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class RioOperationServiceImpl implements RioOperationService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RioOrganDao organDao;
    @Autowired
    private RioSystemDao systemDao;
    @Autowired
    private RioAppDao appDao;
    @Autowired
    private RioServiceDao serviceDao;

    @Autowired
    private RioMonitorService monitorService;


    @Override
    public List<String> insertOrganList(List<RioOrgan> organs) {
        List<String> ids = new ArrayList<>();
        for(RioOrgan organ : organs){
            organDao.insert(organ);
            ids.add(organ.getId());
        }
        return ids;
    }

    @Override
    public List<String> updateOrganList(List<RioOrgan> organs) {
        List<String> ids = new ArrayList<>();
        for(RioOrgan organ : organs){
            if(organDao.selectById(organ.getId()) != null)
                organDao.updateById(organ);
            else
                organDao.insert(organ);
            ids.add(organ.getId());
        }
        return ids;
    }

    @Override
    public void deleteOrganList(List<RioOrgan> organs) {
        QueryWrapper<RioOrgan> wrapper = new QueryWrapper<>();
        List<RioOrgan> list = organDao.selectList(wrapper);
        list.removeAll(organs);
        for(RioOrgan organ : list){
            organDao.deleteById(organ.getId());
        }
    }

    @Override
    public List<String> insertSystemList(List<RioSystem> systems) {
        List<String> ids = new ArrayList<>();
        for(RioSystem system : systems){
            systemDao.insert(system);
            ids.add(system.getId());
        }
        return ids;
    }

    @Override
    public List<String> updateSystemList(List<RioSystem> systems) {
        List<String> ids = new ArrayList<>();
        for(RioSystem system : systems){
            if(systemDao.selectById(system.getId()) != null)
                systemDao.updateById(system);
            else
                systemDao.insert(system);
            ids.add(system.getId());
        }
        return ids;
    }

    @Override
    public void deleteSystemList(List<RioSystem> systems) {
        QueryWrapper<RioSystem> wrapper = new QueryWrapper<>();
        List<RioSystem> list = systemDao.selectList(wrapper);
        list.removeAll(systems);
        for(RioSystem system : list){
            systemDao.deleteById(system.getId());
        }
    }

    @Override
    public List<String> insertAppList(List<RioApp> apps) {
        List<String> ids = new ArrayList<>();
        for(RioApp app : apps){
            appDao.insert(app);
            ids.add(app.getId());
        }
        return ids;
    }

    @Override
    public List<String> updateAppList(List<RioApp> apps) {
        List<String> ids = new ArrayList<>();
        for(RioApp app : apps){
            if(appDao.selectById(app.getId()) != null)
                appDao.updateById(app);
            else
                appDao.insert(app);
            ids.add(app.getId());
        }
        return ids;
    }

    @Override
    public void deleteAppList(List<RioApp> apps) {
        QueryWrapper<RioApp> wrapper = new QueryWrapper<>();
        List<RioApp> list = appDao.selectList(wrapper);
        list.removeAll(apps);
        for(RioApp app : list){
            appDao.deleteById(app.getId());
        }
    }

    @Override
    public List<String> insertServiceList(List<RioService> services) {
        List<String> ids = new ArrayList<>();
        for(RioService service : services){
           serviceDao.insert(service);
           ids.add(service.getId());
        }
        return ids;
    }

    @Override
    public List<String> updateServiceList(List<RioService> services) {
        List<String> ids = new ArrayList<>();
        for(RioService service : services){
            if(serviceDao.selectById(service.getId()) != null)
                serviceDao.updateById(service);
            else
                serviceDao.insert(service);
            ids.add(service.getId());
        }
        return ids;
    }

    @Override
    public void deleteServiceList(List<RioService> services) {
        QueryWrapper<RioService> wrapper = new QueryWrapper<>();
        List<RioService> list = serviceDao.selectList(wrapper);
        list.removeAll(services);
        for(RioService service : list){
            serviceDao.deleteById(service.getId());
        }
    }

    @Override
    public List<String> selectServiceIds() {
        QueryWrapper<RioService> wrapper = new QueryWrapper<>();
        List<RioService> list = serviceDao.selectList(wrapper);
        List<String> ids = new ArrayList<>();
        for(RioService service : list){
            ids.add(service.getId());
        }
        return ids;
    }

    @Override
    public void loadBaseData(String type) throws IOException {
        logger.info("开始执行腾讯里约基础数据更新...");
        //机构ids
        List<String> oIds = new ArrayList<>();
        //系统ids
        List<String> sIds = new ArrayList<>();
        //应用ids
        List<String> aIds = new ArrayList<>();

        //获取所有的机构
        List<RioOrganDto> organDtos = monitorService.getAllRioOrganList(9999);
        List<RioOrgan> organs = new ArrayList<>();
        for(RioOrganDto dto : organDtos){
            RioOrgan organ = new RioOrgan();
            organ.setId(dto.getId());
            organ.setName(dto.getName());
            organs.add(organ);
        }

        if(type.equals("insert")){
            oIds = insertOrganList(organs);
        }else {
            deleteOrganList(organs);
            oIds = updateOrganList(organs);
        }
        //获取所有的系统
        for(String oId : oIds){
            List<RioSystemDto> systemDtos = monitorService.getAllRioSystemListByOrgId(oId,2,9999);
            List<RioSystem> systems = new ArrayList<>();
            for(RioSystemDto dto : systemDtos){
                RioSystem system = new RioSystem();
                system.setId(dto.getId());
                system.setOrganId(dto.getOrgan().getId());
                system.setName(dto.getName());
                systems.add(system);
            }

            if(type.equals("insert")){
                sIds = insertSystemList(systems);
            }else {
                deleteSystemList(systems);
                sIds = updateSystemList(systems);
            }
        }
        //获取所有的应用
        if(sIds != null){
            for (String sId : sIds){
                List<RioAppDto> appDtos = monitorService.getAllRioAppsListBySysId(sId,9999);
                List<RioApp> apps = new ArrayList<>();
                for(RioAppDto dto : appDtos){
                    RioApp app = new RioApp();
                    app.setId(dto.getPaasid());
                    app.setOrganId(dto.getOrg().getId());
                    app.setSystemId(dto.getSys().getId());
                    app.setName(dto.getName());
                    app.setStatus(dto.getStatus());
                    apps.add(app);
                }

                if(type.equals("insert")){
                    aIds = insertAppList(apps);
                }else {
                    deleteAppList(apps);
                    aIds = updateAppList(apps);
                }
            }
        }
        //获取所有服务
        if(aIds != null){
            for(String aId : aIds){
                List<RioServiceDto> serviceDtos = monitorService.getRioSvcsListBypaasId(aId,9999);
                List<RioService> services = new ArrayList<>();
                for(RioServiceDto dto : serviceDtos){
                    RioService service = new RioService();
                    service.setId(dto.getSvcId());
                    service.setAppId(dto.getApp().getPaasid());
                    service.setSystemId(dto.getSys().getId());
                    service.setStatus(dto.getStatus());
                    service.setBPublic(dto.isPublic());
                    service.setName(dto.getSvcName());
                    services.add(service);
                }

                if(type.equals("insert")){
                    insertServiceList(services);
                }else {
                    deleteServiceList(services);
                    updateServiceList(services);
                }
            }
        }
        logger.info("腾讯里约基础数据更新完成");
    }


}
