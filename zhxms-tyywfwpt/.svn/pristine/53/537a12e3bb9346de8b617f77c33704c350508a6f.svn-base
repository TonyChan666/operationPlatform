package cn.com.bmsoft.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * Rio系统Dto
 */
@Data
public class RioSystemDto implements Serializable {
    //系统 ID
    private String id;
    //系统名称
    private String name;
    //描述信息
    private String description;
    //系统状态
    //0：草稿
    //1：审批中
    //2：已同意
    //3：已拒绝
    //4：已删除
    private int status;
    //系统应用数
    @JSONField(name = "app_num")
    private int appNum;
    //所属机构
    private RioOrganDto organ;
    //负责人
    private RioOperatorDto[] operators;
    //供应商
    private RioVendorsDto[] vendors;
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

