package cn.com.bmsoft.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * Rio服务被调用详情
 */
@Data
public class RioSevCalledDetailDto implements Serializable {

    @JSONField(name = "cnt_success")
    private String cntSuccess;

    @JSONField(name = "cnt_err")
    private String cntErr;

    //请求 IP 列表
    private String[] ips;

    //平均耗时，单位毫秒
    @JSONField(name = "avg_duration")
    private String avgDuration;


}
