package com.jxrory.picea.user.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jxrory.picea.common.exception.ApiException;
import com.jxrory.picea.user.config.AuthBearerConfig;
import com.jxrory.picea.user.model.dto.TokenPayloadDTO;
import com.jxrory.picea.user.model.entity.User;
import com.jxrory.picea.user.model.enums.LoginExceptionEnum;
import com.jxrory.picea.user.model.request.LoginRequest;
import com.jxrory.picea.user.model.vo.LoginVO;
import com.jxrory.picea.user.service.LoginService;
import com.jxrory.picea.user.service.UserService;
import com.jxrory.picea.user.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Rory
 * @date 2022/1/3 下午11:27
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthBearerConfig authBearerConfig;

    @Resource
    private UserService userService;

    @Override
    public LoginVO login(LoginRequest request) {
        // 1. 获取用户
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, request.getUsername()));
        if (user == null) {
            throw new ApiException(LoginExceptionEnum.USERNAME_OR_PASSWORD_ERR);
        }

        // 2. 校验密码
        String password = SecureUtil.hmacMd5(user.getPwSalt()).digestHex(request.getPassword());
        if (!password.equals(user.getPassword())) {
            throw new ApiException(LoginExceptionEnum.USERNAME_OR_PASSWORD_ERR);
        }

        // 3. 生成token
        String token = JwtUtil.createToken(
                new TokenPayloadDTO(authBearerConfig.getIssuer(), authBearerConfig.getSubject(), user.getUid(), authBearerConfig.getDuration()),
                authBearerConfig.getSecretKey());

        return new LoginVO(token);
    }

}
