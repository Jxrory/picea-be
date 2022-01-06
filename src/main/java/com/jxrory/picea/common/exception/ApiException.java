package com.jxrory.picea.common.exception;

/**
 * ApiException 主要是给前端返回的异常状态
 *
 * @author Rory
 * @date 2022/1/3 下午11:52
 */
public class ApiException extends RuntimeException{

    private ExceptionHandler exceptionHandler;

    public ApiException(ExceptionHandler exceptionHandler) {
        super(exceptionHandler.getMessage());
        this.exceptionHandler = exceptionHandler;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return exceptionHandler.getCode();
    }
}