package cn.com.bmsoft.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Rio应用entity
 *       存放基础数据
 */
@Data
@TableName("l_rio_app")
public class RioApp implements Serializable {

    //应用标识，由英文组成
    @TableField("id")
    private String id;
    //机构id
    @TableField("organ_id")
    private String organId;
    //系统id
    @TableField("system_id")
    private String systemId;
    //应用名称
    @TableField("name")
    private String name;
    //描述信息
    @TableField("description")
    private String description;
    //应用状态；0:启用、1:删除；
    @TableField("status")
    private int status;
}
