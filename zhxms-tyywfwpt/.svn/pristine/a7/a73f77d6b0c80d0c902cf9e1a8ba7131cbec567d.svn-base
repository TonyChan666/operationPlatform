package cn.com.bmsoft.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * Rio应用Dto
 */
@Data
public class RioAppDto implements Serializable {
    //应用标识，由英文组成
    @JSONField(name = "paas_id")
    private String paasid;
    //应用名称
    private String name;
    //描述信息
    private String description;
    //应用状态；0:启用、1:删除；
    private int status;
    //所属系统
    private RioSystemDto sys;
    //所属机构
    private RioOrganDto org;
    //负责人
    private RioOperatorDto[] operators;
    //供应商
    private RioVendorsDto[] vendors;
    //认证标识
    @JSONField(name = "connector_ids")
    private String[] connectorIds;
    //创建人
    @JSONField(name = "create_user")
    private RioOperatorDto createUser;
    //创建时间，时间戳
    private String createTime;
    //审核人
    @JSONField(name = "approval_user")
    private RioOperatorDto approvalUser;
    //审核时间，时间戳
    @JSONField(name = "approval_time")
    private String approvalTime;

}
