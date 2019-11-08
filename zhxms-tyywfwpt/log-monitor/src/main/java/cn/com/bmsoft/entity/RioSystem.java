package cn.com.bmsoft.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Rio系统entity
 *      存放基础数据
 */
@Data
@TableName("l_rio_system")
public class RioSystem implements Serializable {

    //系统 ID
    @TableField("id")
    private String id;
    //机构 ID
    @TableField("organ_id")
    private String organId;
    //系统名称
    @TableField("name")
    private String name;
    //描述信息
    @TableField("description")
    private String description;
    //系统状态
    //0：草稿
    //1：审批中
    //2：已同意
    //3：已拒绝
    //4：已删除
    @TableField("status")
    private int status;
    //系统应用数
    @TableField("app_num")
    private int appNum;
}
