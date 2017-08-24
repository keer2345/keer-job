package com.keer.job.admin.controller.interceptor;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * push cookies to model as cookieMap
 */
public class CookieInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && ArrayUtils.isNotEmpty(request.getCookies())) {
            HashMap<String, Cookie> cookieMap = new HashMap<String, Cookie>();
            for (Cookie cookie : request.getCookies()) {
                cookieMap.put(cookie.getName(), cookie);
            }
            modelAndView.addObject("cookieMap", cookieMap);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
