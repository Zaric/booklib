package com.book.controllers;

import com.book.biz.user.UserBiz;
import com.book.util.CryptUtils;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.book.dao.UserDAO;
import com.book.model.User;
import com.book.util.Utils;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author zhangzuoqiang
 * @Date Nov 23, 2012 12:36:11 AM
 */
public class LoginController {

    @Autowired
    private UserDAO userDAO;

    private UserBiz userBiz = new UserBiz();

    @Get("")
    public String show() throws IOException {
        return "login";
    }

    @Post("")
    public String doLogin(final Invocation inv,
                          @Param("loginName") final String loginName,
                          @Param("password") final String password,
                          @Param("verifyCode") final String verifyCode) {
        if (StringUtils.isEmpty(loginName.trim())
                || StringUtils.isEmpty(password.trim())
                || StringUtils.isEmpty(verifyCode.trim())) {
            inv.addModel("error", "不能为空！");
            return "login";
        }

        final User user = this.userDAO.getByLoginName(loginName.trim());
        if (user == null) {
            inv.addModel("loginName", loginName.trim());
            inv.addModel("error", "用户名不存在");
            return "login";
        }
        if (user.getStatus() == 0) {
            inv.addModel("loginName", loginName.trim());
            inv.addModel("error", "用户未激活！");
            return "login";
        }
        if (user.getStatus() == 2) {
            inv.addModel("loginName", loginName.trim());
            inv.addModel("error", "此用户已被注销，无法登录！");
            return "login";
        }
        //  验证码
        HttpSession session = inv.getRequest().getSession();
        if (null != session) {
            String code = session.getAttribute(Utils.VERIFY_CODE).toString();
            if (!StringUtils.equalsIgnoreCase(code, verifyCode)) {
                inv.addModel("loginName", loginName);
                inv.addModel("error", "验证码错误");
                return "login";
            }
        }
        String pwd = CryptUtils.encrypt(password.trim(), CryptUtils.CRYPT_KEY);
        if (!user.getPassword().equals(pwd)) {
            inv.addModel("loginName", loginName);
            inv.addModel("error", "密码错误");
            return "login";
        }

        userBiz.loginAction(inv, user);
        return "r:" + Utils.path(inv) + "/book";
    }
}
