package cn.com.bmsoft.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * Rio服务Dto
 */
@Data
public class RioServiceDto implements Serializable {
    //服务 ID
    @JSONField(name = "svc_id")
    private String svcId;
    //服务名称
    @JSONField(name = "svc_name")
    private String svcName;
    //描述信息
    private String description;
    //是否公共服务
    @JSONField(name = "is_public")
    private boolean isPublic;
    //系统
    private RioSystemDto sys;
    //应用
    private RioAppDto app;
    //标签
    private String[] labels;
    //服务状态；
    // 0: 草稿、
    // 1: 审批中、
    // 2: 已同意 、
    // 3: 已拒绝、
    // 4: 已撤回、
    // 5: 已禁用、
    // 6: 已冻结、
    // 7: 已删除
    private int status;
    //授权应用数量
    @JSONField(name = "auth_app_num")
    private int authAppNum;
    //创建人
    @JSONField(name = "create_user")
    private RioOperatorDto createUser;
    //创建时间, 时间戳
    @JSONField(name = "create_time")
    private String createTime;
    //审核人
    @JSONField(name = "approval_user")
    private RioOperatorDto approvalUser;
    //审核时间, 时间戳
    @JSONField(name = "approval_time")
    private String approvalTime;
}
