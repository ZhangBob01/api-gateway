package com.bob.apigateway.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: toudaizhi
 * @Date: 2019-06-04 12:17
 * @Description:
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response,
                                 String name, String value, int maxAge){
        //设置cookie
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name){

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie:cookies){
                if(name.equals(cookie.getName())){
                    return cookie;
                }
            }
        }

        return null;
    }
}
