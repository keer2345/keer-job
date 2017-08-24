package com.keer.job.admin.controller.interceptor;

import com.keer.job.admin.controller.annotation.PermessionLimit;
import com.keer.job.admin.core.util.CookieUtil;
import com.keer.job.admin.core.util.PropertiesUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 * 权限拦截，简易版
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    public static final String LOGIN_IDENTITY_KEY = "LOGIN_IDENTITY";
    public static final String LOGIN_IDENTITY_TOKEN;

    static {
        String username = PropertiesUtil.getString("keer.job.login.username");
        String password = PropertiesUtil.getString("keer.job.login.password");
        String temp = username + "_" + password;
        LOGIN_IDENTITY_TOKEN = new BigInteger(1, temp.getBytes()).toString(16);
    }

    public static boolean login(HttpServletResponse response, boolean
            ifRemember) {
        CookieUtil.set(response, LOGIN_IDENTITY_KEY, LOGIN_IDENTITY_TOKEN, ifRemember);
        return true;
    }

    public static void logout(HttpServletRequest request,
                              HttpServletResponse response) {
        CookieUtil.remove(request, response, LOGIN_IDENTITY_KEY);
    }

    public static boolean ifLogin(HttpServletRequest request) {
        String indentityInfo = CookieUtil.getValue(request, LOGIN_IDENTITY_KEY);
        if (indentityInfo == null || !LOGIN_IDENTITY_TOKEN.equals(indentityInfo
                .trim())) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new BigInteger(1, "username_password".getBytes())
                .toString(16));
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }
        if (!ifLogin(request)) {
            HandlerMethod method = (HandlerMethod) handler;
            PermessionLimit permessionLimit = method.getMethodAnnotation
                    (PermessionLimit.class);
            if (permessionLimit == null || permessionLimit.limit()) {
                response.sendRedirect(request.getContextPath() + "/toLogin");
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }
}
