package com.jxrory.picea.common.exception;

/**
 * @author Rory
 * @date 2022/1/3 下午11:44
 */
public interface ExceptionHandler {

    /**
     * 获取异常code
     *
     * @return 异常code
     */
    String getCode();

    /**
     * 获取异常信息
     *
     * @return 异常信息
     */
    String getMessage();
}
