package com.book.util;

import net.paoding.rose.web.Invocation;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;

public class WebUtil {

    /**
     * 获取COOKIE
     *
     * @param name
     */
    public static String getCookieValue(final Invocation inv, final String name) {
        Cookie[] cookies = inv.getRequest().getCookies();
        if (cookies == null) return null;
        for (Cookie ck : cookies) {
            if (StringUtils.equalsIgnoreCase(name, ck.getName()))
                return ck.getValue();
        }
        return null;
    }


    /**
     * @param inv
     * @param param
     * @param defaultvalue
     * @return
     * @Todo 根据指定参数在Request对象得到整数值，如果为空或出错则返回给定的默认值
     */
    public static int getIntByRequestParament(final Invocation inv,
                                              String param, int defaultvalue) {
        try {
            return Integer.parseInt(inv.getRequest().getParameter(param));
        } catch (Exception e) {
            return defaultvalue;
        }
    }
}