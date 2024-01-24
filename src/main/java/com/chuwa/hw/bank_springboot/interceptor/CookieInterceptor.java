package com.chuwa.hw.bank_springboot.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CookieInterceptor implements HandlerInterceptor{
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Cookie cookie = new Cookie("cookieName", "cookieValue");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 有效期7天
        response.addCookie(cookie);
    }
}
