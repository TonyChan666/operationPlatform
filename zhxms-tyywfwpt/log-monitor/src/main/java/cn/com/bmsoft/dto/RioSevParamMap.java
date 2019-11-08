package cn.com.bmsoft.dto;

import lombok.Data;

@Data
public class RioSevParamMap {
    //机构 ID
    private String org_id;
    //系统 ID
    private String sys_id;
    //应用 ID
    private String[] pass_id;
    //服务 ID
    private String[] svc_id;
    //服务发布路径
    private String pub_path;
    //开始时间，时间戳
    private String start_time;
    //结束时间，时间戳
    private String end_time;


}
