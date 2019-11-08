package cn.com.bmsoft.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Rio服务entity
 *       存放基础数据
 */
@Data
@TableName("l_rio_service")
public class RioService implements Serializable {

    //服务 ID
    @TableField("id")
    private String id;
    //服务名称
    @TableField("name")
    private String name;
    //描述信息
    @TableField("description")
    private String description;
    //是否公共服务
    @TableField("b_public")
    private boolean bPublic;
    //系统
    @TableField("system_id")
    private String systemId;
    //应用
    @TableField("app_id")
    private String appId;
    //标签(逗号隔开)
    @TableField("labels")
    private String labels;
    //服务状态；
    // 0: 草稿、
    // 1: 审批中、
    // 2: 已同意 、
    // 3: 已拒绝、
    // 4: 已撤回、
    // 5: 已禁用、
    // 6: 已冻结、
    // 7: 已删除
    @TableField("status")
    private int status;
    //授权应用数量
    @TableField("auth_app_num")
    private int authAppNum;
}
