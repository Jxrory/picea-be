package com.jxrory.picea.user.service;

import com.jxrory.picea.user.model.request.LoginRequest;
import com.jxrory.picea.user.model.vo.LoginVO;

/**
 * @author Rory
 * @date 2022/1/3 下午11:23
 */
public interface LoginService {

    /**
     * 使用账户名和密码登录
     *
     * @param request 登录的请求参数
     * @return {@link LoginVO}
     */
    LoginVO login(LoginRequest request);
}
