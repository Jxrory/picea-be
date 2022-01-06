package com.jxrory.picea.user.model.request;

import com.jxrory.picea.model.enums.GenderEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户添加更新操作
 *
 * @author Rory
 * @date 2022/1/3 下午9:20
 */
@Data
public class UserCuRequest {
    /**
     * 用户名/登录名
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 用户别名, 展示名字
     */
    @NotBlank
    private String nickname;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 0 未知, 1 男, 2 女
     */
    private GenderEnum sex;

    /**
     * 头像URL
     */
    private String avatar;
}
