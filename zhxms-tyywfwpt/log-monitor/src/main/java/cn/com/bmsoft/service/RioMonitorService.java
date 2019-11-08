package cn.com.bmsoft.service;

import cn.com.bmsoft.dto.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface RioMonitorService {
    /**
     * 获取所有的机构列表
     * @param limit 条数
     * @return
     */
    List<RioOrganDto> getAllRioOrganList(int limit) throws IOException;
    /**
     * 根据机构Id获取系统列表
     * @param orgId 机构id
     * @param status 系统状态
     * @param limit 条数
     * @return
     */
    List<RioSystemDto> getAllRioSystemListByOrgId(String orgId, int status, int limit) throws IOException;
    /**
     * 根据系统id获取应用列表
     * @param sysId 系统id
     * @param limit 条数
     * @return
     */
    List<RioAppDto> getAllRioAppsListBySysId(String sysId, int limit) throws IOException;
    /**
     * 获取公开服务列表
     * @param limit 条数
     * @return
     */
    List<RioServiceDto> getPublicRioSvcsList(int limit) throws IOException;

    /**
     * 获取某一应用下服务列表
     * @param paasId 应用id
     * @param limit
     * @return
     */
    List<RioServiceDto> getRioSvcsListBypaasId(String paasId, int limit) throws IOException;
    /**
     * 根据应用id获取已经归档服务列表
     * @param paasId 应用id
     * @param limit 条数
     * @return
     */
    List<RioServiceDto> getOnFileRioSvcsListBySysId(String paasId, int limit) throws IOException;
    /**
     * 获取某一应用已订阅第三方服务列表
     * @param paasId 应用id
     * @param limit 条数
     * @return
     */
    List<RioServiceDto> getSubscribeRioSvcsListBySysId(String paasId, int limit) throws IOException;

    /**
     * 获取服务被调用次数
     * @param param
     * @return
     * @throws IOException
     */
    RioSevCalledDetailDto getSevFrequencyCount(RioSevParamMap param) throws IOException;

    /**
     * 获取请求第三方服务次数
     * @param param
     * @return
     * @throws IOException
     */
    RioSevCalledDetailDto getSevReqFrequencyCount(RioSevParamMap param) throws IOException;

    /**
     * 获取服务的请求方 ip 数目
     * @param param
     * @param limit 最大多少条
     * @return
     * @throws IOException
     */
    RioSevCalledDetailDto getSevIpCount(RioSevParamMap param, int limit) throws IOException;

    /**
     * 获取服务被调用的响应时长
     * @param param
     * @return
     * @throws IOException
     */
    RioSevCalledDetailDto getSevResTime(RioSevParamMap param) throws IOException;

    /**
     * 获取响应日志数据
     * @param ids 服务ids
     * @return
     * @throws IOException
     */
    List<Map<String,Object>> getRioMonitorDataBySvcIds(List<String> ids) throws IOException;

}
