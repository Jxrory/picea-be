package com.jxrory.picea.user.controller;

import com.jxrory.picea.common.utils.IpUtils;
import com.jxrory.picea.user.model.request.LoginRequest;
import com.jxrory.picea.user.model.vo.LoginVO;
import com.jxrory.picea.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Rory
 * @date 2022/1/3 下午10:24
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginVO> login(@Validated LoginRequest loginRequest, HttpServletRequest request) {
        // 设置真实 IP
        loginRequest.setRealIp(IpUtils.getClientIp(request));
        // 生成 Access Token
        return new ResponseEntity<>(loginService.login(loginRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
