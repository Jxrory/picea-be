package com.jxrory.picea.user.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jxrory.picea.common.exception.ApiException;
import com.jxrory.picea.user.config.AuthBearerConfig;
import com.jxrory.picea.user.model.entity.User;
import com.jxrory.picea.user.model.enums.LoginExceptionEnum;
import com.jxrory.picea.user.model.request.LoginRequest;
import com.jxrory.picea.user.model.vo.LoginVO;
import com.jxrory.picea.user.service.LoginService;
import com.jxrory.picea.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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

        /**
         * 构建 JWT:
         *     1. "iss" (Issuer: 发行人) Claim
         *     2. "sub" (Subject: 主题) Claim
         *     3. "aud" (Audience: 用户) Claim
         *     4. "exp" (Expiration Time: 过期时间) Claim
         *     5. "nbf" (Not Before: 开始时间) Claim
         *     6. "iat" (Issued At: 发行时间) Claim
         *     7. "jti" (JWT ID) Claim
         */
        Date now = new Date();
        String token = Jwts.builder()
                .setHeaderParam("ver", authBearerConfig.getVersion())

                .setIssuer(authBearerConfig.getIssuer())
                .setSubject(authBearerConfig.getSubject())
                .setAudience(user.getUid())
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(DateUtils.addMinutes(now, authBearerConfig.getDuration()))
                .setId(UUID.fastUUID().toString(true))

                .signWith(SignatureAlgorithm.HS256, authBearerConfig.getSecretKey())
                .compact();

        return new LoginVO(token);
    }

}
