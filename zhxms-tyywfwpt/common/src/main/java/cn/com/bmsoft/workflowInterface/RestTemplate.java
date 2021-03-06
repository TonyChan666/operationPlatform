package cn.com.bmsoft.workflowInterface;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class RestTemplate {

    /*获取待办任务列表*/
    public String daibanList(Long user){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/tasks/?mark=page";
        ResponseEntity<String> entity = restTemplate.getForEntity(url ,String.class);
        String body = entity.getBody();
        return body;
    }
    /*通过用户获取待办任务列表*/
    public  Collection<String> daibanList(Long user, Integer size, Integer page){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/tasks/?mark=page&assignee="+user+"&size="+size+"&page="+page;
        ResponseEntity<String> entity = restTemplate.getForEntity(url ,String.class);
        String body = entity.getBody();
        JSONObject jsonObject = new JSONObject().parseObject(body);
        String data = jsonObject.getString("data");
        JSONObject rows = new JSONObject().parseObject(data);
        JSONArray jsonArray =rows.getJSONArray("rows");
        Collection<String> slids = new ArrayList<>();
        String slid = "";
        for (int i = 0; i < jsonArray.size();i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            slid = jsonObject1.getString("processInstanceId");//sys
            slids.add(slid);
        }
        return slids;
    }
    /*通过实例id获取待办任务,返回环节id*/
    public String daibanSlid(String processInstanceId){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/tasks/?mark=page&processInstanceId="+processInstanceId;
        ResponseEntity<String> entity = restTemplate.getForEntity(url ,String.class);
        String body = entity.getBody();
        JSONObject jsonObject = new JSONObject().parseObject(body);
        String data = jsonObject.getString("data");
        JSONObject rows = new JSONObject().parseObject(data);
        JSONArray jsonArray =rows.getJSONArray("rows");
        String wjid = "";
        for (int i = 0; i < jsonArray.size();i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            wjid = jsonObject1.getString("id");//sys
        }
        return wjid;
    }
    /*查看流程图  二进制流*/
    public void viewFlowChart(){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String processInstanceId = "2530";
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/process-instances/"+processInstanceId+"/diagram";
        ResponseEntity<String> entity = restTemplate.getForEntity(url ,String.class);
        String body = entity.getBody();
        System.out.println(body);
    }
    /*流程启动*/
    public  Map<String, Object> run( HashMap<String, Object> map){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/process-instances";
        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(map);
        ResponseEntity<String> entity = restTemplate.postForEntity(url,request,String.class,map);
        String body = entity.getBody();
        System.out.println(body);
        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();
        result = gson.fromJson(body, result.getClass());
        return result;
    }
    /*任务回退*/
    public String rollback(String taskId){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        HashMap<String, String> map = new HashMap<>();
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/tasks/"+taskId+"/?mark=callback";
        HttpEntity<HashMap<String, String>> request = new HttpEntity<>(map);
        ResponseEntity<String> entity = restTemplate.postForEntity(url,request,String.class,map);
        String body = entity.getBody();

        return body;
    }
    /*任务回撤*/
    public String revoke(String taskId){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        HashMap<String, String> map = new HashMap<>();
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/tasks/"+taskId+"/?mark=revoke";
        HttpEntity<HashMap<String, String>> request = new HttpEntity<>(map);
        ResponseEntity<String> entity = restTemplate.postForEntity(url,request,String.class,map);
        String body = entity.getBody();
        System.out.println(body);
        return body;
    }
    /*任务转派*/
    public void  transfer( HashMap<String, Object> map, String taskId ){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/tasks/"+taskId+"/?mark=transfer";
        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(map);
        restTemplate.put(url,request,String.class,map);
    }
    /*任务完成/认领/委派*/
    public Map<String, Object>  completeOrclaim(HashMap<String, Object> map,String taskId ) {
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/tasks/"+taskId;
        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(map);
        ResponseEntity<String> entity = restTemplate.postForEntity(url,request,String.class,map);
        String body = entity.getBody();
        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();
        result = gson.fromJson(body, result.getClass());
        return result;
    }
    /*取消认领*/
    public void cancelClaim(String taskId ) {
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String url = "http://10.194.186.222:9601/bpm-platform/runtime/tasks/"+taskId+"?mark=cancelClaim";
        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(null);
        restTemplate.put(url,request,String.class,null);
    }
    /*流程模板列表*/
    public Map<String, Object> flowchartTemplateList(Map<String, Object> params){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String url = "http://10.194.186.222:9601/bpm-platform/models/?mark=page&page="+Integer.valueOf(params.get("page").toString())+"&size="+Integer.valueOf(params.get("size").toString());
        ResponseEntity<String> entity = restTemplate.getForEntity(url ,String.class);
        String body = entity.getBody();
        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();
        result = gson.fromJson(body, result.getClass());
        return result;
    }


    /*新增流程模板*/
    public  Map<String, Object> addFlowchartTemplate(){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        HashMap<String, String> map = new HashMap<>();
        String url = "http://10.194.186.222:9601/bpm-platform/models";
        HttpEntity<HashMap<String, String>> request = new HttpEntity<>(map);
        ResponseEntity<String> entity = restTemplate.postForEntity(url,request,String.class,map);
        String body = entity.getBody();
        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();
        result = gson.fromJson(body, result.getClass());
        return result;
    }
    /*发布流程*/
    public Map<String, Object> fabuFlowchartTemplate(int id){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        HashMap<String, String> map = new HashMap<>();
        String url = "http://10.194.186.222:9601/bpm-platform/models/"+id+"/deployments";
        HttpEntity<HashMap<String, String>> request = new HttpEntity<>(map);
        ResponseEntity<String> entity = restTemplate.postForEntity(url,request,String.class,map);
        String body = entity.getBody();
        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();
        result = gson.fromJson(body, result.getClass());
        return result;
    }
    /*删除流程模板*/
    public  Map<String, Object>  deleteFlowchartTemplate(int id){
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        HashMap<String, String> map = new HashMap<>();
        String url = "http://10.194.186.222:9601/bpm-platform/models/"+id+"/";
        HttpEntity<HashMap<String, String>> request = new HttpEntity<>(map);
        String entity = restTemplate.execute(url, HttpMethod.DELETE,null,null,map);
        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<String, Object>();
        result = gson.fromJson(entity, result.getClass());
        return result;
    }
}
