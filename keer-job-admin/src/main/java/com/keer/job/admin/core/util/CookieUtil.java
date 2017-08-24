package com.keer.job.admin.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieUtil
 */
public class CookieUtil {
    //默认缓存时间，秒
    private static final int COOKIE_MAX_AGE = 60 * 60 * 2;
    //保存路径，根路径
    private static final String COOKIE_PATH = "/";

    /**
     * 保存Cookie
     *
     * @param response
     * @param key
     * @param value
     * @param ifRemember
     */
    public static void set(HttpServletResponse response, String key, String
            value, boolean ifRemember) {
        int age;
        if (ifRemember) {
            age = COOKIE_MAX_AGE;
        } else {
            age = -1;
        }

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(age);
        cookie.setPath(COOKIE_PATH);
        response.addCookie(cookie);
    }

    /**
     * 保存Cookie
     *
     * @param response
     * @param key
     * @param value
     * @param maxAge
     * @param path
     */
    public static void set(HttpServletResponse response, String key, String
            value, int maxAge, String path) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 查询Value
     *
     * @param request
     * @param key
     * @return
     */
    public static String getValue(HttpServletRequest request, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 查询Cookie
     *
     * @param request
     * @param key
     * @return
     */
    private static Cookie get(HttpServletRequest request, String key) {
        Cookie[] arr_cookie = request.getCookies();
        if (arr_cookie != null && arr_cookie.length > 0) {
            for (Cookie cookie : arr_cookie) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 删除Cookie
     *
     * @param request
     * @param response
     * @param key
     */
    public static void remove(HttpServletRequest request, HttpServletResponse
            response, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            set(response, key, "", 0, COOKIE_PATH);
        }
    }
}
