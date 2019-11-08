package cn.com.bmsoft.task;

import cn.com.bmsoft.modules.job.task.ITask;
import cn.com.bmsoft.service.LogFieldValueService;
import cn.com.bmsoft.utils.DateUtil;
import cn.com.bmsoft.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.IO;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * (硬件)科来网络回溯设备
 * 获取设备分析日志-定时任务
 */
@Component("NetworkRetrospectiveTask")
public class NetworkRetrospectiveTask implements ITask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static Environment env;
    @Autowired
    public void set(Environment env) {
        this.env = env;
    }
    @Autowired
    private LogFieldValueService valueService;

    private String uri;

    private String session;

    @Override
    public void run(String params) {
        //登录用户
        try {
            getValidSession();
            //获取链路id
            List<Integer> ids = getNetlinkId();
            //获取数据
            for(Integer id : ids){
                List<Map<String,Object>> list = getAlarmLogQuery(id);
                valueService.insertMapList(list,"recall");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 身份验证-登录
     * @return session
     * @throws IOException
     */
    private void getValidSession() throws IOException {
        String username = env.getProperty("kelai.recall.username");
        String password = env.getProperty("kelai.recall.password");
        this.uri = env.getProperty("kelai.recall.uri");
        String url = this.uri + "/login?username="+username+"&password="+password;
        HttpEntity entity = HttpClientUtil.doHttpParam(null,url,"post");

        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        if(json.getInteger("login_errcode") == 0){
            this.session = json.getString("session");
        }else {
            logger.info(json.getString("login_errmsg"));
        }
    }

    /**
     * 获取接口版本号
     * @return
     * @throws IOException
     */
    private Map<String,Object> getAPIVersion() throws IOException{
        String url = this.uri + "/serviceinfo.php?session="+this.session;
        HttpEntity entity = HttpClientUtil.doHttpParam(null,url,"POST");

        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        Map<String,Object> map = new HashMap<>();
        if(json.getInteger("login_errcode") == 0){
            map.put("api_version",json.getInteger("api_version"));
            map.put("desc",json.getString("desc"));
        }else {
            logger.info(json.getString("login_errmsg"));
        }
        return map;
    }

    /**
     * 获取链路ids
     * @return
     * @throws IOException
     */
    private List<Integer> getNetlinkId() throws IOException{
        String url = this.uri + "/configget.php?session="+this.session;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","netlink");
        String jsonString = jsonObject.toJSONString();
        HttpEntity entity = HttpClientUtil.doHttpParam(jsonString,url,"POST");

        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONArray netlinks = json.getJSONArray("netlinks");
        List<Integer> ids = new ArrayList<>();
        for(int i=0;i<netlinks.size();i++){
            JSONObject net = netlinks.getJSONObject(i);
            ids.add(net.getIntValue("id"));
        }
        return ids;
    }

    /**
     * 获取警报表的日志
     * @param netlinkId 链路id
     * @return
     * @throws IOException
     */
    private List<Map<String,Object>> getAlarmLogQuery(int netlinkId) throws IOException{
        String url = this.uri + "/statsquery.php?session="+this.session;

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("table","alarm_log");
        jsonParam.put("fields",env.getProperty("kelai.recall.alarmLog.fields"));
        jsonParam.put("netlink",netlinkId);
        jsonParam.put("timeunit",0);

        long endTime = new Date().getTime();
        int num = Integer.parseInt(env.getProperty("kelai.recall.alarmLog.timeRange"));
        long startTime = DateUtil.dateRoll(new Date(), Calendar.SECOND,-1*num).getTime();

        jsonParam.put("starttime",startTime);
        jsonParam.put("endtime",endTime);

        String jsonString = jsonParam.toJSONString();
        HttpEntity entity = HttpClientUtil.doHttpParam(jsonString,url,"POST");

        List<Map<String,Object>> list = new ArrayList<>();

        JSONObject json = JSONObject.parseObject(EntityUtils.toString(entity));
        JSONArray fields = json.getJSONArray("fields");

        for(int i=0;i<fields.size();i++){
            JSONObject field = fields.getJSONObject(i);
            Map<String,Object> map = new HashMap<>();
            map.put("netlink",netlinkId);
            map.put("alarm_name",field.get("name"));
            map.put("alarm_type",field.get("type"));
            map.put("alarm_level",field.get("level"));
            map.put("alarm_description",field.get("description"));
            map.put("stat_time",field.get("stat_time"));
            map.put("trigger_time",field.get("trigger_time"));
            map.put("src_ip_addr",field.get("src_ip_addr"));
            map.put("dst_ip_addr",field.get("dst_ip_addr"));
            map.put("src_port",field.get("src_port"));
            map.put("dst_port",field.get("dst_port"));
            map.put("create_time",DateUtil.dataFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
            list.add(map);
        }
        return list;

    }



}
