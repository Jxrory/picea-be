package com.jxrory.picea.common.interceptor;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * @author Rory
 * @date 2022/1/8 下午9:00
 */
@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_KEY = "Authorization";

    @Value(value = "${auth.bearer.secret-key}")
    private String tokenSecretKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.debug(">>> AuthorizationInterceptor preHandle --------");
        // 取出 token
        String token = request.getHeader(AUTHORIZATION_KEY);
        log.info("token={}", token);
        // 去除 "Bearer " 数据
        String realToken = StringUtils.substring(token, "Bearer ".length());

        try {
            Claims jwtBody = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(tokenSecretKey))
                    .parseClaimsJws(realToken).getBody();
            log.debug("token uid={}", jwtBody.getAudience());
            return true;
        } catch (ExpiredJwtException e) {
            // token 过期需要重新登录
            log.warn("token 过期需要重新登录 token={}", token);
            try {
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(new ErrorMsg("00001", "凭证过期需要重新登录!!")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            log.error("token 校验失败 err={}", e.getMessage(), e);
            try {
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(new ErrorMsg("00001", "认证失败请重新登录!!")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // 校验未通过
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        log.debug(">>> AuthorizationInterceptor postHandle --------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        log.debug(">>> AuthorizationInterceptor afterCompletion --------");
    }
}
