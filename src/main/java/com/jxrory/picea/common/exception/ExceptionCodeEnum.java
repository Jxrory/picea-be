package com.jxrory.picea.common.exception;

/**
 * @author Rory
 * @date 2022/1/3 下午11:55
 */
public enum ExceptionCodeEnum implements ExceptionHandler {
    // 数据操作错误定义
    PRAM_NOT_MATCH("400", "参数不正确"),
    VALIDATE_FAILED("400", "参数检验失败"),
    ;

    private String code;
    private String message;

    private ExceptionCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}