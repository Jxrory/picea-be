package com.jxrory.picea.user.model.vo;

import lombok.Data;

/**
 * @author Rory
 * @date 2022/1/3 下午11:24
 */
@Data
public class LoginVO {

    public LoginVO(String token) {
        this.token = token;
    }

    /**
     * token
     */
    private String token;
}
