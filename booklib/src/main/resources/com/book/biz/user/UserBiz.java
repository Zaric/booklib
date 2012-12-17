package com.book.biz.user;

import com.book.model.User;
import com.book.util.EmailUtils;
import net.paoding.rose.web.Invocation;

import javax.servlet.http.Cookie;

/**
 * User: Mooqu.cn
 * Date: 12-12-12
 */
public class UserBiz {



    /**
     * @param inv
     * @param user
     */
    public final void loginAction(final Invocation inv, final User user) {
        inv.getRequest().getSession().setAttribute("loginUser", user);
        inv.getRequest().getSession().setMaxInactiveInterval(10 * 60);
        if (null == user) {
            return;
        }
        // final Cookie cookie = new Cookie("JSESSIONID",
        // inv.getRequest().getSession().getId());
        final Cookie cookie = new Cookie("user", user.getUuid());
        cookie.setMaxAge(5 * 60);
        cookie.setPath("/");
        inv.getResponse().addCookie(cookie);
    }
}
