package cn.com.bmsoft.modules.rm.entity;

import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.validator.group.AddGroup;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @auther zhangyibing  zhangyibing@bmsoft.com.cn
* @Date 2019/9/27
* @desription 资源管理-链路登记
**/
@Data
@TableName("rm_link")
public class LinkEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 链路编码
     */
    @NotBlank(message="链路编码不能为空", groups = {AddGroup.class})
    private String llbm;

    /**
     * 链路名称
     */
    @NotBlank(message="链路名称不能为空", groups = {AddGroup.class})
    @Size(max = 40, message = "链路名称"+ RmParams.FIELD_OVERMAX_TIPS + 40 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
    private String llmc;

    /**
     * 带宽
     */
    @NotNull(message="带宽不能为空", groups = {AddGroup.class})
    private String dk;

    /**
     * 运维人员
     */
    @NotNull(message="运维人员不能为空", groups = {AddGroup.class})
    private Integer ywryid;
    /**
     * 运维人员姓名
     */
    @TableField(exist = false)
    private String ywry;
    /**
     * 运维人员姓名(多余字段，前端需要)
     */
    @TableField(exist = false)
    private String ywrymc;
    /**
     * 业务组ID
     */
    @TableField(exist = false)
    private List<Integer> ywzid;
    /**
     * 业务组名称
     */
    @TableField(exist = false)
    private String ywzmc;
    /**
     * 备注说明
     */
    @Size(max = 400, message = "备注说明"+ RmParams.FIELD_OVERMAX_TIPS + 400 + RmParams.FIELD_OVERMAX_TIPS2, groups = {AddGroup.class})
    private String bzsm;

    /**
     * 创建人id
     */
    private Integer cjrid;

    /**
     * 创建时间
     */
    private Date cjsj;

    /**
     * 最后修改人
     */
    private Integer xgrid;

    /**
     * 最后修改时间
     */
    private Date xgsj;

    /**
     * 是否有效
     */
    private String sfyx;
    /**
     * 是否删除
     */
    private String deleteFlag;

    /**
     * 设备信息
     */
    @TableField(exist = false)
    private List<LinkDeviceEntity> linkDevices;
}
