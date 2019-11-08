package cn.com.bmsoft.modules.rm.entity;

import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
* @auther zhangyibing  zhangyibing@bmsoft.com.cn
* @Date 2019/9/30
* @desription
**/
@Data
@TableName("rm_deployment_info")
public class DeploymentInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 资源ID
     */
    @NotBlank(message="所属资源不能为空", groups = {AddGroup.class})
    private String zyid;
    /**
     * 服务器ID
     */
    @NotBlank(message="服务器id不能为空", groups = {AddGroup.class})
    private Integer fwqid;
    /**
     * 安装服务器
     */
    @TableField(exist = false)
    private String azfwq;
    /**
     * 安装位置
     */
    @NotBlank(message="安装位置不能为空", groups = {AddGroup.class})
    @Size(max = 40, message = "安装位置"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
    private String azwz;
    /**
     * 版本号
     */
    @NotBlank(message="版本号不能为空", groups = {AddGroup.class})
    @Size(max = 20, message = "版本号"+ RmParams.FIELD_OVERMAX_TIPS + 20 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
    private String bbh;
    /**
     * 创建人id
     */
    private Integer cjrid;
    /**
     * 创建时间
     */
    private Date cjsj;
    /**
     * 修改人id
     */
    private Integer xgrid;
    /**
     * 最后修改时间
     */
    private Date xgsj;
    /**
     * 资源表名
     */
    private String zybm;
    /**
     * 端口号
     */
    private Long dkh;


}
