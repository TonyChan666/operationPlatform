package cn.com.bmsoft.service;

import cn.com.bmsoft.entity.RioApp;
import cn.com.bmsoft.entity.RioOrgan;
import cn.com.bmsoft.entity.RioService;
import cn.com.bmsoft.entity.RioSystem;

import java.io.IOException;
import java.util.List;

/**
 * Rio统一数据操作Service
 */
public interface RioOperationService {

    List<String> insertOrganList(List<RioOrgan> organs);

    List<String> updateOrganList(List<RioOrgan> organs);

    void deleteOrganList(List<RioOrgan> organs);

    List<String> insertSystemList(List<RioSystem> systems);

    List<String> updateSystemList(List<RioSystem> systems);

    void deleteSystemList(List<RioSystem> systems);

    List<String> insertAppList(List<RioApp> apps);

    List<String> updateAppList(List<RioApp> apps);

    void deleteAppList(List<RioApp> apps);

    List<String> insertServiceList(List<RioService> services);

    List<String> updateServiceList(List<RioService> services);

    void deleteServiceList(List<RioService> services);

    List<String> selectServiceIds();

    void loadBaseData(String type) throws IOException;


}
