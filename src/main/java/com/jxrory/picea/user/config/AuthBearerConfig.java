package com.jxrory.picea.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author Rory
 * @date 2022/1/5 上午8:56
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "auth.bearer")
public class AuthBearerConfig implements Serializable {

    /**
     * token 版本
     */
    private String version;

    /**
     * "iss" (Issuer: 发行人) Claim
     */
    private String issuer;

    /**
     * "sub" (Subject: 主题) Claim
     */
    private String subject;

    /**
     * bearer 的有限时间
     */
    private Integer duration;

    /**
     * 秘钥
     */
    private String secretKey;
}
