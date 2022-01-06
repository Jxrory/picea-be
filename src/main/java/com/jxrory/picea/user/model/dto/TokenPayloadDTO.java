package com.jxrory.picea.user.model.dto;

import cn.hutool.core.lang.UUID;
import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @author Rory
 * @date 2022/1/4 下午9:59
 */
@Data
public class TokenPayloadDTO {

    public TokenPayloadDTO(String iss, String sub, String aud, int duration) {
        Date now = new Date();
        this.iss = iss;
        this.sub = sub;
        this.aud = aud;
        this.iat = now;
        this.nbf = now;
        this.exp = DateUtils.addMinutes(now, duration);
        this.jti = UUID.randomUUID(true).toString();
    }

    /**
     * issuer
     */
    private String iss;

    /**
     * subject
     */
    private String sub;

    /**
     * audience
     */
    private String aud;

    /**
     * expiration
     */
    private Date exp;

    /**
     * not_before
     */
    private Date nbf;

    /**
     * issued_at
     */
    private Date iat;

    /**
     * id
     */
    private String jti;

}
