package cn.com.bmsoft.modules.sys.form;

import cn.com.bmsoft.validator.group.AddGroup;
import cn.com.bmsoft.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 密码表单
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@Data
public class PasswordForm {

    /**
     * 原密码
     */
    private String password;

    /**
     * 新密码
     */
    @NotBlank(message="新密码不能为空", groups = {UpdateGroup.class})
    @Pattern(regexp="^.{8,}$", message="密码长度不能少于8位", groups = {UpdateGroup.class})
    private String newPassword;

}
