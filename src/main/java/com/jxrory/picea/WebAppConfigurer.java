package com.jxrory.picea;

import com.jxrory.picea.common.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Rory
 * @date 2022/1/8 下午9:29
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Resource
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/api/auth/login").order(0);
    }
}
