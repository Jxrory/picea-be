package com.jxrory.picea.user.model.request;

import com.jxrory.picea.common.constants.RegularConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Rory
 * @date 2022/1/3 下午10:38
 */
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID=1L;

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = RegularConstants.USER_NAME, message = "用户名格式错误")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = RegularConstants.PASSWORD, message = "密码格式错误，密码必须以字母开头，长度在6~18之间，只能包含字符、数字和下划线")
    private String password;

    private String realIp;
}