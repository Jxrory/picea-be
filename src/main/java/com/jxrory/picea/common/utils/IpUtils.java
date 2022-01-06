package com.jxrory.picea.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rory
 * @date 2022/1/3 下午11:13
 */
public class IpUtils {

    public static String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if(checkIsIp(ip)){
            return ip;
        }

        ip = request.getHeader("X-Real-IP");
        if(checkIsIp(ip)){
            return ip;
        }

        ip = request.getRemoteAddr();
        if("0:0:0:0:0:0:0:1".equals(ip)){
            //本地 localhost访问 ipv6
            ip = "127.0.0.1";
        }
        if(checkIsIp(ip)){
            return ip;
        }

        return "";
    }

    public static boolean checkIsIp(String ip){
        if(StringUtils.isBlank(ip)){
            return false;
        }

        if("unKnown".equals(ip)){
            return false;
        }

        if("unknown".equals(ip)){
            return false;
        }

        return ip.split("\\.").length == 4;
    }
}
