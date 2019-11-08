package cn.com.bmsoft.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_user_dept")
public class SysUserDeptEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 业务组id
     */
    private Integer deptId;

    /**
     * 业务组名称
     */
    @TableField(exist = false)
    private String deptName;
}
