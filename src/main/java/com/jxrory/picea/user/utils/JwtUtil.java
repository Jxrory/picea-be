package com.jxrory.picea.user.utils;

import com.alibaba.fastjson.JSON;
import com.jxrory.picea.user.model.dto.TokenPayloadDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * JWT Util
 * 协议标准: https://tools.ietf.org/html/rfc7519
 *
 * @author Rory
 * @date 2022/1/4 上午9:44
 */
@Slf4j
@Component
public class JwtUtil {


    /**
     * token 的版本号
     */
    private static String tokenVersion = "1";

    /**
     * 构建 JWT:
     * 已经声明的字段:
     * 1. "iss" (Issuer: 发行人) Claim
     * 2. "sub" (Subject: 主题) Claim
     * 3. "aud" (Audience: 用户) Claim
     * 4. "exp" (Expiration Time: 过期时间) Claim
     * 5. "nbf" (Not Before: 开始时间) Claim
     * 6. "iat" (Issued At: 发行时间) Claim
     * 7. "jti" (JWT ID) Claim
     *
     * @param payloadDto payload 数据
     * @param secretKey  加密key
     * @return token
     */
    public static String createToken(TokenPayloadDTO payloadDto, String secretKey) {

        return Jwts.builder()
                .setHeaderParam("ver", tokenVersion)

//                .setIssuer(issuer)
//                .setSubject(subject)
//                .setAudience(audience)
//                .setExpiration(DateUtils.addMinutes(now, duration))
//                .setNotBefore(now)
//                .setIssuedAt(now)
//                .setId(UUID.fastUUID().toString(true))

                .setPayload(JSON.toJSONString(payloadDto))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * jwt解密，需要密钥和token，如果解密失败，说明token无效
     *
     * @param token     token
     * @param secretKey secretKey
     * @return Claims
     */
    public static Claims parseToken(String token, String secretKey) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException ex) {
            return null;
        }
    }
}
