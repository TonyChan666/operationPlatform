package cn.com.bmsoft.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 系统验证码
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@Data
@TableName("sys_captcha")
public class SysCaptchaEntity {

    @TableId(type = IdType.INPUT)
    private String uuid;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private Date expireTime;

}
