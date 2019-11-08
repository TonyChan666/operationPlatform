package cn.com.bmsoft.modules.epm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by wgl on 2019/9/21.
 */
@Data
@TableName("epm_plan")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="EmergencePlanEntityExtend对象", description="应急预案表查询参数")

public class EmergencePlanEntityExtend {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "预案制定编号")
    private String yjyabh;//唯一性，YAZD+随机5位数

    @ApiModelProperty(value = "应急预案名称")
    private String yjyamc;

    @ApiModelProperty(value = "应急类型")
    private String yjlx;

    @ApiModelProperty(value = "应急响应级别")
    private String yjxyjb;

    @ApiModelProperty(value = "预案内容描述")
    private String yanrms;

    @ApiModelProperty(value = "预案状态")
    private String yazt;

    @ApiModelProperty(value = "是否已评审")
    private String sfyps;

    @ApiModelProperty(value = "是否已评估")
    private String sfypg;

    @ApiModelProperty(value = "附件id")
    private String fjid;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后修改人")
    private String updateUser;

    @ApiModelProperty(value = "最后修改时间(用于预案派发时间帅选)")
    private Date updateTime;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "评审人员")
    private String reviewUser;

    @ApiModelProperty(value = "评估人员")
    private String assessUser;

    @ApiModelProperty(value = "附件名称")
    private String name;

    @ApiModelProperty(value = "派发任务状态")
    private String taskStatus;

    @TableField(exist=false)
    @ApiModelProperty(value = "运维人员名字集")
    private String reviewUserId;

    @ApiModelProperty(value = "是否删除")
    private String deleteFlag; //是否删除（默认为null,删除是更新为0）

    @TableField(exist=false)
    @ApiModelProperty(value = "管理人员和运维组长是否具有评审，评估标识 false代表没有，true代表有")
    private boolean isOperate;

    @TableField(exist=false)
    @ApiModelProperty(value = "是否已完成评审或者评估操作")
    private boolean hasOperate;

    @TableField(exist=false)
    @ApiModelProperty(value = "评审或者评估的标识(主要用于应急预案首页区分评审和评估)")
    private String rwmc;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

