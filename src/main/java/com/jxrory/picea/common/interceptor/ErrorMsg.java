package com.jxrory.picea.common.interceptor;

import lombok.Data;

/**
 * @author Rory
 * @date 2022/1/7 上午12:42
 */
@Data
public class ErrorMsg {

    public ErrorMsg(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;
}
