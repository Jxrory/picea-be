package com.jxrory.picea.common.interceptor;

import com.jxrory.picea.common.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Rory
 * @date 2022/1/7 上午12:30
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity bizExceptionHandler(HttpServletRequest request, ApiException e) {
        return new ResponseEntity<>(new ErrorMsg(e.getCode(), e.getMessage()), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity paramsBindException(HttpServletRequest request, BindException e) {
        return new ResponseEntity<>(new ErrorMsg("00001",
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
