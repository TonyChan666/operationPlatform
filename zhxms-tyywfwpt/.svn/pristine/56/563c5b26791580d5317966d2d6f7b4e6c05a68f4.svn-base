package cn.com.bmsoft.service.impl;

import cn.com.bmsoft.dto.*;
import cn.com.bmsoft.service.RioMonitorService;
import cn.com.bmsoft.utils.DateUtil;
import cn.com.bmsoft.utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RioMonitorServiceImpl implements RioMonitorService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static Environment env;
    @Autowired
    public void set(Environment env) {
        this.env = env;
    }

    @Override
    public List<RioOrganDto> getAllRioOrganList(int limit) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/omp/agency/list"
                +"?version="+env.getProperty("tencent.rio.version")
                +"&limit="+limit;
        HttpEntity entity = HttpClientUtil.doHttpParam(null,uri,"GET");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        JSONArray items = data.getJSONArray("items");
        List<RioOrganDto> rioOrgans = new ArrayList<>();
        for(int i = 0;i < items.size();i ++){
            JSONObject item = items.getJSONObject(i);
            RioOrganDto organ = JSON.toJavaObject(item, RioOrganDto.class);
            rioOrgans.add(organ);
        }
        return rioOrgans;
    }

    @Override
    public List<RioSystemDto> getAllRioSystemListByOrgId(String orgId, int status, int limit) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/amp/system/list/getbyorganization"
                +"?version="+env.getProperty("tencent.rio.version")
                +"&limit="+limit
                +"&org_id="+orgId
                +"system_status="+status;
        HttpEntity entity = HttpClientUtil.doHttpParam(null,uri,"GET");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        JSONArray items = data.getJSONArray("items");
        List<RioSystemDto> rioSystems = new ArrayList<>();
        for(int i = 0;i < items.size();i ++){
            JSONObject item = items.getJSONObject(i);
            RioSystemDto system = JSON.toJavaObject(item, RioSystemDto.class);
            rioSystems.add(system);
        }
        return rioSystems;
    }

    @Override
    public List<RioAppDto> getAllRioAppsListBySysId(String sysId, int limit) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/amp/app/list"
                +"?version="+env.getProperty("tencent.rio.version")
                +"&pageSize="+limit
                +"&sysid="+sysId;
        HttpEntity entity = HttpClientUtil.doHttpParam(null,uri,"GET");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        JSONArray items = data.getJSONArray("apps");
        List<RioAppDto> rioApps = new ArrayList<>();
        for(int i = 0;i < items.size();i ++){
            JSONObject item = items.getJSONObject(i);
            RioAppDto app = JSON.toJavaObject(item, RioAppDto.class);
            rioApps.add(app);
        }
        return rioApps;
    }

    @Override
    public List<RioServiceDto> getPublicRioSvcsList(int limit) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/amp/app/service/public/list"
                +"?version="+env.getProperty("tencent.rio.version")
                +"&pageSize="+limit;
        HttpEntity entity = HttpClientUtil.doHttpParam(null,uri,"GET");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        JSONArray items = data.getJSONArray("items");
        List<RioServiceDto> rioSvcs = new ArrayList<>();
        for(int i = 0;i < items.size();i ++){
            JSONObject item = items.getJSONObject(i);
            RioServiceDto svc = JSON.toJavaObject(item, RioServiceDto.class);
            rioSvcs.add(svc);
        }
        return rioSvcs;
    }

    @Override
    public List<RioServiceDto> getRioSvcsListBypaasId(String paasId, int limit) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/amp/app/service/list"
                +"?version="+env.getProperty("tencent.rio.version")
                +"&limit="+limit
                +"&paas_id="+paasId;
        HttpEntity entity = HttpClientUtil.doHttpParam(null,uri,"GET");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        JSONArray items = data.getJSONArray("items");
        List<RioServiceDto> rioSvcs = new ArrayList<>();
        for(int i = 0;i < items.size();i ++){
            JSONObject item = items.getJSONObject(i);
            RioServiceDto svc = JSON.toJavaObject(item, RioServiceDto.class);
            rioSvcs.add(svc);
        }
        return rioSvcs;
    }


    @Override
    public List<RioServiceDto> getOnFileRioSvcsListBySysId(String paasId, int limit) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/amp/app/service/arch/list"
                +"?version="+env.getProperty("tencent.rio.version")
                +"&limit="+limit
                +"&paas_id="+paasId;
        HttpEntity entity = HttpClientUtil.doHttpParam(null,uri,"GET");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        JSONArray items = data.getJSONArray("items");
        List<RioServiceDto> rioSvcs = new ArrayList<>();
        for(int i = 0;i < items.size();i ++){
            JSONObject item = items.getJSONObject(i);
            RioServiceDto svc = JSON.toJavaObject(item, RioServiceDto.class);
            rioSvcs.add(svc);
        }
        return rioSvcs;
    }

    @Override
    public List<RioServiceDto> getSubscribeRioSvcsListBySysId(String paasId, int limit) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/amp/app/service/arch/list"
                +"?version="+env.getProperty("tencent.rio.version")
                +"&limit="+limit
                +"&paas_id="+paasId;
        HttpEntity entity = HttpClientUtil.doHttpParam(null,uri,"GET");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        JSONArray items = data.getJSONArray("items");
        List<RioServiceDto> rioSvcs = new ArrayList<>();
        for(int i = 0;i < items.size();i ++){
            JSONObject item = items.getJSONObject(i);

            RioServiceDto svc = JSON.toJavaObject(item, RioServiceDto.class);
            rioSvcs.add(svc);
        }
        return rioSvcs;
    }

    @Override
    public RioSevCalledDetailDto getSevFrequencyCount(RioSevParamMap param) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/op/service/invocation/frequency/get"
                +"?version="+env.getProperty("tencent.rio.version");
        String jsonString = JSON.toJSONString(param);
        HttpEntity entity = HttpClientUtil.doHttpParam(jsonString,uri,"POST");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        RioSevCalledDetailDto detail = JSON.toJavaObject(data, RioSevCalledDetailDto.class);
        return detail;
    }

    @Override
    public RioSevCalledDetailDto getSevReqFrequencyCount(RioSevParamMap param) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/op/service/request/frequency/get"
                +"?version="+env.getProperty("tencent.rio.version");
        String jsonString = JSON.toJSONString(param);
        HttpEntity entity = HttpClientUtil.doHttpParam(jsonString,uri,"POST");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        RioSevCalledDetailDto detail = JSON.toJavaObject(data, RioSevCalledDetailDto.class);
        return detail;
    }

    @Override
    public RioSevCalledDetailDto getSevIpCount(RioSevParamMap param, int limit) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/op/service/request/ipnumber/get"
                +"?version="+env.getProperty("tencent.rio.version");
        String jsonString = JSON.toJSONString(param);
        HttpEntity entity = HttpClientUtil.doHttpParam(jsonString,uri,"POST");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        RioSevCalledDetailDto detail = JSON.toJavaObject(data, RioSevCalledDetailDto.class);
        return detail;
    }

    @Override
    public RioSevCalledDetailDto getSevResTime(RioSevParamMap param) throws IOException {
        String uri = env.getProperty("tencent.rio.url")+"/ebus/op/service/response/time/get"
                +"?version="+env.getProperty("tencent.rio.version");
        String jsonString = JSON.toJSONString(param);
        HttpEntity entity = HttpClientUtil.doHttpParam(jsonString,uri,"POST");
        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONObject data = json.getJSONObject("data");

        RioSevCalledDetailDto detail = JSON.toJavaObject(data, RioSevCalledDetailDto.class);
        return detail;
    }

    @Override
    public List<Map<String, Object>> getRioMonitorDataBySvcIds(List<String> ids) throws IOException {
        logger.info("开始获取服务监控数据...");
        List<Map<String, Object>> list = new ArrayList<>();

        RioSevParamMap paramMap = new RioSevParamMap();
        String[] svcId = new String[1];

        for(String id : ids){

            Map<String,Object> map = new HashMap<>();

            String endTime = new Date().getTime()+"";
            int num = Integer.parseInt(env.getProperty("tencent.rio.timeRange"));
            String startTime = DateUtil.dateRoll(new Date(),Calendar.SECOND,-1*num).getTime()+"";

            svcId[0] = id;
            paramMap.setSvc_id(svcId);
            paramMap.setStart_time(startTime);
            paramMap.setEnd_time(endTime);

            RioSevCalledDetailDto dto = getSevFrequencyCount(paramMap);
            map.put("call_success",dto.getCntSuccess());
            map.put("call_err",dto.getCntErr());

            dto = getSevReqFrequencyCount(paramMap);
            map.put("request_success",dto.getCntSuccess());
            map.put("request_err",dto.getCntErr());

            dto = getSevResTime(paramMap);
            map.put("avg_duration",dto.getAvgDuration());

            map.put("create_time",DateUtil.dataFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));

            list.add(map);
        }
        logger.info("获取服务监控数据完成");
        return list;
    }
}
