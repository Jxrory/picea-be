package com.jxrory.picea.user.model.enums;

import com.jxrory.picea.common.exception.ExceptionHandler;
import lombok.Getter;

/**
 * @author Rory
 * @date 2022/1/3 下午11:59
 */
@Getter
public enum LoginExceptionEnum implements ExceptionHandler {
    // 数据操作错误定义
    USERNAME_OR_PASSWORD_ERR("L00001", "用户名或密码错误"),
    ;

    private String code;
    private String message;

    LoginExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}