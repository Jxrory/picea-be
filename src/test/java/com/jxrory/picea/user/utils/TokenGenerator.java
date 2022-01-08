package com.jxrory.picea.user.utils;

import cn.hutool.core.lang.UUID;
import com.jxrory.picea.user.config.AuthBearerConfig;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @author Rory
 * @date 2022/1/8 下午10:22
 */
@Slf4j
@SpringBootTest
public class TokenGenerator {

    @Resource
    private AuthBearerConfig authBearerConfig;

    /**
     * 构建 JWT:
     * 1. "iss" (Issuer: 发行人) Claim
     * 2. "sub" (Subject: 主题) Claim
     * 3. "aud" (Audience: 用户) Claim
     * 4. "exp" (Expiration Time: 过期时间) Claim
     * 5. "nbf" (Not Before: 开始时间) Claim
     * 6. "iat" (Issued At: 发行时间) Claim
     * 7. "jti" (JWT ID) Claim
     */
    @Test
    public void createToken() {
        Date now = new Date();
        String token = crateToken(now, "123456");
        log.debug("token={}", token);
    }

    @Test
    void parseToken() {
        Date date = DateUtils.addDays(new Date(), -30);
        String token = crateToken(date, "0ef5b1811a1700f8cfcb97082df1ad58");
//        String token = "1234567";
        log.debug("token={}", token);

        Claims jwtBody = null;
        try {
            jwtBody = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(authBearerConfig.getSecretKey()))
                    .parseClaimsJws(token).getBody();
            log.debug("{}", jwtBody);
        } catch (ExpiredJwtException e) {
            // token 过期需要重新登录
            log.warn("token 过期需要重新登录 token={}", token);
        } catch (Exception err) {
            log.error("err:", err);
        }
    }

    String crateToken(Date now, String userId) {
        return Jwts.builder()
                .setHeaderParam("ver", authBearerConfig.getVersion())

                .setIssuer(authBearerConfig.getIssuer())
                .setSubject(authBearerConfig.getSubject())
                .setAudience(userId)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(DateUtils.addMinutes(now, authBearerConfig.getDuration()))
                .setId(UUID.fastUUID().toString(true))

                .signWith(SignatureAlgorithm.HS256, authBearerConfig.getSecretKey())
                .compact();
    }
}
